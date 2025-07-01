package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.*;
import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.service.AdvertService;
import de.upteams.sortmeister.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "List all adverts", description = "Retrieve a list of all adverts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of all adverts")
    @GetMapping
    public List<Advert> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Search adverts", description = "Search adverts by title")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    @GetMapping("/search")
    public List<Advert> search(@RequestParam String title) {
        return service.getResults(title);
    }

    @Operation(summary = "Get advert by ID", description = "Retrieve a specific advert by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Advert found"),
            @ApiResponse(responseCode = "404", description = "Advert not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Advert> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Create new advert", description = "Add a new advert to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Advert created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Advert> create(@Valid @RequestBody CreateAdvertDto req) {
        Advert advert = service.create(new Advert(req.title(), req.description(), req.photo()));
        return ResponseEntity.ok(advert);
    }
//
//    @Operation(summary = "Update item", description = "Modify details of an existing waste item")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid input data"),
//            @ApiResponse(responseCode = "404", description = "Item not found")
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<ItemDto> update(@PathVariable Long id, @Valid @RequestBody UpdateItemRequest req) {
//        if (service.getById(id).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        Item updated = service.update(id, new Item(req.name(), req.containerId()));
//        return ResponseEntity.ok(new ItemDto(updated.getId(), updated.getName(), updated.getContainerId()));
//    }
//
    @Operation(summary = "Delete item", description = "Remove a waste item by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
