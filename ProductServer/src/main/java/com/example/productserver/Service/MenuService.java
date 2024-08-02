package com.example.productserver.Service;

import com.example.productserver.Dto.MenuDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface MenuService {
    MenuDto createMenu(MenuDto menuDto, MultipartFile image) throws IOException;

    List<MenuDto> readByProductId(Long productId);

    MenuDto readByMenuId(Long menuId);

    MenuDto updateMenu(Long menuId, MenuDto menuDto);

    void deleteMenu(Long menuId);
}
