package de.upteams.sortmeister.service;

import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.repository.ContainerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> getAll() {
        return containerRepository.findAll();
    }

    public Optional<Container> getContainerById(Long id) {
        return containerRepository.findById(id);
    }

    public Container createContainer(Container container) {
        return containerRepository.save(container);
    }

    public Container updateContainer(Long id, Container updatedContainer) {
        return containerRepository.findById(id)
                .map(container -> {
                    container.setName(updatedContainer.getName());
                    container.setColor(updatedContainer.getColor());
                    container.setDescription(updatedContainer.getDescription());
                    return containerRepository.save(container);
                })
                .orElseThrow(() -> new IllegalArgumentException("Container not found with id " + id));
    }

    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }
}