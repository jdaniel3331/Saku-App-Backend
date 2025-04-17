package com.jdaniel.sakuappapi.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "users_credentials", schema = "authentication")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCredential {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "was_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean wasVerified;
    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
