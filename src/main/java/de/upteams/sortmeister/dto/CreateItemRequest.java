package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateItemRequest(
        @NotBlank String name,
        @NotNull Long containerId
) {}
