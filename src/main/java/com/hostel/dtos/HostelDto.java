package com.hostel.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HostelDto {
    private String id;
    @NotEmpty(message = "hostelCode can not be blank")
    private String hostelCode;
    @NotEmpty(message = "location can not be blank")
    private String location;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private Long fees;
    private String numberOfBeds;
    private boolean isAvailable;
}
