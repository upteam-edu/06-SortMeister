package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.ContainerDto;
import de.upteams.sortmeister.dto.ExtendedItemDto;
import de.upteams.sortmeister.dto.ItemResult;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository repository;
    private final ContainerService containerService;

    public ItemService(ItemRepository repository, ContainerService containerService) {
        this.repository = repository;
        this.containerService = containerService;
    }

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Optional<Item> getById(Long id) {
        return repository.findById(id);
    }

    public ExtendedItemDto getExtededItemById(Long id) {
        Item item = repository.findById(id).orElseThrow();
        ContainerDto container = containerService.getById(item.getContainerId()).map(c-> new ContainerDto(c.getId(), c.getName(), c.getColor(), c.getDescription())).orElseThrow();
        return new ExtendedItemDto(item.getId(), item.getName(), container);
    }

    public List<Item> search(String name) {
        return repository.findByNameContains(name);
    }

    public Item create(Item item) {
        return repository.save(item);
    }

    public Item update(Long id, Item item) {
        item.setId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ItemResult> getResults(String name) {
        return search(name).stream().map(item -> {
            Container container = containerService.getById(item.getContainerId()).orElse(null);
            ContainerDto cd = container != null ? new ContainerDto(container.getId(), container.getName(), container.getColor(), container.getDescription()) : null;
            return new ItemResult(item.getName(), cd);
        }).collect(Collectors.toList());
    }
}
