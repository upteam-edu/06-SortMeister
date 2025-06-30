
package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.service.AdvertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Adverts", description = "Operations related to advertisements")
@RestController
@RequestMapping("/api/adverts")

public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }



    @Operation(summary = "Get all adverts", description = "Retrieve a list of all advertisements")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of adverts")
    @GetMapping
    public ResponseEntity<List<AdvertDto>> getAllAdverts() {
        List<AdvertDto> adverts = advertService.getAllAdverts();
        return ResponseEntity.ok(adverts);
    }

    @Operation(summary = "Get advert by ID", description = "Retrieve a specific advertisement by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advert found"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdvertDto> getAdvertById(
            @Parameter(description = "ID of the advert to retrieve", required = true)
            @PathVariable Long id) {
        return advertService.getAdvertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new advert", description = "Add a new advertisement to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Advert created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<AdvertDto> createAdvert(
            @Parameter(description = "Request body for creating a new advert", required = true)
            @Valid @RequestBody AdvertDto advertDto) {
        AdvertDto createdAdvert = advertService.createAdvert(advertDto);
        return new ResponseEntity<>(createdAdvert, HttpStatus.CREATED);
    }

    @Operation(summary = "Update advert", description = "Modify details of an existing advertisement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advert updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdvertDto> updateAdvert(
            @Parameter(description = "ID of the advert to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Request body for updating an advert", required = true)
            @Valid @RequestBody AdvertDto advertDto) {
        try {
            AdvertDto result = advertService.updateAdvert(id, advertDto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete advert", description = "Remove an advertisement by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Advert deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvert(
            @Parameter(description = "ID of the advert to delete", required = true)
            @PathVariable Long id) {
        try {
            advertService.deleteAdvert(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}