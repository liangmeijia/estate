package com.lmj.estate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class House implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 地址
     */
    private String address;

    /**
     * 栋
     */
    private String building;

    /**
     * 单元
     */
    private String unit;

    /**
     * 门牌号
     */
    private String number;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 房价
     */
    private BigDecimal price;

    /**
     * 户主id
     */
    private Integer userId;

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

    private static final long serialVersionUID = 1L;
}