package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.ComplaintAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.ComplaintVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.ComplaintQuery;
import com.lmj.estate.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lmj
 * @version 1.0
 * @description 投诉服务
 * @date 2025/01/24 16:46:13
 */
@RestController
@RequiredArgsConstructor
public class ComplaintsController {
    private final ComplaintService complaintService;

    /**
     * 投诉申请
     * @param complaintAddDTO 投诉申请条件
     * @return 是否成功
     */
    @PostMapping("complaint")
    public R<Void> addComplaint(@Valid @RequestBody ComplaintAddDTO complaintAddDTO){
        return complaintService.addComplaint(complaintAddDTO);
    }

    /**
     * 分页查询投诉申请
     * @param complaintsQuery 查询条件
     * @return 分页结果
     */
    @PostMapping("complaints")
    public R<PageDTO<ComplaintVO>> getComplaints(@Valid @RequestBody ComplaintQuery complaintsQuery){
        return R.ok(complaintService.getComplaints(complaintsQuery));
    }

}
