package com.lmj.estate.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
/**
 * description 用于修改自动生成的 entity 实体类，让其添加@Data注解，并删除get/set方法
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 15:28:42
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 添加 @Data 注解
        FullyQualifiedJavaType dataAnnotation = new FullyQualifiedJavaType("lombok.Data");
        topLevelClass.addImportedType(dataAnnotation);
        topLevelClass.addAnnotation("@Data");

        // 移除生成的 getter 和 setter 方法
        topLevelClass.getMethods().clear();

        return true;
    }
}

