package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "height")
    private BigDecimal height;

    @Column(name = "avatar")
    private byte[] avatar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Profile(String bio, LocalDate birthDate, BigDecimal height) {
        this.bio = bio;
        this.birthDate = birthDate;
        this.height = height;
        this.createAt = LocalDateTime.now();
    }
}
