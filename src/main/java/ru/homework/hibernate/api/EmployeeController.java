package ru.homework.hibernate.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.hibernate.model.dto.EmployeeDto;
import ru.homework.hibernate.model.pojo.Employee;
import ru.homework.hibernate.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/original{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            Employee byId = service.getById(id);

            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Сотрудник с id: " + id + " не найден");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDtoById(@PathVariable Long id) {
        try {
            EmployeeDto byId = service.getDtoById(id);

            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Сотрудник с id: " + id + " не найден");
        }
    }

    @GetMapping("/allOriginal")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> all = service.getAll();

            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployeesDto() {
        try {
            List<EmployeeDto> all = service.getAllDto();

            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            EmployeeDto created = service.create(employee);

            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось сохранить  работника");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody Employee employee) {
        try {
            EmployeeDto updated = service.update(employee);

            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось обновить информацию о работнике");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            service.delete(id);

            return ResponseEntity.ok("Работник успешно удален");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось удалить информацию о работнике");
        }
    }
}
