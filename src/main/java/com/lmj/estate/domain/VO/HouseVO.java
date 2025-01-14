package com.lmj.estate.domain.VO;

import lombok.Data;

/**
 * description 房产分页查询结果
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 11:06:07
 */
@Data
public class HouseVO {
    /**
     * 主键
     */
    private Long id;

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
    private Double area;

    /**
     * 房价
     */
    private Double price;

    /**
     * 户主姓名
     */
    private String name;
}
