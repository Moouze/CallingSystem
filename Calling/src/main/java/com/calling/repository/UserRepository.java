package com.calling.repository;

import com.calling.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <Users, Long> {

    public Optional<Users> findByUser(String email);
}
