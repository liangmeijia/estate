package com.lmj.estate.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.BilUpdateDTO;
import com.lmj.estate.domain.DTO.BillAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.entity.Bill;

public interface BillService extends IService<Bill> {

    PageDTO<BillVO> getBills(BillQuery billQuery);

    R<Void> addBill(BillAddDTO billDTO);

    R<Void> updateBill(BilUpdateDTO billDTO);

}
