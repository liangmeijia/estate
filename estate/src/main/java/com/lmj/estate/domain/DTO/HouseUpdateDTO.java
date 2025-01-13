package com.lmj.estate.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * description 修改房屋
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 10:24:27
 */
@Data
public class HouseUpdateDTO {
    @NotNull(message = "id不能为空")
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
     * 户主姓名(可选填)
     */
    private String name;
}
