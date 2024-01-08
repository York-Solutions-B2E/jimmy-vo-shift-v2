package com.york.shifts.controller;

import com.york.shifts.model.Shift;
import com.york.shifts.service.ShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService){ this.shiftService = shiftService;}

    @GetMapping
    public ResponseEntity<List<Shift>> getAllShifts() {
        List<Shift> shifts = shiftService.getAllActiveShifts();
        return ResponseEntity.ok(shifts);
    }
}
