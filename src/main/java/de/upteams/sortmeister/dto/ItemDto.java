package de.upteams.sortmeister.dto;

import de.upteams.sortmeister.model.Item;

public record ItemDto(Long id, String name, ContainerDto container) {
    public ItemDto(Item item) {
        this(
                item.getId(),
                item.getName(),
                // Если у item есть контейнер, создаем ContainerDto, иначе null
                item.getContainer() != null ?
                        new ContainerDto(
                                item.getContainer().getId(),
                                item.getContainer().getName(),
                                item.getContainer().getColor(),
                                item.getContainer().getDescription()
                        ) :
                        null
        );
    }
}
