package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.CreateItemRequest;
import de.upteams.sortmeister.dto.ItemDto;
import de.upteams.sortmeister.dto.ItemResult;
import de.upteams.sortmeister.dto.UpdateItemRequest;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.service.ItemService;
import de.upteams.sortmeister.service.ContainerService;
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
import java.util.Optional;

@Tag(name = "Items", description = "Operations related to waste items")
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class ItemController {

    private final ItemService itemService;
    private final ContainerService containerService;


    public ItemController(ItemService itemService, ContainerService containerService) {
        this.itemService = itemService;
        this.containerService = containerService;
    }

    @Operation(summary = "List all items", description = "Retrieve a list of all waste items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of items")
    @GetMapping
    public List<ItemDto> getAll() {
        return itemService.getAllItems().stream()
                .map(ItemDto::new)
                .toList();
    }

    @Operation(summary = "Search items", description = "Search waste items by name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    @GetMapping("/search")
    public List<ItemResult> search(
            @Parameter(description = "Query string for item name search", required = true)
            @RequestParam String name) {
        return itemService.getResults(name);
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


        Optional<Item> itemOptional = itemService.getById(id);

        if (itemOptional.isPresent()) {
            return ResponseEntity.ok(new ItemDto(itemOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
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

        Container container = null;
        if (req.containerId() != null) {
            Optional<Container> foundContainer = containerService.getById(req.containerId());
            if (foundContainer.isEmpty()) {

                return ResponseEntity.badRequest().build();
            }
            container = foundContainer.get();
        }

        Item i = itemService.create(new Item(req.name(), container));
        return ResponseEntity.ok(new ItemDto(i));
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
            Container container = null;
            if (req.containerId() != null) {
                Optional<Container> foundContainer = containerService.getById(req.containerId());
                if (foundContainer.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }
                container = foundContainer.get();
            }

            Item updated = itemService.update(id, new Item(req.name(), container));
            return ResponseEntity.ok(new ItemDto(updated));
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
        if (itemService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}