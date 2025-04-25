package com.jdaniel.sakuappapi.user.repository;

import com.jdaniel.sakuappapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
