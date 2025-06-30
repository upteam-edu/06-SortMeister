package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.dto.CreateAdvertRequest;
import de.upteams.sortmeister.dto.UpdateAdvertRequest;
import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.service.AdvertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Adverts", description = "Operations related to adverts")
@RestController
@RequestMapping("/api/adverts")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class AdvertController {

    private final AdvertService service;

    public AdvertController(AdvertService service) {
        this.service = service;
    }

    @Operation(summary = "Get all adverts", description = "Retrieve a list of all adverts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of adverts")
    @GetMapping
    public List<AdvertDto> getAll() {
        return service.getAll().stream()
                .map(a -> new AdvertDto(a.getTitle(), a.getDescription(), a.getPhoto()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get advert by title", description = "Retrieve a specific advert by its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advert found"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @GetMapping("/{title}")
    public ResponseEntity<AdvertDto> getByTitle(@PathVariable String title) {
        return service.getByTitle(title)
                .map(a -> ResponseEntity.ok(new AdvertDto(a.getTitle(), a.getDescription(), a.getPhoto())))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new advert", description = "Add a new advert to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advert created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<AdvertDto> create(@Valid @RequestBody CreateAdvertRequest req) {
        Advert a = service.create(new Advert(req.title(), req.description(), req.photo()));
        return ResponseEntity.ok(new AdvertDto(a.getTitle(), a.getDescription(), a.getPhoto()));
    }

    @Operation(summary = "Update advert", description = "Modify details of an existing advert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advert updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @PutMapping("/{title}")
    public ResponseEntity<AdvertDto> update(@PathVariable String title, @Valid @RequestBody UpdateAdvertRequest req) {
        if (service.getByTitle(title).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Advert updated = service.update(title, new Advert(req.title(), req.description(), req.photo()));
        return ResponseEntity.ok(new AdvertDto(updated.getTitle(), updated.getDescription(), updated.getPhoto()));
    }

    @Operation(summary = "Delete advert", description = "Remove an advert by its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Advert deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @DeleteMapping("/{title}")
    public ResponseEntity<Void> delete(@PathVariable String title) {
        if (service.getByTitle(title).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(title);
        return ResponseEntity.noContent().build();
    }
}
