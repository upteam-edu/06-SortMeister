package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.CreateItemRequest;
import de.upteams.sortmeister.dto.ItemDto;
import de.upteams.sortmeister.dto.ItemResult;
import de.upteams.sortmeister.dto.UpdateItemRequest;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Items", description = "Operations related to waste items")
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Operation(summary = "List all items", description = "Retrieve a list of all waste items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of items")
    @GetMapping
    public List<ItemDto> getAll() {
        return service.getAllItems().stream()
                .map(i -> new ItemDto(i.getId(), i.getName(), i.getContainerId()))
                .toList();
    }

    @Operation(summary = "Search items", description = "Search waste items by name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    @GetMapping("/search")
    public List<ItemResult> search(
            @Parameter(description = "Query string for item name search", required = true)
            @RequestParam String name) {
        return service.getResults(name);
    }

    @Operation(summary = "Get item by ID", description = "Retrieve a specific waste item by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(
            @Parameter(description = "ID of the item to retrieve", required = true)
            @PathVariable Long id) {
        return service.getById(id)
                .map(i -> ResponseEntity.ok(new ItemDto(i.getId(), i.getName(), i.getContainerId())))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new item", description = "Add a new waste item to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ItemDto> create(
            @Parameter(description = "Request body for creating a new item", required = true)
            @Valid @RequestBody CreateItemRequest req) {
        Item i = service.create(new Item(req.name(), req.containerId()));
        return ResponseEntity.ok(new ItemDto(i.getId(), i.getName(), i.getContainerId()));
    }

    @Operation(summary = "Update item", description = "Modify details of an existing waste item")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(
            @Parameter(description = "ID of the item to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Request body for updating an item", required = true)
            @Valid @RequestBody UpdateItemRequest req) {
        try {
            Item updated = service.update(id, new Item(req.name(), req.containerId()));
            return ResponseEntity.ok(new ItemDto(updated.getId(), updated.getName(), updated.getContainerId()));
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Delete item", description = "Remove a waste item by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the item to delete", required = true)
            @PathVariable Long id) {
        if (service.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
