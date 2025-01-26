package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.ComplaintAddDTO;
import com.lmj.estate.domain.DTO.ComplaintUpdateDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ProcessComplaintDTO;
import com.lmj.estate.domain.VO.ComplaintVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.ComplaintQuery;
import com.lmj.estate.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 修改投诉申请
     * @param complaintUpdateDTO 修改条件
     * @return 是否成功
     */
    @PutMapping("/complaint")
    public R<Void> updateComplaint(@Valid @RequestBody ComplaintUpdateDTO complaintUpdateDTO){
        return complaintService.updateComplaint(complaintUpdateDTO);
    }

    /**
     * 删除投诉申请
     * @param id 投诉申请id
     * @return 是否成功
     */
    @DeleteMapping("/complaint/{id}")
    public R<Void> delComplaint(@PathVariable Long id){
        return complaintService.delComplaint(id);
    }

    @PostMapping("/complaint/process")
    public R<Void> processComplaint(@Valid @RequestBody ProcessComplaintDTO processComplaintDTO){
        return complaintService.processComplaint(processComplaintDTO);
    }

}
