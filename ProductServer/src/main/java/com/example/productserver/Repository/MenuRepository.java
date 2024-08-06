package com.example.productserver.Repository;

import com.example.productserver.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {
    List<MenuEntity> findByProductEntityId(Long productId);
}
