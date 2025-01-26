package com.lmj.estate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmj.estate.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("select title from notice ORDER BY create_time desc limit 0,#{counts}")
    List<String> selectNoticesTitleTop(@Param("counts") Integer counts);

}