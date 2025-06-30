package de.upteams.sortmeister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto {
    private Long id;
    private String title;
    private String description;
    private String photo;
} 