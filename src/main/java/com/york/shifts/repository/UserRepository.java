package com.york.shifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.york.shifts.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

