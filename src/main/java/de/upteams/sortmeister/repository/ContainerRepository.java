package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Container;
import java.util.List;
import java.util.Optional;

public interface ContainerRepository {
    Optional<Container> findById(Long id);
    Container save(Container container);
    List<Container> findAll();
    void deleteById(Long id);
    Optional<Container> findByName(String name);
    Optional<Container> findByColor(String color);
}