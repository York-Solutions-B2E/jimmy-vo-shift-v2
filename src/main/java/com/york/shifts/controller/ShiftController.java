package com.york.shifts.controller;

import com.york.shifts.model.Shift;
import com.york.shifts.repository.ShiftRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftRepository shiftRepository;
    public ShiftController(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }


    @GetMapping
    public List<Shift> getAllShifts(@RequestParam(defaultValue = "0") Integer weekOffset) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        startOfWeek = startOfWeek.plusWeeks(weekOffset);
        endOfWeek = endOfWeek.plusWeeks(weekOffset);

        return shiftRepository.findAllByIsActiveTrueAndStartIsBetween(startOfWeek, endOfWeek);
    }
}
