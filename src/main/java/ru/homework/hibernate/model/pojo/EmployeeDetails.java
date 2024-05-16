package ru.homework.hibernate.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee_details")
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_details_id", nullable = false)
    private Long id;

    private String address;
    private Date birthdayDate;
}
