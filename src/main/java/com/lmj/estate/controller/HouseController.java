package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.*;
import com.lmj.estate.domain.VO.HouseVO;
import com.lmj.estate.domain.VO.ParkVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.HouseQuery;
import com.lmj.estate.domain.query.ParkQuery;
import com.lmj.estate.service.HouseService;
import com.lmj.estate.service.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description 房屋及设施管理
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 10:22:33
 */
@RestController
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final ParkService parkService;
    /**
     * 房屋信息登记
     * @param houseDTO 房屋信息
     * @return 是否成功
     */
    @PostMapping("/house")
    public R<Void> addHouse(@Valid @RequestBody HouseAddDTO houseDTO){
        return houseService.addHouse(houseDTO);
    }

    /**
     * 删除房屋
     * @param id 房屋id
     * @return 是否成功
     */
    @DeleteMapping("/house/{id}")
    public R<Void> deleteHouse(@PathVariable Long id){
        if(houseService.removeById(id)){
            return R.ok();
        }else {
            return R.no();
        }
    }

    /**
     * 修改房屋信息
     * @param houseDTO 要修改的房屋信息
     * @return 是否成功
     */
    @PutMapping("/house")
    public R<Void> updateHouse(@Valid @RequestBody HouseUpdateDTO houseDTO){
        return houseService.updateHouse(houseDTO);
    }
    /**
     * 分页查询房屋
     * @param houseQuery 分页查询条件
     * @return 查询结果
     */
    @PostMapping("/houses")
    public R<PageDTO<HouseVO>> getHousePage(@RequestBody HouseQuery houseQuery){
        return R.ok(houseService.getHouses(houseQuery));
    }

    /**
     * 添加车位
     * @param parkDTO 车位信息
     * @return 是否成功
     */
    @PostMapping("/park")
    public R<Void> addPark(@Valid  @RequestBody ParkAddDTO parkDTO){
        return parkService.addPark(parkDTO);
    }

    /**
     * 修改车位信息
     * @param parkDTO 要修改的车位信息
     * @return 是否成功
     */
    @PutMapping("/park")
    public R<Void> updatePark(@Valid @RequestBody ParkUpdateDTO parkDTO){
        return parkService.updatePark(parkDTO);
    }

    /**
     * 删除车位
     * @param id 车位id
     * @return 是否成功
     */
    @DeleteMapping("/park/{id}")
    public R<Void> deletePark(@PathVariable Long id){
        if(parkService.removeById(id)){
            return R.ok();
        }else {
            return R.no();
        }
    }

    /**
     * 分页查询车位
     * @param parkQuery 查询条件
     * @return 查询结果
     */
    @PostMapping("/parks")
    public R<PageDTO<ParkVO>> getParks(@RequestBody ParkQuery parkQuery){
        return R.ok(parkService.getParks(parkQuery));
    }

}
