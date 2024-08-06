package com.example.productserver.Dao;

import com.example.productserver.Entity.MenuEntity;
import com.example.productserver.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDaoImpl implements MenuDao{

    private final MenuRepository menuRepository;

    public MenuDaoImpl(@Autowired MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuEntity createProduct(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }

    @Override
    public List<MenuEntity> findByProductId(Long productId) {
        return menuRepository.findByProductEntityId(productId);
    }

    @Override
    public MenuEntity findById(Long menuId) {
        return menuRepository.findById(menuId).
                orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
    }

    @Override
    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }
}
