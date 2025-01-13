package com.lmj.estate.domain.DTO;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;


/**
 * description 添加房屋
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/13 10:24:27
 */
@Data
public class HouseAddDTO {
    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 栋
     */
    @NotBlank(message = "栋不能为空")
    private String building;

    /**
     * 单元
     */
    @NotBlank(message = "单元不能为空")
    private String unit;

    /**
     * 门牌号
     */
    @NotBlank(message = "门牌号不能为空")
    private String number;

    /**
     * 面积
     */
    @NotBlank(message = "面积不能为空")
    private Double area;

    /**
     * 房价
     */
    @NotBlank(message = "房价不能为空")
    private Double price;

    /**
     * 户主姓名(可选填)
     */
    private String name;
}
