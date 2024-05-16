package ru.homework.hibernate.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.hibernate.model.dto.EmployeeDetailsDto;
import ru.homework.hibernate.model.dto.EmployeeDto;
import ru.homework.hibernate.model.dto.PositionDto;
import ru.homework.hibernate.model.dto.ProjectDto;
import ru.homework.hibernate.model.pojo.Employee;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.model.pojo.Project;
import ru.homework.hibernate.repository.*;
import ru.homework.hibernate.repository.imp.EmployeeDetailsRepository;
import ru.homework.hibernate.repository.imp.EmployeeRepository;
import ru.homework.hibernate.repository.imp.PositionRepository;
import ru.homework.hibernate.repository.imp.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    IRepository<Employee> employeeRepository;
    IRepository<EmployeeDetails> detailsRepository;
    IRepository<Position> positionRepository;
    IRepository<Project> projectRepository;


    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeDetailsRepository detailsRepository,
                           PositionRepository positionRepository,
                           ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.detailsRepository = detailsRepository;
        this.positionRepository = positionRepository;
        this.projectRepository = projectRepository;
    }

    public Employee getById(long id) {
        return employeeRepository.getById(id);
    }
    public EmployeeDto getDtoById(long id) {
        return employeeToDto(employeeRepository.getById(id));
    }

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public List<EmployeeDto> getAllDto() {
        List<Employee> allEmployees = employeeRepository.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee :allEmployees) {
            employeeDtos.add(employeeToDto(employee));
        }

        return employeeDtos;
    }

    public EmployeeDto create(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Не указан сотрудник");
        }

        EmployeeDetails details = employee.getDetails();
        Position position = employee.getPosition();
        List<Project> projects = employee.getProjects();

        checkDetails(details, employee);
        checkPosition(position, employee);
        checkProjects(projects, employee);

        Employee savedEmployee = employeeRepository.create(employee);
        EmployeeDto employeeDto = employeeToDto(savedEmployee);

        return employeeDto;
    }

    public EmployeeDto update(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Не указан сотрудник");
        }

        EmployeeDetails details = employee.getDetails();
        Position position = employee.getPosition();
        List<Project> projects = employee.getProjects();

        checkDetails(details, employee);
        checkPosition(position, employee);
        checkProjects(projects, employee);

        Employee updatedEmployee = employeeRepository.update(employee);
        EmployeeDto employeeDto = employeeToDto(updatedEmployee);

        return employeeDto;
    }

    public boolean delete(long id) {
        return employeeRepository.delete(id);
    }

    private void checkDetails(EmployeeDetails details, Employee employee) {
        if (details.getId() == null) {
            if (details.getAddress() == null || details.getBirthdayDate() == null) {
                throw new RuntimeException("Не корректно указаны дополнительные сведения сотрудника");
            } else {
                EmployeeDetails savedNewDetails = detailsRepository.create(details);
                employee.setDetails(savedNewDetails);
            }
        } else {
            employee.setDetails(detailsRepository.getById(details.getId()));
        }
    }

    private void checkPosition(Position position, Employee employee) {
        if (position.getId() == null) {
            if (position.getName() == null) {
                throw new RuntimeException("Не указана должность работника");
            } else {
                Position savedNewPosition = positionRepository.create(employee.getPosition());
                employee.setPosition(savedNewPosition);
            }
        } else {
            employee.setPosition(positionRepository.getById(position.getId()));
        }
    }

    private void checkProjects(List<Project> projects, Employee employee) {
        if (projects == null) {
            employee.setProjects(new ArrayList<>());
        } else {
            ArrayList<Project> forEmployee = new ArrayList<>();
            projects.removeIf(Objects::isNull);

            projects.stream().forEach(project -> {
                if (project.getId() == null) {
                    if (project.getName() == null) {
                        throw new RuntimeException("Попытка добавить проект без названия");
                    } else {
                        forEmployee.add(projectRepository.create(project));
                    }
                } else {
                    forEmployee.add(projectRepository.getById(project.getId()));
                }
            });

            employee.setProjects(forEmployee);
        }
    }

    private EmployeeDto employeeToDto(Employee employee) {
        EmployeeDetails details = employee.getDetails();
        Position position = employee.getPosition();
        List<Project> projects = employee.getProjects();

        EmployeeDetailsDto detailsDto = EmployeeDetailsService.employeeDetailsToDto(details);

        PositionDto positionDto = PositionService.positionToDto(position);

        ArrayList<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            projectDtos.add(ProjectService.projectToDto(project));
        }

        return EmployeeDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .details(detailsDto)
                .position(positionDto)
                .projects(projectDtos)
                .build();
    }
}
