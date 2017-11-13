package com.umkc.medspace.api.objects;

public class Menu {
    private Integer menuId;
    private String menuTitle;
    private Integer menuImage;

    public Menu(int i, String s, int image) {
        this.menuId = i;
        this.menuTitle = s;
        this.menuImage = image;
    }

    public Integer getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Integer menuImage) {
        this.menuImage = menuImage;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}
