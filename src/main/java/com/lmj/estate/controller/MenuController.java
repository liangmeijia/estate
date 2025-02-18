package com.lmj.estate.controller;

import com.lmj.estate.entity.Menu;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    /**
     * 获取菜单
     * @param roleId 角色id
     * @return 角色对应的菜单
     */
    @GetMapping(value = "/list/{roleId}")
    public R<Object> list(@PathVariable String roleId){
        return R.ok(menuService.lambdaQuery().like(Menu::getMenuRight, roleId).list());
    }
}
