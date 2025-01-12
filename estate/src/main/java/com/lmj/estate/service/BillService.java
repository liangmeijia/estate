package com.lmj.estate.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.BillDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.entity.Bill;

import java.util.List;

public interface BillService extends IService<Bill> {

    PageDTO<BillVO> getBills(BillQuery billQuery);

    R<Void> addBill(BillDTO billDTO);

    R<Void> updateBill(BillDTO billDTO);

}
