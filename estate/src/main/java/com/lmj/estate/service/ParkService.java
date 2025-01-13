package com.lmj.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ParkAddDTO;
import com.lmj.estate.domain.DTO.ParkUpdateDTO;
import com.lmj.estate.domain.VO.ParkVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.ParkQuery;
import com.lmj.estate.entity.Park;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/13 14:30:42
 */
public interface ParkService extends IService<Park> {
    R<Void> addPark(ParkAddDTO parkDTO);

    R<Void> updatePark(ParkUpdateDTO parkDTO);

    PageDTO<ParkVO> getParks(ParkQuery parkQuery);
}
