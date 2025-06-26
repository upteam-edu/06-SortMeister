package de.upteams.sortmeister.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtendedItemDto {
    private Long id;
    private String name;
    private ContainerDto container;

    @Data
    @Builder
    public static class ContainerDto {
        private Long id;
        private String name;
        private String color;
    }
}