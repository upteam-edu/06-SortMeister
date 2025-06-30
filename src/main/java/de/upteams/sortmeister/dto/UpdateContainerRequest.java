package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateContainerRequest(
        @NotBlank String name,
        @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Invalid color format. Must be hex.")
        String color,
        String description
) {}
