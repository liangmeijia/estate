package com.lmj.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.ComplaintAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.ComplaintVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.ComplaintQuery;
import com.lmj.estate.entity.Complaints;

public interface ComplaintService extends IService<Complaints> {
    R<Void> addComplaint(ComplaintAddDTO complaintAddDTO);

    PageDTO<ComplaintVO> getComplaints(ComplaintQuery complaintsQuery);
}
