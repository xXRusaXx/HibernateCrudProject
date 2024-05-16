package ru.homework.hibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.hibernate.model.dto.EmployeeDetailsDto;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.repository.IRepository;
import ru.homework.hibernate.repository.imp.EmployeeDetailsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService {

    IRepository<EmployeeDetails> repository;

    @Autowired
    public EmployeeDetailsService(EmployeeDetailsRepository repository) {
        this.repository = repository;
    }

    public EmployeeDetails getById(long id) {
        return repository.getById(id);
    }

    public List<EmployeeDetails> getAll() {
        return repository.getAll();
    }

    public EmployeeDetailsDto getDtoById(long id) {
        return employeeDetailsToDto(repository.getById(id));
    }

    public List<EmployeeDetailsDto> getAllDto() {
        List<EmployeeDetails> allEmployeeDetails = repository.getAll();
        ArrayList<EmployeeDetailsDto> employeeDetailsDtos = new ArrayList<>();

        for (EmployeeDetails employeeDetails : allEmployeeDetails) {
            employeeDetailsDtos.add(employeeDetailsToDto(employeeDetails));
        }

        return employeeDetailsDtos;
    }

    public EmployeeDetailsDto create(EmployeeDetails employeeDetails) {
        return employeeDetailsToDto(repository.create(employeeDetails));
    }

    public EmployeeDetailsDto update(EmployeeDetails employeeDetails) {
        return employeeDetailsToDto(repository.update(employeeDetails));
    }

    public boolean delete(long id) {
        return repository.delete(id);
    }

    public static EmployeeDetailsDto employeeDetailsToDto(EmployeeDetails details) {
        return EmployeeDetailsDto.builder()
                .address(details.getAddress())
                .birthdayDate(details.getBirthdayDate())
                .build();
    }
}
