package com.lmj.estate.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
/**
 * description 给自动生成的实体类添加id主键自增方式的注解
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 20:55:16
 */
public class TableIdPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        // 插件配置校验
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass,
                                       IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
                                       ModelClassType modelClassType) {
        // 检查是否为主键字段
        if ("主键".equals(introspectedColumn.getRemarks())) {
            // 添加 @TableId 注解
            field.addAnnotation("@TableId(value = \"" + introspectedColumn.getActualColumnName() + "\", type = IdType.AUTO)");

            // 确保导入注解所需的类
            topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableId");
            topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.IdType");
        }
        return true;
    }
}

