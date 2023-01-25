package com.example.waa_first_demo.aspect.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logger {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID uuid;

    LocalDateTime dateTime;

    long time;

    long principle;

    String operation;

    public Logger(LocalDateTime dateTime, long time, long principle, String operation) {
        this.dateTime = dateTime;
        this.time = time;
        this.principle = principle;
        this.operation = operation;
    }
}
