package ru.homework.hibernate.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.hibernate.model.dto.EmployeeDetailsDto;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.service.EmployeeDetailsService;

import java.util.List;

@RestController
@RequestMapping("/employeeDetails")
public class EmployeeDetailsController {

    private EmployeeDetailsService service;

    @Autowired
    public EmployeeDetailsController(EmployeeDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) {
        try {
            EmployeeDetailsDto created = service.create(employeeDetails);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось сохранить информацию о работнике");
        }
    }

    @GetMapping("/allOriginal")
    public ResponseEntity<?> getAllEmployeeDetails() {
        try {
            List<EmployeeDetails> all = service.getAll();
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о работниках не найдены");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployeeDetailsDto() {
        try {
            List<EmployeeDetailsDto> allDto = service.getAllDto();
            return ResponseEntity.ok(allDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о работниках не найдены");
        }
    }

    @GetMapping("/original/{id}")
    public ResponseEntity<?> getEmployeeDetailsById(@PathVariable Long id) {
        try {
            EmployeeDetails byId = service.getById(id);
            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о работнике не найдены");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetailsDtoById(@PathVariable Long id) {
        try {
            EmployeeDetailsDto byId = service.getDtoById(id);
            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о работнике не найдены");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) {
        try {
            EmployeeDetailsDto updated = service.update(employeeDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось обновить информацию о работнике");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeDetails(@PathVariable Long id) {
        try {
            Boolean isDelete = service.delete(id);
            return ResponseEntity.ok(isDelete);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось удалить информацию о работнике");
        }
    }
}
