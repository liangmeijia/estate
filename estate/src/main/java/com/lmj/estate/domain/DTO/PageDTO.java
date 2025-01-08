package com.lmj.estate.domain.DTO;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageDTO<T> {
    //总数量
    private Long total;
    //总页数
    private Long pages;
    //记录
    private List<T> records;

    public static <P,D> PageDTO<D> of(Page<P> p,Class<D> clazz){
        PageDTO<D> pageDTO = new PageDTO<>();
        pageDTO.setTotal(p.getTotal());
        pageDTO.setPages(p.getPages());
        List<P> records = p.getRecords();

        if(CollectionUtils.isEmpty(records)){
            pageDTO.setRecords(Collections.emptyList());
            return pageDTO;
        }
        pageDTO.setRecords(BeanUtil.copyToList(records,clazz));
        return pageDTO;
    }

    /**
     * 将MP的Page<P>对象转换为PageDTO<D>
     * @param p 利用MP查询出的结果
     * @param convertor 一个函数式接口，表示一种行为/函数，即将P转换为D的一种行为
     * @return PageDTO<D>
     * @param <P> 要查询的实体类型
     * @param <D> 要返回的实体类型
     */
    public static <P,D> PageDTO<D> of(Page<P> p, Function<P,D> convertor){
        PageDTO<D> pageDTO = new PageDTO<>();
        pageDTO.setTotal(p.getTotal());
        pageDTO.setPages(p.getPages());
        List<P> records = p.getRecords();

        if(CollectionUtils.isEmpty(records)){
            pageDTO.setRecords(Collections.emptyList());
            return pageDTO;
        }
        pageDTO.setRecords(records.stream().map(convertor).collect(Collectors.toList()));
        return pageDTO;
    }
}
