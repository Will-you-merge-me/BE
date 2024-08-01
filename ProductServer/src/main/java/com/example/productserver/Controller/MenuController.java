package com.example.productserver.Controller;

import com.example.productserver.Dto.MenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto,
                                              @RequestPart(value = "image", required = false) MultipartFile image) {
        return null;
    }
}
