package de.upteams.sortmeister.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAdvertDto(  @NotBlank
                                String title,
                                @NotBlank
                                String photo,
                                @NotBlank
                                String description) {

}
