package ru.homework.hibernate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.model.pojo.Project;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private EmployeeDetailsDto details;
    private PositionDto position;
    private List<ProjectDto> projects;
}