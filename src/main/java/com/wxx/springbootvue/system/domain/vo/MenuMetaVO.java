package com.wxx.springbootvue.system.domain.vo;

/**
 * @author 她爱微笑
 * @date 2020/3/26
 */
public class MenuMetaVO {

    private String title;

    private String icon;

    public MenuMetaVO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MenuMetaVO{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
