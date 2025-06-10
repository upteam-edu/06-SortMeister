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
        return repository.save(container);
    }

    public Container update(Long id, Container container) {
        container.setId(id);
        return repository.save(container);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
