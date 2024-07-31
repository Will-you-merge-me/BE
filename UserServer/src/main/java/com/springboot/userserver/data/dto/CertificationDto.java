package com.springboot.userserver.data.dto;


import com.springboot.userserver.data.entity.CertificationEntity;
import com.springboot.userserver.data.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CertificationDto {
    private String document;

    public static CertificationEntity dtoToEntity(CertificationDto certificationDto, UserEntity userEntity) {
        return CertificationEntity.builder()
                .document(certificationDto.getDocument())
                .user(userEntity)
                .build();
    }


}
