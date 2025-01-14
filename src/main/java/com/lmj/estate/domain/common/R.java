package com.lmj.estate.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private static final Long CODE_SUCCESS = 200L;
    private static final Long CODE_ERR_BUSINESS = 500L;

    private Boolean OK;
    private Long code;
    private String msg;
    private T data;
    public static <T> R<T> oK(String msg,T data) {
        return new R<>(true,CODE_SUCCESS,msg,data);

    }
    public static <T> R<T> no(String msg,T data) {
        return new R<>(false,CODE_ERR_BUSINESS,msg,data);

    }
    /**
     * 成功带消息不带数据
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> R<T> ok(String msg) {
        return new R<>(true,CODE_SUCCESS,msg,null);

    }

    /**
     * 失败带消息不带数据
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> R<T> no(String msg) {
        return new R<>(false,CODE_ERR_BUSINESS,msg,null);

    }
    /**
     * 成功并不带数据
     * @return
     * @param <T>
     */
    public static <T> R<T> ok() {
        return new R<>(true,CODE_SUCCESS,"成功",null);

    }

    /**
     * 失败且不带数据
     * @return
     * @param <T>
     */
    public static <T> R<T> no() {
        return new R<>(false,CODE_ERR_BUSINESS,"失败",null);
    }
    /**
     * 成功并带数据
     * @param data
     * @return
     * @param <T>
     */
    public static <T> R<T> ok(T data) {
        return new R<>(true,CODE_SUCCESS,"成功",data);

    }

    /**
     * 失败并带数据
     * @param data
     * @return
     * @param <T>
     */
    public static <T> R<T> no(T data) {
        return new R<>(false,CODE_ERR_BUSINESS,"失败",data);
    }

    /**
     *
     */

}