package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.ItemDto;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.repository.ContainerRepository;
import de.upteams.sortmeister.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ContainerRepository containerRepository;

    public ItemService(ItemRepository itemRepository, ContainerRepository containerRepository) {
        this.itemRepository = itemRepository;
        this.containerRepository = containerRepository;
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> new ItemDto(item.getId(), item.getName(), item.getType(), item.getDescription(), item.getContainer() != null ? item.getContainer().getId() : null))
                .collect(Collectors.toList());
    }

    public Optional<ItemDto> getItemById(Long id) {
        return itemRepository.findById(id)
                .map(item -> new ItemDto(item.getId(), item.getName(), item.getType(), item.getDescription(), item.getContainer() != null ? item.getContainer().getId() : null));
    }

    @Transactional
    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.name());
        item.setType(itemDto.type());
        item.setDescription(itemDto.description());

        if (itemDto.containerId() != null) {
            Container container = containerRepository.findById(itemDto.containerId())
                    .orElseThrow(() -> new IllegalArgumentException("Container not found with id " + itemDto.containerId()));
            item.setContainer(container);
        }

        Item savedItem = itemRepository.save(item);
        return new ItemDto(savedItem.getId(), savedItem.getName(), savedItem.getType(), savedItem.getDescription(), savedItem.getContainer() != null ? savedItem.getContainer().getId() : null);
    }

    @Transactional
    public ItemDto updateItem(Long id, ItemDto updatedItemDto) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItemDto.name());
                    item.setType(updatedItemDto.type());
                    item.setDescription(updatedItemDto.description());

                    if (updatedItemDto.containerId() != null) {
                        Container container = containerRepository.findById(updatedItemDto.containerId())
                                .orElseThrow(() -> new IllegalArgumentException("Container not found with id " + updatedItemDto.containerId()));
                        item.setContainer(container);
                    } else {
                        item.setContainer(null);
                    }

                    Item updatedItem = itemRepository.save(item);
                    return new ItemDto(updatedItem.getId(), updatedItem.getName(), updatedItem.getType(), updatedItem.getDescription(), updatedItem.getContainer() != null ? updatedItem.getContainer().getId() : null);
                })
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id " + id));
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}