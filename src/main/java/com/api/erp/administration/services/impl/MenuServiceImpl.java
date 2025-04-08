package com.api.erp.administration.services.impl;

import com.api.erp.administration.dto.MenuRequest;
import com.api.erp.administration.entity.Menu;
import com.api.erp.administration.repository.MenuRepository;
import com.api.erp.administration.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> getMenuTree() {
        return menuRepository.findByParentIsNull(); // Top level, each has children loaded
    }

    public Menu createMenu(MenuRequest request) {
        Menu menu = new Menu();
        menu.setName(request.getName());

        if (request.getParentId() != null) {
            Menu parent = menuRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent menu not found"));
            menu.setParent(parent);
        }

        return menuRepository.save(menu);
    }

}

