package com.lmj.estate.plugin;


import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;

import java.util.Iterator;
import java.util.List;
/**
 * description 为自动生成的mapper.xml文件，删除默认的方法
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 16:06:21
 */
public class RemoveDefaultXmlMethodsPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true; // 插件验证通过
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        // 获取根节点的所有子元素
        List<VisitableElement> elements = document.getRootElement().getElements();
        elements.clear();
        return true; // 继续生成过程
    }

}

