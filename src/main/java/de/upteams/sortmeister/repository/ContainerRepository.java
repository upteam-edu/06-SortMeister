package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Container;

import java.util.List;
import java.util.Optional;

public interface ContainerRepository {
    List<Container> findAll();
    Optional<Container> findById(Long id);
    Container save(Container container);
    void deleteById(Long id);

    boolean existsByName(String name);
    boolean existsByColor(String color);
    Optional<Container> findByName(String name);
    Optional<Container> findByColor(String color);
}