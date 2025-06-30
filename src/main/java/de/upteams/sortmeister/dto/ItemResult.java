
package de.upteams.sortmeister.dto;

import de.upteams.sortmeister.model.Item;

public record ItemResult(
        Long id,
        String name,
        String type,
        String description,
        ContainerDto container
) {

    public ItemResult(Item item) {
        this(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getDescription(),
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