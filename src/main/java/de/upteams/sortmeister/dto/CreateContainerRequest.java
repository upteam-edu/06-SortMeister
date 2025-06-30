package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateContainerRequest(
        @NotBlank String name,
        @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Invalid color format. Must be hex (e.g., #A1B2C3).")
        String color,
        String description
) {}
