package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ProcessRepairDTO;
import com.lmj.estate.domain.DTO.RepairAddDTO;
import com.lmj.estate.domain.VO.RepairDetailVO;
import com.lmj.estate.domain.VO.RepairVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.RepairQuery;
import com.lmj.estate.service.RepairsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lmj
 * @version 1.0
 * @description 维修服务
 * @date 2025/01/20 15:19:59
 */
@RestController
@RequiredArgsConstructor
public class RepairsController {
    private final RepairsService repairsService;

    /**
     * 提交维修申请
     * @param repairsAddDTO 维修申请
     * @return 是否成功
     */
    @PostMapping("repair")
    public R<Void> addRepairs(@Valid @RequestBody RepairAddDTO repairsAddDTO){
         return repairsService.addRepair(repairsAddDTO);
    }

    /**
     * 处理维修申请
     * @param processRepairsDTO 处理维修申请的条件
     * @return 是否成功
     */
    @PutMapping("repair")
    public R<Void> processRepairs(@Valid @RequestBody ProcessRepairDTO processRepairsDTO){
        return repairsService.processRepair(processRepairsDTO);
    }

    /**
     * 分页查询维修申请
     * @param repairQuery 查询条件
     * @return 查询结果
     */
    @PostMapping("repairs")
    public R<PageDTO<RepairVO>> getRepairs(@Valid @RequestBody RepairQuery repairQuery){
        return R.ok(repairsService.getRepairs(repairQuery));
    }

    /**
     * 获取维修申请详情
     * @param id 维修申请id
     * @return 维修申请详情
     */
    @GetMapping("/repair")
    public R<RepairDetailVO> getRepairDetail(@RequestParam Long id){
        return repairsService.getRepairDetail(id);
    }


}
