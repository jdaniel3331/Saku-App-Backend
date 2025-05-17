package com.jdaniel.sakuappapi.auth.repository;

import com.jdaniel.sakuappapi.auth.model.UserCredential;
import com.jdaniel.sakuappapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<UserCredential> getUserCredentialByEmailIgnoreCase(String email);

}
