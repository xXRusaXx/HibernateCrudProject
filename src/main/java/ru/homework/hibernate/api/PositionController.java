package ru.homework.hibernate.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.hibernate.model.dto.PositionDto;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> createEmployeeDetails(@RequestBody Position position) {
        try {
            PositionDto created = service.create(position);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось сохранить информацию о должности");
        }
    }

    @GetMapping("/allOriginal")
    public ResponseEntity<?> getAllEmployeeDetails() {
        try {
            List<Position> all = service.getAll();
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о должностях не найдены");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployeeDetailsDto() {
        try {
            List<PositionDto> allDto = service.getAllDto();
            return ResponseEntity.ok(allDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о должностях не найдены");
        }
    }

    @GetMapping("/original/{id}")
    public ResponseEntity<?> getEmployeeDetailsById(@PathVariable Long id) {
        try {
            Position byId = service.getById(id);
            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о должности не найдены");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetailsDtoById(@PathVariable Long id) {
        try {
            PositionDto byId = service.getDtoById(id);
            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о должности не найдены");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployeeDetails(@RequestBody Position position) {
        try {
            PositionDto updated = service.update(position);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось обновить информацию о должности");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeDetails(@PathVariable Long id) {
        try {
            Boolean isDelete = service.delete(id);
            return ResponseEntity.ok(isDelete);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось удалить информацию о должности");
        }
    }
}
