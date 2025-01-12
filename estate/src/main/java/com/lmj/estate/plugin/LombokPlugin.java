package com.lmj.estate.plugin;

import com.baomidou.mybatisplus.annotation.TableName;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.Field;
import java.util.List;


/**
 * description 用于修改自动生成的 entity 实体类，让其添加@Data和@TableName注解;删除get/set方法;并添加@TableLogic注解
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
        // 1.添加 @Data 注解
        FullyQualifiedJavaType dataAnnotation = new FullyQualifiedJavaType("lombok.Data");
        topLevelClass.addImportedType(dataAnnotation);
        topLevelClass.addAnnotation("@Data");

        //2.添加@TableName 注解
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        topLevelClass.addAnnotation("@TableName(value = \""+tableName+"\",autoResultMap = true)");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.baomidou.mybatisplus.annotation.TableName"));

        //3.移除生成的 getter 和 setter 方法
        topLevelClass.getMethods().clear();

        // 4.遍历所有字段，找到逻辑删除字段并添加 @TableLogic 注解
        for (Field field : topLevelClass.getFields()) {
            if ("deleteFlag".equalsIgnoreCase(field.getName())) {
                // 在 delete_flag 字段上添加 @TableLogic 注解
                field.addAnnotation("@TableLogic");
                // 添加 @TableLogic 注解的导入
                topLevelClass.addImportedType(new FullyQualifiedJavaType("com.baomidou.mybatisplus.annotation.TableLogic"));
            }
        }
        return true;
    }
}

