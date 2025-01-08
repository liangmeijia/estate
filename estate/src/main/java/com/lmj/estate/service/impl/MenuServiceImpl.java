package com.lmj.estate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.MenuDao;
import com.lmj.estate.entity.Menu;
import com.lmj.estate.service.MenuService;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

}
