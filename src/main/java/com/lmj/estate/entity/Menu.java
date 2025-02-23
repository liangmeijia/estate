package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String menuCode;
    private String menuName;
    private String menuLevel;
    private String menuParentCode;
    private String menuClick;
    private String menuRight;
    private String menuComponent;
    private String menuIcon;
    @TableLogic
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
