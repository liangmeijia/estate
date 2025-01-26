package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.NoticeAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.NoticeVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.NoticeQuery;
import com.lmj.estate.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * 查询最新创建的前几个公告标题
     * @param counts 公告标题的个数
     * @return 标题列表
     */
    @GetMapping("/notices/top")
    public R<List<String>> getNoticesTitleTop(@RequestParam Integer counts){
        return noticeService.getNoticesTitleTop(counts);
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

    @PostMapping("notice")
    public R<Void> addNotice(@Valid @RequestBody NoticeAddDTO noticeAddDTO){
        return noticeService.addNotice(noticeAddDTO);
    }

}
