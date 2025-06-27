package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.repository.AdvertRepository;
import de.upteams.sortmeister.service.AdvertService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/adverts")
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class AdvertController {
    private final AdvertService service;

    public AdvertController(AdvertService service) {
        this.service = service;
    }

    @GetMapping
    public List<Advert> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advert> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <Advert> save(@Valid @RequestBody Advert advert) {
        Advert savedAdvert = service.save(advert);
        return ResponseEntity.ok(savedAdvert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void>  deleteById(Long id) {
        if(service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity <Advert> update(@PathVariable Long id, @Valid @RequestBody Advert advert) {
        try {
            Advert updatedAdvert = service.update(id, advert);
            return ResponseEntity.ok(updatedAdvert);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
