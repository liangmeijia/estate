package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.ComplaintsMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.domain.DTO.ComplaintAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.ComplaintVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.ComplaintsStatus;
import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.query.ComplaintQuery;
import com.lmj.estate.entity.Complaints;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 投诉服务
 * @date 2025/01/24 16:49:53
 */
@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl extends ServiceImpl<ComplaintsMapper, Complaints> implements ComplaintService {
    private final UserMapper userMapper;
    @Override
    public R<Void> addComplaint(ComplaintAddDTO complaintAddDTO) {
        Complaints complaints = BeanUtil.copyProperties(complaintAddDTO, Complaints.class);
        complaints.setStatus(ComplaintsStatus.PENDING);
        complaints.setCreateTime(LocalDateTime.now());
        baseMapper.insert(complaints);
        return R.ok("投诉成功");
    }

    @Override
    public PageDTO<ComplaintVO> getComplaints(ComplaintQuery complaintsQuery) {
        Page<Complaints> page = complaintsQuery.toMpPageDefaultByCreateTime();

        ComplaintsStatus status = complaintsQuery.getStatus();
        Long curUserId = complaintsQuery.getCurUserId();
        User curUser = userMapper.selectById(curUserId);

        lambdaQuery().eq(status!=null,Complaints::getStatus,status)
                .eq(((curUser!=null)&&(curUser.getRoleId() == UserRole.USER)),Complaints::getApplicantId,curUserId)
                .page(page);

        return PageDTO.of(page,complaints -> {
            ComplaintVO complaintVO = BeanUtil.copyProperties(complaints, ComplaintVO.class);
            User user = userMapper.selectById(complaints.getApplicantId());
            if(!StrUtil.isEmptyIfStr(user)){
                complaintVO.setApplicantName(user.getName());
                complaintVO.setApplicantPhone(user.getPhone());
            }
            return complaintVO;
        });
    }
}
