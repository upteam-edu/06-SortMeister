package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.service.ContainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping
    public List<Container> getAllContainers() {
        return containerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> getContainerById(@PathVariable Long id) {
        return containerService.getContainerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Container> createContainer(@RequestBody Container container) {
        Container createdContainer = containerService.createContainer(container);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContainer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Container> updateContainer(@PathVariable Long id, @RequestBody Container updatedContainer) {
        try {
            Container container = containerService.updateContainer(id, updatedContainer);
            return ResponseEntity.ok(container);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }
}