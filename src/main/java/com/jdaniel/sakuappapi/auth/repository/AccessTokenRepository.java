package com.jdaniel.sakuappapi.auth.repository;

import com.jdaniel.sakuappapi.auth.model.AccessToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    Optional<AccessToken> findByAccessTokenIgnoreCase(String accessToken);
    @Modifying
    @Transactional
    @Query("UPDATE AccessToken a SET a.isExpired = true, a.isRevoked = true WHERE a.user.userCredential.email = :email")
    void invalidateTokensByUserEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE AccessToken a SET a.isExpired = true, a.isRevoked = true WHERE a.accessToken = :token")
    void invalidateToken(String token);
}
