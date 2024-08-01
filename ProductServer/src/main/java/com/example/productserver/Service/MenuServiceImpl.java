package com.example.productserver.Service;

import com.example.productserver.Dto.MenuDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Override
    public MenuDto createMenu(MenuDto menuDto, MultipartFile image) {
        return null;
    }

    @Override
    public List<MenuDto> readByProductId(Long productId) {
        return List.of();
    }

    @Override
    public MenuDto readByMenuId(Long menuId) {
        return null;
    }

    @Override
    public MenuDto updateMenu(Long menuId, MenuDto menuDto) {
        return null;
    }

    @Override
    public void deleteMenu(Long menuId) {

    }
}
