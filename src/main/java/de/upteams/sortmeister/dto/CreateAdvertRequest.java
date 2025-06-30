package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAdvertRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String photo;
} 