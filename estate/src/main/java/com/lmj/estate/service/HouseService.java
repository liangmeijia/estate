package com.lmj.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.HouseAddDTO;
import com.lmj.estate.domain.DTO.HouseUpdateDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.HouseVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.HouseQuery;
import com.lmj.estate.entity.House;

/**
 * description
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 10:25:30
 */
public interface HouseService extends IService<House> {
    R<Void> addHouse(HouseAddDTO houseDTO);

    PageDTO<HouseVO> getHouses(HouseQuery houseQuery);

    R<Void> updateHouse(HouseUpdateDTO houseDTO);
}
