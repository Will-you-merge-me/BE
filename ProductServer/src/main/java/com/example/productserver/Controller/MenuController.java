package com.example.productserver.Controller;

import com.example.productserver.Dto.MenuDto;
import com.example.productserver.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(@Autowired MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto,
                                              @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        MenuDto create = menuService.createMenu(menuDto, image);
        return ResponseEntity.ok().body(create);
    }

    @GetMapping("/productNum/{productId}")
    public ResponseEntity<List<MenuDto>> readByProductId(@PathVariable Long productId){
        List<MenuDto> menuList = menuService.readByProductId(productId);
        return ResponseEntity.ok().body(menuList);
    }

    @GetMapping("/menuNum/{menuId}")
    public ResponseEntity<MenuDto> readByMenuId(@PathVariable Long menuId){
        MenuDto menuDto = menuService.readByMenuId(menuId);
        return ResponseEntity.ok().body(menuDto);
    }

    @PatchMapping("/update/{menuId}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long menuId,
                                              @RequestBody MenuDto menuDto){
        MenuDto update = menuService.updateMenu(menuId, menuDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/delete/{menuId}")
    public void deleteMenu(@PathVariable Long menuId){
        menuService.deleteMenu(menuId);
    }
}
