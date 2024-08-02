package com.example.productserver.Service;

import com.example.productserver.Dao.MenuDao;
import com.example.productserver.Dao.ProductDao;
import com.example.productserver.Dto.MenuDto;
import com.example.productserver.Entity.MenuEntity;
import com.example.productserver.Entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    private final MenuDao menuDao;
    private final ProductDao productDao;
    private final S3UploadUtil s3UploadUtil;

    public MenuServiceImpl(@Autowired MenuDao menuDao,
                           S3UploadUtil s3UploadUtil,
                           ProductDao productDao) {
        this.menuDao = menuDao;
        this.productDao = productDao;
        this.s3UploadUtil = s3UploadUtil;
    }

    @Override
    public MenuDto createMenu(MenuDto menuDto, MultipartFile image) throws IOException {

        ProductEntity productEntity = productDao.findById(menuDto.getProductId());
        String uploadUrl = null;

        if(!image.isEmpty()){
            File uploadFile = s3UploadUtil.convert(image) // MultipartFile 을 File 로 전환
                    .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File Convert Fail"));

            String PRODUCT_IMG_DIR = "menu";
            uploadUrl = s3UploadUtil.upload(uploadFile, PRODUCT_IMG_DIR);
        }

        MenuEntity menuEntity = MenuDto.dtoToEntity(menuDto,productEntity, uploadUrl);
        menuEntity = menuDao.createProduct(menuEntity);
        return MenuDto.entityToDto(menuEntity);
    }

    @Override
    public List<MenuDto> readByProductId(Long productId) {
        List<MenuEntity> list = menuDao.findByProductId(productId);
        return list.stream().
                map(MenuDto::entityToDto).
                collect(Collectors.toList());
    }

    @Override
    public MenuDto readByMenuId(Long menuId) {
        MenuEntity menuEntity = menuDao.findById(menuId);
        return MenuDto.entityToDto(menuEntity);
    }

    @Override
    public MenuDto updateMenu(Long menuId, MenuDto menuDto) {
        return null;
    }

    @Override
    public void deleteMenu(Long menuId) {
        menuDao.deleteMenu(menuId);
    }
}
