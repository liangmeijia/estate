package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.NoticeMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.dao.UserNoticeReadMapper;
import com.lmj.estate.domain.DTO.NoticeAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.NoticeDetailVO;
import com.lmj.estate.domain.VO.NoticeTitleTopVO;
import com.lmj.estate.domain.VO.NoticeVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.ReadStatus;
import com.lmj.estate.domain.query.NoticeQuery;
import com.lmj.estate.entity.Notice;
import com.lmj.estate.entity.User;
import com.lmj.estate.entity.UserNoticeRead;
import com.lmj.estate.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lmj
 * @version 1.0
 * @description 公告服务
 * @date 2025/01/26 10:49:35
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    private final UserNoticeReadMapper userNoticeReadMapper;
    private final UserMapper userMapper;
    @Override
    public R<List<NoticeTitleTopVO>> getNoticesTitleTop(Integer counts,Long userId) {
        //1.查询出用户的前counts个公告阅读记录
        LambdaQueryWrapper<UserNoticeRead> LQW = new LambdaQueryWrapper<>();
        LQW.eq(userId!=null,UserNoticeRead::getUserId,userId)
                .orderByDesc(UserNoticeRead::getCreateTime)
                .last("LIMIT "+counts);
        List<UserNoticeRead> userNoticeReads = userNoticeReadMapper.selectList(LQW);
        //2.根据公告阅读记录查询公告的 标题 和创建时间
        List<NoticeTitleTopVO> noticeTitleTopVOS = new ArrayList<>(userNoticeReads.size());
        for (UserNoticeRead userNoticeRead:userNoticeReads){
            Long noticeId = userNoticeRead.getNoticeId();
            Notice notice = baseMapper.selectById(noticeId);

            NoticeTitleTopVO noticeTitleTopVO = new NoticeTitleTopVO();
            noticeTitleTopVO.setTitle(notice.getTitle());
            noticeTitleTopVO.setCreateTime(userNoticeRead.getCreateTime());
            noticeTitleTopVOS.add(noticeTitleTopVO);
        }
        return R.ok(noticeTitleTopVOS);
    }

    @Override
    public PageDTO<NoticeVO> getNotices(NoticeQuery noticeQuery) {
        Page<UserNoticeRead> page = noticeQuery.toMpPageDefaultByCreateTime();

        Long userId = noticeQuery.getUserId();
        ReadStatus noticeReadStatus = noticeQuery.getStatus();
        LocalDateTime startTime = noticeQuery.getStartTime();

        //1.
        LambdaQueryWrapper<UserNoticeRead> LQW = new LambdaQueryWrapper<>();
        LQW.eq(userId!=null,UserNoticeRead::getUserId,userId)
                .eq(noticeReadStatus!=null,UserNoticeRead::getStatus,noticeReadStatus)
                .ge(startTime!=null,UserNoticeRead::getCreateTime,startTime);

        userNoticeReadMapper.selectPage(page,LQW);

        return PageDTO.of(page, userNoticeRead->{
            Notice notice = baseMapper.selectById(userNoticeRead.getNoticeId());
            NoticeVO noticeVO = new NoticeVO();
            if(!StrUtil.isEmptyIfStr(notice)){
                noticeVO = BeanUtil.copyProperties(notice, NoticeVO.class);
            }
            noticeVO.setStatus(userNoticeRead.getStatus());
            return noticeVO;
        });
    }

    @Override
    @Transactional
    public R<Void> addNotice(NoticeAddDTO noticeAddDTO) {
        LocalDateTime now = LocalDateTime.now();
        //1.插入公告
        Notice notice = BeanUtil.copyProperties(noticeAddDTO, Notice.class);
        notice.setCreateTime(now);
        baseMapper.insert(notice);
        //2.插入用户的公告阅读状态(预先为每一个用户批量插入默认的阅读状态)
        List<User> users = userMapper.selectList(null);
        List<UserNoticeRead> userNoticeReadRecords = users.stream()
                .map(user -> new UserNoticeRead(user.getId(), notice.getId(), ReadStatus.READ_NO,now))
                .collect(Collectors.toList());
        userNoticeReadMapper.insertAll(userNoticeReadRecords);
        return R.ok("公告发布成功");
    }

    @Override
    public R<Long> getNoticesUnReadCounts(Long userId) {
        LambdaQueryWrapper<UserNoticeRead> LWQ = new LambdaQueryWrapper();
        LWQ.eq(UserNoticeRead::getStatus, ReadStatus.READ_NO).
                eq(userId!=null,UserNoticeRead::getUserId,userId);
        Long count = userNoticeReadMapper.selectCount(LWQ);
        return R.ok(count);
    }

    @Override
    public R<NoticeDetailVO> getNoticeDetail(Long noticeId,Long userId) {
        //1.查询公告
        Notice notice = baseMapper.selectById(noticeId);
        NoticeDetailVO noticeDetailVO = BeanUtil.copyProperties(notice, NoticeDetailVO.class);
        //2.查询公告阅读状态
        LambdaQueryWrapper<UserNoticeRead> LWQ = new LambdaQueryWrapper();
        LWQ.eq(noticeId!=null,UserNoticeRead::getNoticeId,noticeId)
                .eq(userId!=null,UserNoticeRead::getUserId,userId);
        UserNoticeRead userNoticeRead = userNoticeReadMapper.selectOne(LWQ);
        ReadStatus status = userNoticeRead.getStatus();
        if(ReadStatus.READ_NO.equals(status)){
            //如果用户未读，设置为已读
            userNoticeRead.setStatus(ReadStatus.READ_YES);
            userNoticeReadMapper.updateById(userNoticeRead);
        }
        //3.返回VO设置阅读状态为已读
        noticeDetailVO.setStatus(ReadStatus.READ_YES);

        return R.ok(noticeDetailVO);
    }

    @Override
    @Transactional
    public R<Void> delNotice(Long id) {
        //1.将所有用户关于此公告的阅读记录删除
        LambdaQueryWrapper<UserNoticeRead> LQW = new LambdaQueryWrapper<>();
        LQW.eq(id!=null,UserNoticeRead::getNoticeId,id);
        userNoticeReadMapper.delete(LQW);
        //2.删除公告
        baseMapper.deleteById(id);
        return R.ok();
    }
}
