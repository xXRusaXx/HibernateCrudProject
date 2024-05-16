package ru.homework.hibernate.service;

import org.springframework.stereotype.Service;
import ru.homework.hibernate.model.dto.PositionDto;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.repository.IRepository;
import ru.homework.hibernate.repository.imp.PositionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    IRepository<Position> repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public Position getById(long id) {
        return repository.getById(id);
    }

    public List<Position> getAll() {
        return repository.getAll();
    }

    public PositionDto getDtoById(long id) {
        return positionToDto(repository.getById(id));
    }

    public List<PositionDto> getAllDto() {
        List<Position> allPositions = repository.getAll();
        ArrayList<PositionDto> positionDtos = new ArrayList<>();

        for (Position position : allPositions) {
            positionDtos.add(positionToDto(position));
        }

        return positionDtos;
    }

    public PositionDto create(Position position) {
        return positionToDto(repository.create(position));
    }

    public PositionDto update(Position position) {
        return positionToDto(repository.update(position));
    }

    public boolean delete(long id) {
        return repository.delete(id);
    }

    public static PositionDto positionToDto(Position position) {
        return PositionDto.builder()
                .name(position.getName())
                .build();
    }
}
