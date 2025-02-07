package com.lmj.estate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmj.estate.domain.VO.NoticeTitleTopVO;
import com.lmj.estate.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {

}