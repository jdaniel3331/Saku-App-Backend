package com.jdaniel.sakuappapi.auth.repository;

import com.jdaniel.sakuappapi.auth.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    Optional<AccessToken> findByAccessTokenIgnoreCase(String accessToken);
}
