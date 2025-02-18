package com.lmj.estate.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.estate.dao.BalanceRecordsMapper;
import com.lmj.estate.dao.MenuMapper;
import com.lmj.estate.dao.UserMapper;
import com.lmj.estate.domain.DTO.*;
import com.lmj.estate.domain.VO.BalanceRecordVO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.*;
import com.lmj.estate.domain.query.BalanceRecordQuery;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.*;
import com.lmj.estate.service.UserService;
import com.lmj.estate.utils.JwtUtils;
import com.lmj.estate.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;



import static com.lmj.estate.domain.constant.ManageConst.DEFAULT_EXPIRED_SECONDS;
import static com.lmj.estate.domain.constant.ManageConst.INIT_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final MenuMapper menuMapper;
    private final BalanceRecordsMapper balanceRecordsMapper;
    private final TransactionTemplate transactionTemplate;
    private final String[] headers = {"姓名","密码","年龄","性别","电话","邮件","余额","角色","状态"};

    @Override
    public R<String> increaseBalance(BalancePaymentDTO balancePaymentDTO) {
        Long id = balancePaymentDTO.getUserId();
        BalancePaymentMethod method= balancePaymentDTO.getMethod();
        Double balance = balancePaymentDTO.getBalance();

        if(balance<=0.0){
            return R.no("请输入正确的充值金额");
        }
        //生成充值记录
        BalanceRecords balanceRecords = new BalanceRecords();
        balanceRecords.setAmount(balance);
        balanceRecords.setUserId(id);
        LocalDateTime now = LocalDateTime.now();
        balanceRecords.setDate(now);
        balanceRecords.setMethod(method);
        balanceRecords.setCreateTime(now);
        //编程式事务
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    baseMapper.increaseBalance(id, balance);
                    // 添加成功充值的记录
                    balanceRecords.setStatus(BalancePaymentStatus.PAYMENT_SUCCESS);
                    balanceRecordsMapper.insert(balanceRecords);
                } catch (Exception e) {
                    // 回滚事务
                    status.setRollbackOnly();
                    // 添加失败充值的记录
                    balanceRecords.setStatus(BalancePaymentStatus.PAYMENT_FAILED);
                    balanceRecordsMapper.insert(balanceRecords);
                }
            }
        });
        return R.ok("充值成功");
    }
    @Override
    public PageDTO<UserVO> findUsersPage(Long pageNum, Long pageSize, String name, UserStatus status, UserRole roleId) {
        //1.构建分页条件
        Page<User> page = Page.of(pageNum, pageSize);
        //2.构建查询条件
        lambdaQuery().like(name != null, User::getName, name)
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
    public PageDTO<UserVO> findUsersPage(UserQuery userQuery){
        //1.构建分页条件
        Page<User> page = userQuery.toMpPageDefaultByCreateTime();
        //2.构建查询条件
        String name = userQuery.getName();
        UserStatus status = userQuery.getStatus();
        UserRole roleId = userQuery.getRoleId();
        lambdaQuery().like(name != null, User::getName, name)
                .eq(status != null, User::getStatus, status)
                .eq(roleId!=null,User::getRoleId,roleId)
                .page(page);
        //4.得到结果
        return PageDTO.of(page, user -> {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            userVO.setPassword(userVO.getPassword().substring(0,6)+"**");
            return userVO;
        });
    }
    @Override
    public R<UserLoginVO> login(String userName, String password) {
        //0.验证数据
        if(StrUtil.isEmptyIfStr(userName)){
            return R.no("用户名不能为空");
        }
        if(StrUtil.isEmptyIfStr(password)){
            return R.no("密码不能为空");
        }
        //1.
        User user = baseMapper.selectOneByName(userName);
        if(StrUtil.isEmptyIfStr(user)){
            return R.no("用户不存在");
        }else if(!PasswordUtil.checkPassword(password, user.getPassword())){
            return R.no("密码错误");
        }else {
            //登录成功
            log.debug("用户:"+userName+" 登录信息认证成功");
            //2.用户登录成功，生成JWT令牌并返回给客户端
            String jwtToken = JwtUtils.generateJwtToken(user.getId().toString(), DEFAULT_EXPIRED_SECONDS); // 令牌有效期10分钟
            log.debug("JWT Token: " + jwtToken);
            //3.结果封装
            UserLoginVO vo = BeanUtil.copyProperties(user, UserLoginVO.class);
            vo.setPassword(vo.getPassword().substring(0,6)+"**");
            vo.setToken(jwtToken);
            LambdaQueryWrapper<Menu> menuLQW = new LambdaQueryWrapper();
            menuLQW.like(Menu::getMenuRight, user.getRoleId().getValue());
            List<Menu> menus = menuMapper.selectList(menuLQW);
            vo.setMenus(menus);
            return R.ok(vo);
        }
    }

    @Override
    public R<Void> addUser(UserAddDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO,User.class);
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(PasswordUtil.hashPassword(INIT_PASSWORD));
        baseMapper.insert(user);
        return R.ok();

    }

    @Override
    public UserVO findUserVOById(long id) {
        User user = baseMapper.selectById(id);
        UserVO userVO = BeanUtil.copyProperties(user,UserVO.class);
        userVO.setPassword(userVO.getPassword().substring(0,6)+"**");
        return userVO;
    }

    @Override
    public R<Void> updateUser(UserUpdateDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO,User.class);
        user.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(user);
        return R.ok();

    }

    @Override
    public R<String> importUsers(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        // 1.验证表头信息
        if (rows.hasNext()) {
            Row row = rows.next();
            if(row.getLastCellNum()!=9){
                return R.no("文档标题个数不符合模板要求");
            }
            for(int i =0;i<headers.length;i++){
                if(!headers[i].equals(row.getCell(i).getStringCellValue())){
                    return R.no("文档标题不符合模板要求");
                }
            }
        }else {
            return R.no("文档为空");
        }
        //2.保存用户信息
        List<User> users = new ArrayList<>();
        while (rows.hasNext()) {
            Row row = rows.next();
            User user = new User();
            if(row.getLastCellNum()!=9){
                return R.no("用户信息个数错误");
            }
            user.setName(row.getCell(0).getStringCellValue());
            row.getCell(1).setCellType(CellType.STRING); // 密码强制设置为字符串类
            user.setPassword(PasswordUtil.hashPassword(row.getCell(1).getStringCellValue()));
            user.setAge((int) row.getCell(2).getNumericCellValue());
            user.setSex(UserSex.fromDesc(row.getCell(3).getStringCellValue()));
            row.getCell(4).setCellType(CellType.STRING); // 电话强制设置为字符串类
            user.setPhone(row.getCell(4).getStringCellValue());
            user.setEmail(row.getCell(5).getStringCellValue());
            user.setBalance(row.getCell(6).getNumericCellValue());
            user.setRoleId(UserRole.fromDesc(row.getCell(7).getStringCellValue()));
            user.setStatus(UserStatus.fromDesc(row.getCell(8).getStringCellValue()));
            user.setCreateTime(LocalDateTime.now());
            users.add(user);
        }
        // 将所有用户信息保存到数据库
        baseMapper.batchInsert(users);
        // 关闭资源
        workbook.close();
        return R.ok();
    }
    public void exportUsers(UserQuery userQuery, HttpServletResponse  response) throws Exception{
        // 0.查询当前页用户信息
        List<UserVO> userVOS = findUsersPage(userQuery).getRecords();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");
        // 1.创建表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        // 2.填充数据
        int rowIndex = 1;
        for (UserVO user : userVOS) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(user.getName());
            row.createCell(1).setCellValue(user.getPassword());
            row.createCell(2).setCellValue(user.getAge());
            row.createCell(3).setCellValue(user.getSex().getDesc());
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getEmail());
            row.createCell(6).setCellValue(user.getBalance());
            row.createCell(7).setCellValue(user.getRoleId().getDesc());
            row.createCell(8).setCellValue(user.getStatus().getDesc());
        }

        // 3.自动调整列宽
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        // 4.设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",  "attachment; filename=users.xlsx");

        // 5.将工作簿写入响应流
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    @Override
    public PageDTO<BalanceRecordVO> getBalanceRecords(BalanceRecordQuery balanceRecordQuery) {
        //构建分页条件
        Page<BalanceRecords> page = balanceRecordQuery.toMpPageDefaultByCreateTime();
        //构建查询条件
        LocalDateTime startTime = balanceRecordQuery.getStartTime();
        LocalDateTime endTime = balanceRecordQuery.getEndTime();
        Long curUserId = balanceRecordQuery.getCurUserId();
        User curUser = baseMapper.selectById(curUserId);
        LambdaQueryWrapper<BalanceRecords> LQW = new LambdaQueryWrapper<>();
        LQW.ge(startTime != null,BalanceRecords::getDate, startTime)
                .le(endTime != null,BalanceRecords::getDate, endTime)
                .eq((curUser!=null)&&(curUser.getRoleId()==UserRole.USER),BalanceRecords::getUserId,curUserId);;
        balanceRecordsMapper.selectPage(page, LQW);
        //返回分页结果
        return PageDTO.of(page,balanceRecords -> {
            BalanceRecordVO balanceRecordVO = BeanUtil.copyProperties(balanceRecords, BalanceRecordVO.class);
            User user = baseMapper.selectById(balanceRecords.getUserId());
            balanceRecordVO.setName(user.getName());
            return balanceRecordVO;
        });

    }

    @Override
    public R<Void> register(UserRegisterDTO userRegisterDTO) {
        //0.
        String name = userRegisterDTO.getName();
        User user = baseMapper.selectOneByName(name);
        if(!StrUtil.isEmptyIfStr(user)){
            return R.no("用户已存在");
        }
        //1.密码加密
        userRegisterDTO.setPassword(PasswordUtil.hashPassword(userRegisterDTO.getPassword()));
        user = BeanUtil.copyProperties(userRegisterDTO, User.class);
        user.setRoleId(UserRole.USER);
        user.setStatus(UserStatus.NORMAL);
        user.setCreateTime(LocalDateTime.now());
        //2.
        baseMapper.insert(user);
        return R.ok("注册成功");

    }

    @Override
    public R<Void> resetPassword(Long id) {
        User user = baseMapper.selectById(id);
        if(StrUtil.isEmptyIfStr(user)){
            return R.no("无此用户");
        }
        //初始化密码
        user.setPassword(PasswordUtil.hashPassword(INIT_PASSWORD));
        baseMapper.updateById(user);
        return R.ok("初始化密码： "+INIT_PASSWORD);
    }

    @Override
    public R<Void> updatePassword(UpdatePasswordDTO updatePasswordDTO){
        User user = baseMapper.selectById(updatePasswordDTO.getUserId());
        if(StrUtil.isEmptyIfStr(user)){
            return R.no("无此用户");
        }

        if(!PasswordUtil.checkPassword(updatePasswordDTO.getPassword(), user.getPassword())){
            return R.no("原密码错误");
        }

        if(!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmNewPassword())){
            return R.no("确认密码错误");
        }
        user.setPassword(PasswordUtil.hashPassword(updatePasswordDTO.getNewPassword()));

        baseMapper.updateById(user);
        return R.ok("修改密码成功");
    }
}
