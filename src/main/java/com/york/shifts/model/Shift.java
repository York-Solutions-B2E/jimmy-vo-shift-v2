package com.york.shifts.model;
import jakarta.persistence.*;

@Entity
@Table(name = "shifts")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getOffDay() {
        return isOffDay;
    }

    public void setOffDay(Boolean offDay) {
        isOffDay = offDay;
    }

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "start_date_time", nullable = false)
    private String start;

    @Column(name = "end_date_time", nullable = false)
    private String end;

    @Column(name = "role", nullable = false)
    private Long role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_off_day", nullable = false)
    private Boolean isOffDay;


    public Shift(Long userId, String start, String end, Long role, Boolean isActive, Boolean isOffDay) {
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.role = role;
        this.isActive = isActive;
        this.isOffDay = isOffDay;
    }

    public Shift() {

    }
}

