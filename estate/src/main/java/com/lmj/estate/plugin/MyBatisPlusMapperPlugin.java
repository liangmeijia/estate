package com.lmj.estate.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;

import java.util.List;
/**
 * description 用于修改自动生成的 Mapper 接口文件，让其继承 BaseMapper<T>,并清空生成的默认方法
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 15:39:03
 */
public class MyBatisPlusMapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true; // 插件验证通过
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        // 清空生成的默认方法
        interfaze.getMethods().clear();

        // 获取实体类的全限定类名
        String modelName = introspectedTable.getBaseRecordType();
        // 添加 BaseMapper 泛型继承
        FullyQualifiedJavaType baseMapper = new FullyQualifiedJavaType("com.baomidou.mybatisplus.core.mapper.BaseMapper<" + modelName + ">");
        interfaze.addSuperInterface(baseMapper);

        // 导入 BaseMapper 和实体类
        interfaze.addImportedType(new FullyQualifiedJavaType("com.baomidou.mybatisplus.core.mapper.BaseMapper"));
        interfaze.addImportedType(new FullyQualifiedJavaType(modelName));

        return true; // 返回 true 表示成功生成文件
    }
}
