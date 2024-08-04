package com.springboot.userserver.data.dto;


import com.springboot.userserver.data.entity.CertificationEntity;
import com.springboot.userserver.data.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CertificationDto {
    private String documentUrl;

    public static CertificationEntity dtoToEntity(String documentUrl, UserEntity userEntity) {
        return CertificationEntity.builder()
                .documentUrl(documentUrl)
                .user(userEntity)
                .build();
    }


}
