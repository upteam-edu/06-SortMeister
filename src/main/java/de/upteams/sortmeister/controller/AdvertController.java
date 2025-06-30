package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.dto.CreateAdvertRequest;
import de.upteams.sortmeister.service.AdvertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advert")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @GetMapping
    public List<AdvertDto> getAll() {
        return advertService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertDto> getById(@PathVariable Long id) {
        return advertService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdvertDto create(@RequestBody @Valid CreateAdvertRequest request) {
        return advertService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertDto> update(@PathVariable Long id, @RequestBody @Valid CreateAdvertRequest request) {
        return advertService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return advertService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
} 