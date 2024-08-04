package com.springboot.userserver.data.entity;

import com.springboot.userserver.data.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uid;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String address;
    private String imageURL;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<CertificationEntity> certificationEntities;
}
