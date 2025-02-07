package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.NoticeAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.NoticeDetailVO;
import com.lmj.estate.domain.VO.NoticeTitleTopVO;
import com.lmj.estate.domain.VO.NoticeVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.NoticeQuery;
import com.lmj.estate.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lmj
 * @version 1.0
 * @description 公告服务
 * @date 2025/01/26 10:46:55
 */
@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    /**
     * 发布公告
     * @param noticeAddDTO 公告信息
     * @return 是否成功
     */
    @PostMapping("notice")
    public R<Void> addNotice(@Valid @RequestBody NoticeAddDTO noticeAddDTO){
        return noticeService.addNotice(noticeAddDTO);
    }
    /**
     * 分页查询公告
     * @param noticeQuery 查询条件
     * @return 查询结果
     */
    @PostMapping("notices")
    public R<PageDTO<NoticeVO>> getNotices(@Valid @RequestBody NoticeQuery noticeQuery){
        return R.ok(noticeService.getNotices(noticeQuery));
    }
    /**
     * 查询未读公告数量
     * @return 未读公告数量
     */
    @GetMapping("/notice/read/no/counts")
    public R<Long> getNoticesUnReadCounts(@RequestParam Long userId){
        return noticeService.getNoticesUnReadCounts(userId);
    }

    /**
     * 查询最新创建的前几个公告标题
     * @param counts 公告标题的个数
     * @param userId 用户id
     * @return 标题列表
     */
    @GetMapping("/notices/top")
    public R<List<NoticeTitleTopVO>> getNoticesTitleTop(@RequestParam Integer counts,@RequestParam Long userId){
        return noticeService.getNoticesTitleTop(counts,userId);
    }

    /**
     * 查询公告详情
     * @param noticeId 公告id
     * @param userId 用户id
     * @return 公告详情
     */
    @GetMapping("/notice")
    public R<NoticeDetailVO> getNoticeDetails(@RequestParam @NotNull Long noticeId, @RequestParam @NotNull Long userId){
        return noticeService.getNoticeDetail(noticeId,userId);
    }

    /**
     * 删除公告
     * @param id 公告id
     * @return 是否成功
     */
    @DeleteMapping("/notice/{id}")
    public R<Void> delNotice(@PathVariable Long id){
        return noticeService.delNotice(id);
    }
}
