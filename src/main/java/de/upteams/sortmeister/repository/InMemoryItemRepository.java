package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryItemRepository implements ItemRepository {
    private final Map<Long, Item> map = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public InMemoryItemRepository() {

        save(new Item("Бутылка пластиковая", 1L));
        save(new Item("Старая газета", 2L));
        save(new Item("Стеклянная банка", 1L));
        save(new Item("Картонная коробка", 3L));
        save(new Item("Батарейки", null));
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Item> findByNameContains(String query) {
        return map.values().stream()
                .filter(i -> i.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Item save(Item item) {
        if (item.getId() == null) {
            item.setId(idGen.getAndIncrement());
        }
        map.put(item.getId(), item);
        return item;
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}