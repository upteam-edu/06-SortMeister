package de.upteams.sortmeister.dto;


import jakarta.validation.constraints.NotBlank;

public record CreateAdvertRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String photo
) {}

