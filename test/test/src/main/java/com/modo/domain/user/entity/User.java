package com.modo.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User
{
    @Id // auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    private Long id; // 식별자

    @Column(unique = true, nullable = false) // 유니크 : 유일성 , nullable = false : 널 값 허용 안함
    private String email;
}
