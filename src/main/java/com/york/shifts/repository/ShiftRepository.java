package com.york.shifts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.york.shifts.model.Shift;

import java.util.List;


@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByIsActiveTrue();
}

