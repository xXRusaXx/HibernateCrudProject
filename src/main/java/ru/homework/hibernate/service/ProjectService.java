package ru.homework.hibernate.service;

import org.springframework.stereotype.Service;
import ru.homework.hibernate.model.dto.ProjectDto;
import ru.homework.hibernate.model.pojo.Project;
import ru.homework.hibernate.repository.IRepository;
import ru.homework.hibernate.repository.imp.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    IRepository<Project> repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project getById(long id) {
        return repository.getById(id);
    }

    public List<Project> getAll() {
        return repository.getAll();
    }

    public ProjectDto getDtoById(long id) {
        return projectToDto(repository.getById(id));
    }

    public List<ProjectDto> getAllDto() {
        List<Project> allProjects = repository.getAll();
        ArrayList<ProjectDto> projectDtos = new ArrayList<>();

        for (Project project :allProjects) {
            projectDtos.add(projectToDto(project));
        }

        return projectDtos;
    }

    public ProjectDto create(Project position) {
        return projectToDto(repository.create(position));
    }

    public ProjectDto update(Project position) {
        return projectToDto(repository.update(position));
    }

    public boolean delete(long id) {
        return repository.delete(id);
    }

    public static ProjectDto projectToDto(Project project) {
        return ProjectDto.builder()
                .name(project.getName())
                .build();
    }
}
