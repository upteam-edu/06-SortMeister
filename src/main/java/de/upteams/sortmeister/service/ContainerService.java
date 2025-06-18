package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.ContainerDto;
import de.upteams.sortmeister.dto.ItemResult;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.repository.ContainerRepository;
import de.upteams.sortmeister.repository.InMemoryContainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {
    private final ContainerRepository repository;

    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }

    public List<Container> getAll() {
        return repository.findAll();
    }

    public Optional<Container> getById(Long id) {
        return repository.findById(id);
    }


    public Container create(Container container) {
        if (repository.findByName(container.getName()).isPresent()) {
            throw new IllegalArgumentException("Контейнер с именем '" + container.getName() + "' уже существует.");
        }
        if (repository.findByColor(container.getColor()).isPresent()) {
            throw new IllegalArgumentException("Контейнер с цветом '" + container.getColor() + "' уже существует.");
        }
        return repository.save(container);
    }

    public Container update(Long id, Container container) {
        Optional<Container> existingContainerOptional = repository.findById(id);
        if (existingContainerOptional.isEmpty()) {
            throw new IllegalArgumentException("Контейнер с ID " + id + " не найден.");
        }
        Container existingContainer = existingContainerOptional.get();

        // Проверяем имя, только если оно изменилось и не совпадает с другим контейнером
        if (!existingContainer.getName().equalsIgnoreCase(container.getName())) {
            if (repository.findByName(container.getName()).isPresent()) {
                throw new IllegalArgumentException("Контейнер с именем '" + container.getName() + "' уже существует.");
            }
        }

        // Проверяем цвет, только если он изменился и не совпадает с другим контейнером
        if (!existingContainer.getColor().equalsIgnoreCase(container.getColor())) {
            if (repository.findByColor(container.getColor()).isPresent()) {
                throw new IllegalArgumentException("Контейнер с цветом '" + container.getColor() + "' уже существует.");
            }
        }

        container.setId(id);
        return repository.save(container);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
