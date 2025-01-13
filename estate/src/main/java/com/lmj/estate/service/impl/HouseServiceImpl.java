package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.HouseMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.domain.DTO.HouseAddDTO;
import com.lmj.estate.domain.DTO.HouseUpdateDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.HouseVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.HouseQuery;
import com.lmj.estate.entity.House;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * description 房屋及设施管理服务
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 10:26:15
 */
@Service
@RequiredArgsConstructor
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {
    private final UserMapper userMapper;
    @Override
    public R<Void> addHouse(HouseAddDTO houseDTO) {
        House house = BeanUtil.copyProperties(houseDTO,House.class);
        //设置户主id
        User user = userMapper.selectOneByName(houseDTO.getName());
        if(!StrUtil.isEmptyIfStr(houseDTO.getName()) && StrUtil.isEmptyIfStr(user)){
            return R.no("无此业主");
        }
        if(!StrUtil.isEmptyIfStr(user)){
            house.setUserId(user.getId());
        }
        //设置创建时间
        house.setCreateTime(LocalDateTime.now());
        try {
            baseMapper.insert(house);
            return R.ok();
        }catch(Exception e){
            e.printStackTrace();
            return R.no();
        }
    }

    @Override
    public PageDTO<HouseVO> getHouses(HouseQuery houseQuery) {
        //构建分页条件
        Page<House> page = houseQuery.toMpPageDefaultByCreateTime();
        //构建查询条件
        String address = houseQuery.getAddress();
        String building = houseQuery.getBuilding();
        String unit = houseQuery.getUnit();
        String number = houseQuery.getNumber();
        lambdaQuery().like(address!=null,House::getAddress,address)
                .eq(building!=null,House::getBuilding,building)
                .eq(unit!=null,House::getUnit,unit)
                .eq(number!=null,House::getNumber,number)
                .page(page);
        //返回结果
        return PageDTO.of(page,house -> {
            HouseVO houseVO = BeanUtil.copyProperties(house, HouseVO.class);
            //查询户主姓名
            User user = userMapper.selectById(house.getUserId());
            if(!StrUtil.isEmptyIfStr(user)){
                houseVO.setName(user.getName());
            }

            return houseVO;
        });
    }

    @Override
    public R<Void> updateHouse(HouseUpdateDTO houseDTO) {
        House house = BeanUtil.copyProperties(houseDTO, House.class);
        //设置户主id
        User user = userMapper.selectOneByName(houseDTO.getName());
        if(!StrUtil.isEmptyIfStr(houseDTO.getName()) && StrUtil.isEmptyIfStr(user)){
            return R.no("无此业主");
        }
        if(!StrUtil.isEmptyIfStr(user)){
            house.setUserId(user.getId());
        }
        //设置更新时间
        house.setUpdateTime(LocalDateTime.now());
        try {
            baseMapper.updateById(house);
            return R.ok();
        }catch (Exception e){
            return R.no();
        }
    }
}
