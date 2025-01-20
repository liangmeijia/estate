package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.*;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ProcessRepairDTO;
import com.lmj.estate.domain.DTO.RepairAddDTO;
import com.lmj.estate.domain.VO.RepairDetailVO;
import com.lmj.estate.domain.VO.RepairVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import com.lmj.estate.domain.enums.RepairStatus;
import com.lmj.estate.domain.query.RepairQuery;
import com.lmj.estate.entity.Bill;
import com.lmj.estate.entity.BillRepairs;
import com.lmj.estate.entity.Repairs;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.RepairsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 维修服务
 * @date 2025/01/20 15:21:51
 */
@Service
@RequiredArgsConstructor
public class RepairsServiceImpl extends ServiceImpl<RepairsMapper, Repairs> implements RepairsService {
    private final UserMapper userMapper;
    private final BillMapper billMapper;
    private final BillRepairsMapper billRepairsMapper;
    @Override
    public R<Void> addRepair(RepairAddDTO repairsAddDTO) {
        //1.
        User user = userMapper.selectOneByName(repairsAddDTO.getApplicantName());
        if(StrUtil.isEmptyIfStr(user)){
            return R.no("无此业主，请重新填写申请人姓名");
        }
        //2.
        Repairs repairs = BeanUtil.copyProperties(repairsAddDTO, Repairs.class);
        repairs.setApplicantId(user.getId());
        repairs.setStatus(RepairStatus.UNDER_REPAIR);
        repairs.setCreateTime(LocalDateTime.now());
        try {
            baseMapper.insert(repairs);
            return R.ok("维修申请成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.no("维修申请失败");
        }

    }

    @Override
    @Transactional
    public R<Void> processRepair(ProcessRepairDTO processRepairsDTO) {
        //1.修改维修申请单
        Repairs repairs = baseMapper.selectById(processRepairsDTO.getId());
        if(StrUtil.isEmptyIfStr(repairs)){
            return R.no("无此维修申请");
        }
        repairs.setStartTime(processRepairsDTO.getStartTime());
        repairs.setEndTime(processRepairsDTO.getEndTime());
        repairs.setPrice(processRepairsDTO.getPrice());
        repairs.setStatus(RepairStatus.IN_REPAIR);
        repairs.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(repairs);
        //2.生成账单
        Bill bill = new Bill();
        bill.setAddress(repairs.getAddress());
        bill.setBuilding(repairs.getBuilding());
        bill.setUnit(repairs.getUnit());
        bill.setNumber(repairs.getNumber());
        bill.setUserId(repairs.getApplicantId());
        bill.setAmountName("维修费用");
        bill.setPrice(processRepairsDTO.getPrice());
        bill.setCostDetails(repairs.getReason());
        bill.setStatus(BillPaymentStatus.PAYMENT_NON);
        bill.setCreateTime(LocalDateTime.now());
        billMapper.insert(bill);
        //3.生成维修申请-账单 关系
        BillRepairs billRepairs = new BillRepairs();
        billRepairs.setRepairId(repairs.getId());
        billRepairs.setBillId(bill.getId());
        billRepairs.setCreateTime(LocalDateTime.now());
        billRepairsMapper.insert(billRepairs);
        return R.ok("维修申请处理成功");
    }

    @Override
    public PageDTO<RepairVO> getRepairs(RepairQuery repairQuery) {
        Page<Repairs> page = repairQuery.toMpPageDefaultByCreateTime();

        String applicantName = repairQuery.getApplicantName();
        RepairStatus status = repairQuery.getStatus();
        LocalDateTime startTime = repairQuery.getStartTime();
        LocalDateTime endTime = repairQuery.getEndTime();
        lambdaQuery().eq(applicantName!=null,Repairs::getApplicantName,applicantName)
                .eq(status!=null,Repairs::getStatus,status)
                .ge(startTime!=null,Repairs::getStartTime,startTime)
                .le(endTime!=null,Repairs::getEndTime,endTime)
                .page(page);

        return PageDTO.of(page,RepairVO.class);
    }

    @Override
    public R<RepairDetailVO> getRepairDetail(Long id) {
        Repairs repairs = baseMapper.selectById(id);
        if(StrUtil.isEmptyIfStr(repairs)){
            return R.no("无此维修申请");
        }else {
            return R.ok(BeanUtil.copyProperties(repairs, RepairDetailVO.class));
        }
    }
}
