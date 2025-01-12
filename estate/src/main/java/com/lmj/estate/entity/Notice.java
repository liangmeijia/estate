package com.lmj.estate.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Notice implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 逻辑删除
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 公告内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}