package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.MenuDao;
import com.lmj.estate.dao.UserDao;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.Menu;
import com.lmj.estate.entity.User;
import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserStatus;
import com.lmj.estate.service.UserService;
import com.lmj.estate.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.lmj.estate.domain.constant.ManageConst.DEFAULT_EXPIRED_SECONDS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    private final MenuDao menuDao;
    @Override
    public void deductionAge(long id, int age) {
        //1.用户是否存在
        User user = this.baseMapper.selectById(id);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        //2.年龄是否够减
        if(user.getAge()<age){
            throw new RuntimeException("年龄不够减");
        }
        //3.减年龄
        baseMapper.deductionAge(id,age);
    }

    @Override
    public PageDTO<UserVO> findUsersPage(UserQuery userQuery) {

        //1.构建分页条件
        Page<User> page = userQuery.toMpPageDefaultByAge();
        //2.构建查询条件
        String name = userQuery.getName();
        Integer age = userQuery.getAge();
        UserStatus status = userQuery.getStatus();
        UserRole roleId = userQuery.getRoleId();
        lambdaQuery().like(name != null, User::getName, name)
                .ge(age != null, User::getAge, age)
                .eq(status != null, User::getStatus, status)
                .eq(roleId!=null,User::getRoleId,roleId)
                .page(page);
        //4.得到结果
        return PageDTO.of(page, user -> {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            userVO.setPassword(userVO.getPassword().substring(0,userVO.getPassword().length()-4)+"**");
            return userVO;
        });
    }

    @Override
    public UserLoginVO login(String userName, String password) {
        //0.验证数据

        //1.根据电话和姓名查用户
        LambdaQueryWrapper<User> userLQW = new LambdaQueryWrapper();
        userLQW.eq(User::getName,userName);
        userLQW.eq(User::getPassword,password);
        User user = this.baseMapper.selectOne(userLQW);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在！");
        }
        log.debug("用户:"+userName+" 登录信息认证成功");
        //2.用户登录成功，生成JWT令牌并返回给客户端
        String jwtToken = JwtUtils.generateJwtToken(user.getId().toString(), DEFAULT_EXPIRED_SECONDS); // 令牌有效期10分钟
        log.debug("JWT Token: " + jwtToken);
        //3.结果封装
        UserLoginVO vo = BeanUtil.copyProperties(user, UserLoginVO.class);
        vo.setToken(jwtToken);
        LambdaQueryWrapper<Menu> menuLQW = new LambdaQueryWrapper();
        menuLQW.like(Menu::getMenuRight, user.getRoleId().getValue());
        List<Menu> menus = menuDao.selectList(menuLQW);
        vo.setMenus(menus);
        return vo;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setCreateTime(LocalDateTime.now());
        baseMapper.insert(user);
    }

    @Override
    public UserVO findUserById(long id) {
        User user = baseMapper.selectById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateTime(LocalDateTime.now());
        LambdaQueryWrapper<User> userLQW = new LambdaQueryWrapper();
        userLQW.eq(User::getId,user.getId());
        baseMapper.update(user,userLQW);
    }

}
