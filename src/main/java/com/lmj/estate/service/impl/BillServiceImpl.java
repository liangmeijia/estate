package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.*;
import com.lmj.estate.domain.DTO.BilUpdateDTO;
import com.lmj.estate.domain.DTO.BillAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillRecordVO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import com.lmj.estate.domain.enums.RepairStatus;
import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.domain.query.BillRecordQuery;
import com.lmj.estate.entity.*;
import com.lmj.estate.service.BillService;
import com.lmj.estate.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    private final BillRepairsMapper billRepairsMapper;
    private final RepairsMapper repairsMapper;
    @Override
    public PageDTO<BillVO> getBills(BillQuery billQuery) {
        // 0.构建分页条件
        Page<Bill> page = billQuery.toMpPageDefaultByCreateTime();
        // 1.构建查询条件
        String address = billQuery.getAddress();
        String building = billQuery.getBuilding();
        String unit = billQuery.getUnit();
        String number = billQuery.getNumber();
        String amountName = billQuery.getAmountName();
        BillPaymentStatus status = billQuery.getStatus();
        Long curUserId = billQuery.getCurUserId();
        User curUser = userMapper.selectById(curUserId);
        lambdaQuery().like(address!=null,Bill::getAddress,address)
                .eq(building!=null,Bill::getBuilding,building)
                .eq(unit!=null,Bill::getUnit,unit)
                .eq(number!=null,Bill::getNumber,number)
                .eq(amountName!=null,Bill::getAmountName,amountName)
                .eq(status!=null,Bill::getStatus,status)
                .eq((curUser!=null)&&(curUser.getRoleId()==UserRole.USER),Bill::getUserId,curUserId)
                .page(page);
        //2.得到结果
        return PageDTO.of(page, bill -> {
            BillVO billVO = BeanUtil.copyProperties(bill, BillVO.class);
            User user = userMapper.selectById(bill.getUserId());
            billVO.setName(user.getName());
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
        if(StrUtil.isEmptyIfStr(house.getUserId())){
            return R.no("此房产无户主，无法添加账单");
        }
        //1.添加账单
        Bill bill = new Bill();
        BeanUtil.copyProperties(billDTO,bill);
        bill.setUserId(house.getUserId());
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
        //1.更新账单
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
    public R<Void> billPayment(Long userId, Long id) {
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
        billRecords.setId(null);
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
            //如果此账单是维修账单,修改维修申请的状态
            LambdaQueryWrapper<BillRepairs> LQW = new LambdaQueryWrapper<>();
            LQW.eq(BillRepairs::getBillId,id);
            BillRepairs billRepairs = billRepairsMapper.selectOne(LQW);
            if(!StrUtil.isEmptyIfStr(billRepairs)){
                Repairs repairs = repairsMapper.selectById(billRepairs.getRepairId());
                repairs.setStatus(RepairStatus.REPAIRED);
                repairsMapper.updateById(repairs);
            }
            return R.ok("缴费成功");
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
        Long curUserId = billRecordQuery.getCurUserId();
        User curUser = userMapper.selectById(curUserId);
        LambdaQueryWrapper<BillRecords> LQW = new LambdaQueryWrapper<>();
        LQW.like(address!=null ,BillRecords::getAddress,address)
                .eq(building!=null,BillRecords::getBuilding,building)
                .eq(unit!=null,BillRecords::getUnit,unit)
                .eq(number!=null,BillRecords::getNumber,number)
                .eq(amountName!=null,BillRecords::getAmountName,amountName)
                .ge(startTime!=null,BillRecords::getDate,startTime)
                .le(endTime!=null,BillRecords::getDate,endTime)
                .eq(status!=null,BillRecords::getStatus,status)
                .eq((curUser!=null)&&(curUser.getRoleId()==UserRole.USER),BillRecords::getUserId,curUserId);
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
