package com.york.shifts.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "role", nullable = false)
    private Integer role;

    public User() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getRole() {
        return role;
    }

    public void setPassword(String password) {
        // Apply secure password hashing here
        this.password = password; // Replace with hashed password
    }
    public String getPassword() {return password;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public User(String email, String password, String firstName, String lastName, Integer role) {
        this.email = email;
        this.password = password; // Ensure password hashing
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

}

