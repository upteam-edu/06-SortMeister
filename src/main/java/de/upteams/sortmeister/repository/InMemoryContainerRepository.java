package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Container;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Optional<Container> findByName(String name) {
        return map.values().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public Optional<Container> findByColor(String color) {
        return map.values().stream()
                .filter(c -> c.getColor().equalsIgnoreCase(color))
                .findFirst();
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
}
