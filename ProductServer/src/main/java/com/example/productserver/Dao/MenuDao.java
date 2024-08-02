package com.example.productserver.Dao;

import com.example.productserver.Entity.MenuEntity;

import java.util.List;

public interface MenuDao{

    MenuEntity createProduct(MenuEntity menuEntity);

    List<MenuEntity> findByProductId(Long productId);

    MenuEntity findById(Long menuId);

    void deleteMenu(Long menuId);
}
