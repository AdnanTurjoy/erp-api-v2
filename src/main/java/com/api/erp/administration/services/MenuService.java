package com.api.erp.administration.services;

import com.api.erp.administration.dto.MenuRequest;
import com.api.erp.administration.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuTree();

    Menu createMenu(MenuRequest request);
}
