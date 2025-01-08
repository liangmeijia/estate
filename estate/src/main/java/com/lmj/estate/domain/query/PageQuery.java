package com.lmj.estate.domain.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class PageQuery {
    //当前页数
    private Long pageNum = 1L;
    //分页大小
    private Long pageSize = 5L;
    //排序字段
    private String column;
    //是否升序
    private Boolean asc = true;

    /**
     * 生成MP的Page<E>对象
     * @param orderItem 排序参数
     * @return  MP的Page对象
     * @param <E> 实体类型
     */
    public <E> Page<E> toMpPage(OrderItem ...orderItem){
        //1.构建分页条件
        //1.1分页条件
        Page<E> page = Page.of(pageNum, pageSize);
        //1.2排序条件
        if(StringUtils.hasLength(column)){
            page.addOrder(new OrderItem(column,asc));
        }else {
            //默认排序条件
            page.addOrder(orderItem);
        }
        return page;
    }
    public <E>Page<E> toMpPageDefault(String defaultColumn,Boolean defaultAsc){
        return toMpPage(new OrderItem(defaultColumn,defaultAsc));
    }
    public <E>Page<E> toMpPageDefaultByAge(){
        return toMpPage(new OrderItem("age",true));
    }
}
