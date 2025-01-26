package com.lmj.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.NoticeAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.NoticeVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.NoticeQuery;
import com.lmj.estate.entity.Notice;

import java.util.List;

/**
 * @author lmj
 * @version 1.0
 * @description 公告服务
 * @date 2025/01/26 10:48:24
 */
public interface NoticeService extends IService<Notice> {
    R<List<String>> getNoticesTitleTop(Integer counts);

    PageDTO<NoticeVO> getNotices(NoticeQuery noticeQuery);

    R<Void> addNotice(NoticeAddDTO noticeAddDTO);
}
