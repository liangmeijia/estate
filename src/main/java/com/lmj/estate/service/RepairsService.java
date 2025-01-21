package com.lmj.estate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.ProcessRepairDTO;
import com.lmj.estate.domain.DTO.RepairAddDTO;
import com.lmj.estate.domain.DTO.RepairUpdateDTO;
import com.lmj.estate.domain.VO.RepairDetailVO;
import com.lmj.estate.domain.VO.RepairVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.RepairQuery;
import com.lmj.estate.entity.Repairs;

/**
 * @author lmj
 * @version 1.0
 * @description 维修服务
 * @date 2025/01/20 15:20:57
 */
public interface RepairsService extends IService<Repairs> {
    R<Void> addRepair(RepairAddDTO repairsAddDTO);

    R<Void> processRepair(ProcessRepairDTO processRepairsDTO);

    PageDTO<RepairVO> getRepairs(RepairQuery repairQuery);

    R<RepairDetailVO> getRepairDetail(Long id);

    R<Void> updateRepair(RepairUpdateDTO repairUpdateDTO);

}
