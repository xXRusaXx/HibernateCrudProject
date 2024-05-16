package ru.homework.hibernate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class EmployeeDetailsDto {
    private String address;
    private Date birthdayDate;
}