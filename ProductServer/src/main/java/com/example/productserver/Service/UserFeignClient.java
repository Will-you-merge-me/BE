package com.example.productserver.Service;

import com.example.productserver.Dto.UserFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-server", path = "/user")
public interface UserFeignClient {
    @GetMapping("/feign/{userId}")
    UserFeignDto findById(@PathVariable Long userId);
}
