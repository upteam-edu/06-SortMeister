// src/main/java/de/upteams/sortmeister/dto/ItemDto.java
package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ItemDto(
        Long id,
        @NotBlank(message = "Name cannot be empty")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,
        @NotBlank(message = "Type cannot be empty")
        @Size(max = 255, message = "Type cannot exceed 255 characters")
        String type,
        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description,
        Long containerId
) {

}