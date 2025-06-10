package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> findAll();
    Optional<Item> findById(Long id);
    List<Item> findByNameContains(String query);
    Item save(Item item);
    void deleteById(Long id);
}