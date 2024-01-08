package com.york.shifts.service;

import com.york.shifts.model.Shift;
import com.york.shifts.repository.ShiftRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository userRepository) {
        this.shiftRepository = userRepository;
    }

    public List<Shift> getAllActiveShifts() {
        return shiftRepository.findAllByIsActiveTrue();
    }


}
