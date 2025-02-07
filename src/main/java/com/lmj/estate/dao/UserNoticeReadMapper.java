package com.lmj.estate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmj.estate.entity.UserNoticeRead;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserNoticeReadMapper extends BaseMapper<UserNoticeRead> {
    void insertAll(@Param("records") List<UserNoticeRead> records);
}