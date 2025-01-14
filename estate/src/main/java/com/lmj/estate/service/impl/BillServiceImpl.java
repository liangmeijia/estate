package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.BillMapper;
import com.lmj.estate.dao.BillRecordsMapper;
import com.lmj.estate.dao.HouseMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.domain.DTO.BilUpdateDTO;
import com.lmj.estate.domain.DTO.BillAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillRecordVO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.domain.query.BillRecordQuery;
import com.lmj.estate.entity.Bill;
import com.lmj.estate.entity.BillRecords;
import com.lmj.estate.entity.House;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private final UserMapper userMapper;
    private final BillRecordsMapper billRecordsMapper;
    @Override
    public PageDTO<BillVO> getBills(BillQuery billQuery) {
        // 0.初始化最终结果的 Page
        Page<Bill> finalPage = billQuery.toMpPageDefaultByCreateTime();
        List<Bill> combinedRecords = new ArrayList<>();
        List<House> houses = null;
        // 1.构建查询条件
        Long userId = billQuery.getId();
        String amountName = billQuery.getAmountName();
        BillPaymentStatus status = billQuery.getStatus();
        if(StrUtil.isEmptyIfStr(userId)){
            // 2.查询房产表中的所有记录
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
        //3。循环获取每一个房产下的所有账单
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
        // 4.手动分页逻辑
        Long current = finalPage.getCurrent();
        Long size = finalPage.getSize();
        Long start = (current - 1) * size;
        Long end = Math.min(start + size, combinedRecords.size());
        List<Bill> paginatedList = combinedRecords.subList(start.intValue(), end.intValue());

        // 5.设置结果到最终 Page 对象
        finalPage.setRecords(paginatedList);
        finalPage.setTotal(combinedRecords.size());
        //6.得到结果
        return PageDTO.of(finalPage, bill -> {
            BillVO billVO = BeanUtil.copyProperties(bill, BillVO.class);
            return billVO;
        });
    }

    @Override
    public R<Void> addBill(BillAddDTO billDTO) {
        //0.判断添加账单的房产是否存在
        House house = isHouse(billDTO.getAddress(),billDTO.getBuilding(),billDTO.getUnit(),billDTO.getNumber());
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
    public R<Void> updateBill(BilUpdateDTO billDTO) {
        //0.判断修改账单的房产是否存在
        House house = isHouse(billDTO.getAddress(),billDTO.getBuilding(),billDTO.getUnit(),billDTO.getNumber());
        if(StrUtil.isEmptyIfStr(house)){
            return R.no("无此房产");
        }
        //1.
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

    @Override
    @Transactional
    public R<Double> billPayment(Long userId, Long id) {
        //获取缴费人的余额
        User user = userMapper.selectById(userId);
        Double balance = user.getBalance();
        //获取账单费用
        Bill bill = baseMapper.selectById(id);
        Double price = bill.getPrice();
        if(BillPaymentStatus.PAYMENT_SUCCESS.equals(bill.getStatus())){
            return R.no("此账单已缴费成功");
        }
        //初始化缴费记录
        BillRecords billRecords = BeanUtil.copyProperties(bill, BillRecords.class);
        billRecords.setAmount(price);
        LocalDateTime now = LocalDateTime.now();
        billRecords.setDate(now);
        billRecords.setCreateTime(now);
        billRecords.setUserId(userId);
        if(balance<price){
            //余额不足，更新账单的缴费状态
            bill.setStatus(BillPaymentStatus.PAYMENT_FAILED);
            baseMapper.updateById(bill);
            //生成缴费记录
            billRecords.setStatus(BillPaymentStatus.PAYMENT_FAILED);
            billRecordsMapper.insert(billRecords);
            return R.no("余额不足，请先充值");
        }else {
            //更新缴费人的余额
            balance = balance - price;
            user.setBalance(balance);
            userMapper.updateById(user);
            //更新账单的缴费状态
            bill.setStatus(BillPaymentStatus.PAYMENT_SUCCESS);
            baseMapper.updateById(bill);
            //生成缴费记录
            billRecords.setStatus(BillPaymentStatus.PAYMENT_SUCCESS);
            billRecordsMapper.insert(billRecords);
            return R.ok(balance);
        }
    }

    @Override
    public PageDTO<BillRecordVO> getBillRecords(BillRecordQuery billRecordQuery) {
        //1.分页条件
        Page<BillRecords> page = billRecordQuery.toMpPageDefaultByCreateTime();
        //2.查询条件
        String address = billRecordQuery.getAddress();
        String building = billRecordQuery.getBuilding();
        String unit = billRecordQuery.getUnit();
        String number = billRecordQuery.getNumber();
        String amountName = billRecordQuery.getAmountName();
        LocalDateTime startTime = billRecordQuery.getStartTime();
        LocalDateTime endTime = billRecordQuery.getEndTime();
        BillPaymentStatus status = billRecordQuery.getStatus();
        LambdaQueryWrapper<BillRecords> LQW = new LambdaQueryWrapper<>();
        LQW.like(address!=null ,BillRecords::getAddress,address)
                .eq(building!=null,BillRecords::getBuilding,building)
                .eq(unit!=null,BillRecords::getUnit,unit)
                .eq(number!=null,BillRecords::getNumber,number)
                .eq(amountName!=null,BillRecords::getAmountName,amountName)
                .ge(startTime!=null,BillRecords::getDate,startTime)
                .le(endTime!=null,BillRecords::getDate,endTime)
                .eq(status!=null,BillRecords::getStatus,status);
        billRecordsMapper.selectPage(page,LQW);
        //3.返回结果
        return PageDTO.of(page,billRecords -> {
            BillRecordVO billRecordVO = BeanUtil.copyProperties(billRecords, BillRecordVO.class);
            User user = userMapper.selectById(billRecords.getUserId());
            billRecordVO.setName(user.getName());
            return billRecordVO;
        });
    }

    /**
     * 判断地址是否存在
     * @param address 地址
     * @param building 栋
     * @param unit 单元
     * @param number 门牌号
     * @return 房屋
     */
    private House isHouse(String address,String building,String unit,String number){
        LambdaQueryWrapper<House> LQW = new LambdaQueryWrapper<>();
        LQW.eq(address!=null,House::getAddress,address)
                .eq(building!=null,House::getBuilding,building)
                .eq(unit!=null,House::getUnit,unit)
                .eq(number!=null,House::getNumber,number);
        return houseMapper.selectOne(LQW);
    }
}
