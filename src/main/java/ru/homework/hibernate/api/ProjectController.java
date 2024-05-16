package ru.homework.hibernate.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.hibernate.model.dto.ProjectDto;
import ru.homework.hibernate.model.pojo.Project;
import ru.homework.hibernate.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/allOriginal")
    public ResponseEntity<?> getAllEmployeeDetails() {
        try {
            List<Project> all = service.getAll();

            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о сотруднике не найдены");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployeeDetailsDto() {
        try {
            List<ProjectDto> allDto = service.getAllDto();

            return ResponseEntity.ok(allDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о сотруднике не найдены");
        }
    }


    @GetMapping("/original{id}")
    public ResponseEntity<?> getEmployeeDetailsDtoById(@PathVariable Long id) {
        try {
            Project byId = service.getById(id);

            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о сотруднике не найдены");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetailsById(@PathVariable Long id) {
        try {
            ProjectDto byId = service.getDtoById(id);

            return ResponseEntity.ok(byId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Данные о сотруднике не найдены");
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployeeDetails(@RequestBody Project project) {
        try {
            ProjectDto created = service.create(project);

            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Не удалось сохранить информацию о работнике");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployeeDetails(@RequestBody Project project) {
        try {
            ProjectDto updated = service.update(project);

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
