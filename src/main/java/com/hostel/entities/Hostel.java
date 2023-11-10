package com.hostel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hostel {
    @Id
    private String id;
    @Column(unique = true)
    private String hostelCode;
    private String location;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private Long fees;
    private String numberOfBeds;
    private boolean isAvailable;
}
