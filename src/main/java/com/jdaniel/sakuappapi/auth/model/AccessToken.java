package com.jdaniel.sakuappapi.auth.model;

import com.jdaniel.sakuappapi.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "access_tokens", schema = "authentication")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_token_id", nullable = false, unique = true)
    private Long accessTokenId;
    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;
    @Column(name = "is_expired", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isExpired;
    @Column(name = "is_revoked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isRevoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
