package de.upteams.sortmeister.service;

import de.upteams.sortmeister.exception.ContainerValidationException;
import de.upteams.sortmeister.exception.ErrorMessages;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.repository.ContainerRepository;
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
        validateUniqueNameAndColor(container, null);
        return repository.save(container);
    }

    public Container update(Long id, Container container) {
        repository.findById(id)
                .orElseThrow(() -> new ContainerValidationException(
                        String.format(ErrorMessages.NOT_FOUND, id)
                ));
        validateUniqueNameAndColor(container, id);
        container.setId(id);
        return repository.save(container);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void validateUniqueNameAndColor(Container container, Long excludedId) {
        repository.findByName(container.getName())
                .filter(c -> excludedId == null || !c.getId().equals(excludedId))
                .ifPresent(c -> {
                    throw new ContainerValidationException(
                            String.format(ErrorMessages.NAME_EXISTS, container.getName())
                    );
                });

        repository.findByColor(container.getColor())
                .filter(c -> excludedId == null || !c.getId().equals(excludedId))
                .ifPresent(c -> {
                    throw new ContainerValidationException(
                            String.format(ErrorMessages.COLOR_EXISTS, container.getColor())
                    );
                });
    }
}