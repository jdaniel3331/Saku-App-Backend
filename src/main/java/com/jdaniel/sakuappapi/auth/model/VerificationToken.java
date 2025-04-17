package com.jdaniel.sakuappapi.auth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "verification_tokens", schema = "authentication")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id", nullable = false)
    private Long verificationTokenId;
    @Column(name = "verification_token", nullable = false, unique = true)
    private String verificationToken;
    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;
    @Column(name = "was_used", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean wasUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
