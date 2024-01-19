package com.york.shifts.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shifts")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "assigned_to_id")
    private Long assignedToId;
    @Column(name = "offered_to_id")
    private Long offeredToId;

    @Column(name = "start_date_time", nullable = false)
    private LocalDate start;

    @Column(name = "end_date_time", nullable = false)
    private LocalDate end;

    @Column(name = "role", nullable = false)
    private Long role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_off_day", nullable = false)
    private Boolean isOffDay;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Long getOfferedToId() {
        return offeredToId;
    }

    public void setOfferedToId(Long offeredToId) {
        this.offeredToId = offeredToId;
    }
    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published){
        isPublished = published;
    }

    public Boolean getOffDay() {
        return isOffDay;
    }

    public void setOffDay(Boolean offDay) {
        isOffDay = offDay;
    }


    public Shift(Long assignedToId, long offeredToId, LocalDate start, LocalDate end, Long role, Boolean isActive, Boolean isOffDay) {
        this.assignedToId = assignedToId;
        this.offeredToId = offeredToId;
        this.start = start;
        this.end = end;
        this.role = role;
        this.isActive = isActive;
        this.isOffDay = isOffDay;
    }

    public Shift() {

    }
}

