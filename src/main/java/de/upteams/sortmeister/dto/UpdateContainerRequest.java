package de.upteams.sortmeister.dto;

import de.upteams.sortmeister.validation.NoCyrillic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateContainerRequest(
        @NotBlank @NoCyrillic String name,
        @NotNull @Pattern(regexp = "^#([A-Fa-f0-9]{6})$") String color,
        String description
) {}