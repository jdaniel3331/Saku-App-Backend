package com.jdaniel.sakuappapi.auth.repository;

import com.jdaniel.sakuappapi.auth.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
