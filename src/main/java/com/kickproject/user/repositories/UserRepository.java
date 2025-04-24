package com.kickproject.user.repositories;

import com.kickproject.user.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByIdAndPhone(Long userId, String userPhone);
    Optional<User> findByPhone(String userPhone);
}
