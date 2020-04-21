package com.wxx.springbootvue.system.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 她爱微笑
 * @date 2020/3/26
 */
public class MenuVO implements Serializable {

    private String name;

    private String path;

    private String component;

    private MenuMetaVO meta;

    private List<MenuVO> children;


    @Override
    public String toString() {
        return "MenuVO{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public MenuMetaVO getMeta() {
        return meta;
    }

    public void setMeta(MenuMetaVO meta) {
        this.meta = meta;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }
}
