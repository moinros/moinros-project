package com.moinros.project.model.pojo.system;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/18 16:21
 * @Verison 1.0
 */
public class MenuSon {

    private Menu menu;

    private List<MenuSon> son;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<MenuSon> getSon() {
        return son;
    }

    public void setSon(List<MenuSon> son) {
        this.son = son;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        if (son != null) {
            for (MenuSon menuSon : son) {
                sb.append(menuSon.toString());
            }
            sb.append("\n");
        }
        return "MenuSon{" + "menu=" + menu + ", son=" + sb.toString() + '}';
    }
}
