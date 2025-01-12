package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.BillDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.BillService;
import com.lmj.estate.service.UserService;
import com.lmj.estate.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description 物业费用管理
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/10 22:37:41
 */
@RestController
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    /**
     * 复杂条件【分页】查询账单
     * @param billQuery 查询条件
     * @return 个人账单列表
     */
    @PostMapping("/bills")
    public R<PageDTO<BillVO>> getBillsById(@RequestBody BillQuery billQuery){
        return R.ok(billService.getBills(billQuery));
    }

    /**
     * 添加账单
     * @param billDTO
     * @return
     */
    @PostMapping("/bill")
    public R<Void> addBill(@RequestBody BillDTO billDTO){
        return billService.addBill(billDTO);
    }

    /**
     * 修改账单
     * @param billDTO
     * @return
     */
    @PutMapping("/bill")
    public R<Void> updateBill(@RequestBody BillDTO billDTO){
        return billService.updateBill(billDTO);
    }

    /**
     * 删除账单
     * @param id 账单id
     * @return
     */
    @DeleteMapping("/bill/{id}")
    public R<Void> deleteBill(@PathVariable long id){
        billService.removeById(id);
        return R.ok();
    }

}
