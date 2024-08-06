package com.example.productserver.Service;

import com.example.productserver.Dto.ReviewFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-server", path = "/review")
public interface ReviewFeignClient {
    @GetMapping("/feign/{productId}")
    ReviewFeignDto avgStarByProductId(@PathVariable Long productId);
}
