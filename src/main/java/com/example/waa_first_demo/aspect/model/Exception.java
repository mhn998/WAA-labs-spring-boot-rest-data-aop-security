package com.example.waa_first_demo.aspect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID uuid;

    LocalDate date;

    LocalTime time;

    long principle;

    String operation;

    public Exception(LocalDate date, LocalTime time, long principle, String operation) {
        this.date = date;
        this.time = time;
        this.principle = principle;
        this.operation = operation;
    }
}
