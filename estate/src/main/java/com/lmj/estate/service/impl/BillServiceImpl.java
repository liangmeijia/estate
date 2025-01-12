package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.BillMapper;
import com.lmj.estate.dao.HouseMapper;
import com.lmj.estate.domain.DTO.BillDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.entity.Bill;
import com.lmj.estate.entity.House;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * description 账单服务类
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 17:45:27
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    private final HouseMapper houseMapper;
    @Override
    public PageDTO<BillVO> getBills(BillQuery billQuery) {
        // 0.初始化最终结果的 Page
        Page<Bill> finalPage = billQuery.toMpPageDefaultByCreateTime();
        List<Bill> combinedRecords = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        // 1.构建查询条件
        Long userId = billQuery.getId();
        String amountName = billQuery.getAmountName();
        BillPaymentStatus status = billQuery.getStatus();
        if(StrUtil.isEmptyIfStr(userId)){
            // 2.查询表中的所有记录
            houses = houseMapper.selectList(null);
        }else {
            //2.查询用户名下的所有房产
            LambdaQueryWrapper<House> LQWHouse = new LambdaQueryWrapper<>();
            LQWHouse.eq(House::getUserId , userId);
            houses = houseMapper.selectList(LQWHouse);
        }
        if(StrUtil.isEmptyIfStr(houses)){
            return null;
        }
        //3。获取每一个房产下的所有账单
        for (House house:houses){
            LambdaQueryWrapper<Bill> LQW = new LambdaQueryWrapper<>();
            LQW.eq(house.getAddress()!=null,Bill::getAddress,house.getAddress())
                    .eq(house.getBuilding()!=null,Bill::getBuilding,house.getBuilding())
                    .eq(house.getUnit()!=null,Bill::getUnit,house.getUnit())
                    .eq(house.getNumber()!=null,Bill::getNumber,house.getNumber())
                    .eq(amountName!=null,Bill::getAmountName,amountName)
                    .eq(status!=null,Bill::getStatus,status);
            // 累积当前结果到 combinedRecords
            combinedRecords.addAll(baseMapper.selectList(LQW));
        }
        // 手动分页逻辑
        Long current = finalPage.getCurrent();
        Long size = finalPage.getSize();
        Long start = (current - 1) * size;
        Long end = Math.min(start + size, combinedRecords.size());
        List<Bill> paginatedList = combinedRecords.subList(start.intValue(), end.intValue());

        // 设置结果到最终 Page 对象
        finalPage.setRecords(paginatedList);
        finalPage.setTotal(combinedRecords.size());
        //4.得到结果
        return PageDTO.of(finalPage, bill -> {
            BillVO billVO = BeanUtil.copyProperties(bill, BillVO.class);
            return billVO;
        });
    }

    @Override
    public R<Void> addBill(BillDTO billDTO) {
        //0.判断添加账单的房产是否存在
        LambdaQueryWrapper<House> LQW = new LambdaQueryWrapper<>();
        String address = billDTO.getAddress();
        String building = billDTO.getBuilding();
        String number = billDTO.getNumber();
        LQW.eq(address!=null,House::getAddress,address)
                .eq(building!=null,House::getBuilding,building)
                .eq(number!=null,House::getNumber,number);
        House house = houseMapper.selectOne(LQW);
        if(StrUtil.isEmptyIfStr(house)){
            return R.no("无此房产");
        }
        //1.添加账单
        Bill bill = new Bill();
        BeanUtil.copyProperties(billDTO,bill);
        bill.setCreateTime(LocalDateTime.now());
        try{
            baseMapper.insert(bill);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.no();
        }
    }

    @Override
    public R<Void> updateBill(BillDTO billDTO) {
        Bill bill = new Bill();
        BeanUtil.copyProperties(billDTO,bill);
        bill.setUpdateTime(LocalDateTime.now());
        try {
            baseMapper.updateById(bill);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.no();
        }
    }
}
