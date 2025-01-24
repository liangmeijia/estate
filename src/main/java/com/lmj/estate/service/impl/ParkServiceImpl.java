package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.ParkMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ParkAddDTO;
import com.lmj.estate.domain.DTO.ParkUpdateDTO;
import com.lmj.estate.domain.VO.ParkVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.ParkStatus;
import com.lmj.estate.domain.enums.ParkType;
import com.lmj.estate.domain.query.ParkQuery;
import com.lmj.estate.entity.Park;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 车位服务
 * @date 2025/01/13 14:31:16
 */
@Service
@RequiredArgsConstructor
public class ParkServiceImpl extends ServiceImpl<ParkMapper, Park> implements ParkService {
    private final UserMapper userMapper;
    @Override
    public R<Void> addPark(ParkAddDTO parkDTO) {
        Park park = BeanUtil.copyProperties(parkDTO, Park.class);
        //0.
        User user = userMapper.selectOneByName(parkDTO.getName());
        if(!StrUtil.isEmptyIfStr(parkDTO.getName()) && StrUtil.isEmptyIfStr(user)){
            return R.no("无此业主");
        }
        if(!StrUtil.isEmptyIfStr(user)){
            park.setUserId(user.getId());
        }
        //1.
        park.setCreateTime(LocalDateTime.now());
        baseMapper.insert(park);
        return R.ok();

    }

    @Override
    public R<Void> updatePark(ParkUpdateDTO parkDTO) {
        Park park = BeanUtil.copyProperties(parkDTO, Park.class);
        //0.
        User user = userMapper.selectOneByName(parkDTO.getName());
        if(!StrUtil.isEmptyIfStr(parkDTO.getName()) && StrUtil.isEmptyIfStr(user)){
            return R.no("无此业主");
        }
        if(!StrUtil.isEmptyIfStr(user)){
            park.setUserId(user.getId());
        }
        //1.
        park.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(park);
        return R.ok();

    }

    @Override
    public PageDTO<ParkVO> getParks(ParkQuery parkQuery) {
        Page<Park> page = parkQuery.toMpPageDefaultByCreateTime();

        String position = parkQuery.getPosition();
        ParkType type = parkQuery.getType();
        ParkStatus status = parkQuery.getStatus();
        lambdaQuery().like(position!=null,Park::getPosition,position)
                .eq(type!=null,Park::getType,type)
                .eq(status!=null,Park::getStatus,status)
                .page(page);

        return PageDTO.of(page,park -> {
            ParkVO parkVO = BeanUtil.copyProperties(park, ParkVO.class);
            //查询业主姓名
            User user = userMapper.selectById(park.getUserId());
            if(!StrUtil.isEmptyIfStr(user)){
               parkVO.setName(user.getName());
            }
            return parkVO;
        });
    }
}
