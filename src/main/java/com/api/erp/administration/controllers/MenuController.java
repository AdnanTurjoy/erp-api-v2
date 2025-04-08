package com.api.erp.administration.controllers;

import com.api.erp.administration.dto.MenuRequest;
import com.api.erp.administration.entity.Menu;
import com.api.erp.administration.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@AllArgsConstructor
public class MenuController {

    @Autowired
    private final MenuService menuService;

    @GetMapping
    public List<Menu> getMenuTree() {
        return menuService.getMenuTree();
    }

    @PostMapping
    public Menu createMenu(@RequestBody MenuRequest request) {
        return menuService.createMenu(request);
    }
}

