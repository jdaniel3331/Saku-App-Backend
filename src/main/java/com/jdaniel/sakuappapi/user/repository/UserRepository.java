package com.jdaniel.sakuappapi.user.repository;

import com.jdaniel.sakuappapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT uc.user FROM UserCredential uc WHERE LOWER(uc.email) = LOWER(:email)")
    Optional<User> getUserByEmailIgnoreCase(@Param("email") String email);
}
