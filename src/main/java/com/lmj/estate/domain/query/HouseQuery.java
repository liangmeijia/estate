package com.lmj.estate.domain.query;

import lombok.Data;

/**
 * description 房屋复杂查询条件
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 11:03:47
 */
@Data
public class HouseQuery extends PageQuery{
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
}
