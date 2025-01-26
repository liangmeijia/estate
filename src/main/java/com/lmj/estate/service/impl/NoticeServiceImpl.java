package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.NoticeMapper;
import com.lmj.estate.domain.DTO.NoticeAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.NoticeVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.NoticeQuery;
import com.lmj.estate.entity.Notice;
import com.lmj.estate.service.NoticeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lmj
 * @version 1.0
 * @description 公告服务
 * @date 2025/01/26 10:49:35
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Override
    public R<List<String>> getNoticesTitleTop(Integer counts) {
        List<String> titlesTop = baseMapper.selectNoticesTitleTop(counts);
        return R.ok(titlesTop);
    }

    @Override
    public PageDTO<NoticeVO> getNotices(NoticeQuery noticeQuery) {
        Page<Notice> page = noticeQuery.toMpPageDefaultByCreateTime();

        String title = noticeQuery.getTitle();
        LocalDateTime startTime = noticeQuery.getStartTime();

        lambdaQuery().like(title!=null,Notice::getTitle,title)
                .ge(startTime!=null,Notice::getCreateTime,startTime)
                .page(page);

        return PageDTO.of(page, NoticeVO.class);
    }

    @Override
    public R<Void> addNotice(NoticeAddDTO noticeAddDTO) {
        Notice notice = BeanUtil.copyProperties(noticeAddDTO, Notice.class);
        notice.setCreateTime(LocalDateTime.now());
        baseMapper.insert(notice);
        return R.ok("公告发布成功");
    }
}
