package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.BilUpdateDTO;
import com.lmj.estate.domain.DTO.BillAddDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.VO.BillVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.BillQuery;
import com.lmj.estate.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @description 物业费用管理
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
     * @return 账单列表
     */
    @PostMapping("/bills")
    public R<PageDTO<BillVO>> getBillsById(@RequestBody BillQuery billQuery){
        return R.ok(billService.getBills(billQuery));
    }

    /**
     * 添加账单
     * @param billDTO 账单信息
     * @return 是否成功
     */
    @PostMapping("/bill")
    public R<Void> addBill(@RequestBody BillAddDTO billDTO){
        return billService.addBill(billDTO);
    }

    /**
     * 修改账单
     * @param billDTO 修改的账单信息
     * @return 是否成功
     */
    @PutMapping("/bill")
    public R<Void> updateBill(@RequestBody BilUpdateDTO billDTO){
        return billService.updateBill(billDTO);
    }

    /**
     * 删除账单
     * @param id 账单id
     * @return 账单信息
     */
    @DeleteMapping("/bill/{id}")
    public R<Void> deleteBill(@PathVariable long id){
        if(billService.removeById(id)){
            return R.ok();
        }else {
            return R.no();
        }
    }

    /**
     * 账单缴费
     * @param userId 缴费人id
     * @param id 账单id
     * @return 是否成功,如果成功就返回缴费后的余额
     */
    @PostMapping("/bill/payment")
    public R<Double> billPayment(@RequestParam Long userId,@RequestParam Long id){
        return billService.billPayment(userId,id);
    }


}
