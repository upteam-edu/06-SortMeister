package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.ContainerDto;
import de.upteams.sortmeister.dto.CreateContainerRequest;
import de.upteams.sortmeister.dto.UpdateContainerRequest;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.service.ContainerService;
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

@Tag(name = "Containers", description = "Operations related to waste containers")
@RestController
@RequestMapping("/api/containers")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class ContainerController {

    private final ContainerService service;

    public ContainerController(ContainerService service) {
        this.service = service;
    }

    @Operation(summary = "Get all containers", description = "Retrieve a list of all waste container types")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of containers")
    @GetMapping
    public List<ContainerDto> getAll() {
        return service.getAll().stream()
                .map(c -> new ContainerDto(c.getId(), c.getName(), c.getColor(), c.getDescription()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get container by ID", description = "Retrieve a specific waste container by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Container found"),
            @ApiResponse(responseCode = "404", description = "Container not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContainerDto> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(c -> ResponseEntity.ok(new ContainerDto(c.getId(), c.getName(), c.getColor(), c.getDescription())))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new container", description = "Add a new waste container type to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Container created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ContainerDto> create(@Valid @RequestBody CreateContainerRequest req) {
        Container c = service.create(new Container(req.name(), req.color(), req.description()));
        return ResponseEntity.ok(new ContainerDto(c.getId(), c.getName(), c.getColor(), c.getDescription()));
    }

    @Operation(summary = "Update container", description = "Modify details of an existing waste container")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Container updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Container not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContainerDto> update(@PathVariable Long id, @Valid @RequestBody UpdateContainerRequest req) {
        if (service.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Container updated = service.update(id, new Container(req.name(), req.color(), req.description()));
        return ResponseEntity.ok(new ContainerDto(updated.getId(), updated.getName(), updated.getColor(), updated.getDescription()));
    }

    @Operation(summary = "Delete container", description = "Remove a waste container type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Container deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Container not found")
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

