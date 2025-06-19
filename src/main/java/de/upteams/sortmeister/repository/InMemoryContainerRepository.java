package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Container;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryContainerRepository implements ContainerRepository {

    private final Map<Long, Container> map = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<Container> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Container> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Container save(Container container) {
        if (container.getId() == null) {
            container.setId(idGen.getAndIncrement());
        }
        map.put(container.getId(), container);
        return container;
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }

    @Override
    public boolean existsByColor(String color) {
        if (color == null) return false;
        String normalizedColor = color.trim().toLowerCase();
        return map.values().stream()
                .anyMatch(c -> normalizedColor.equals(
                        Optional.ofNullable(c.getColor()).orElse("").trim().toLowerCase()));
    }

    @Override
    public boolean existsByName(String name) {
        if (name == null) return false;
        String normalizedName = name.trim().toLowerCase();
        return map.values().stream()
                .anyMatch(c -> normalizedName.equals(
                        Optional.ofNullable(c.getName()).orElse("").trim().toLowerCase()));
    }
}
