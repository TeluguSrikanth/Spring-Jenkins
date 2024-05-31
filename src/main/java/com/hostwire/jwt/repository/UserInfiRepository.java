package com.hostwire.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostwire.jwt.entity.UserInfi;

public interface UserInfiRepository extends JpaRepository<UserInfi, Integer>{

	Optional<UserInfi> findByName(String username);

}
