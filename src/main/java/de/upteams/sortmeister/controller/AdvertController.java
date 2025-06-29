package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.service.AdvertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adverts")
public class AdvertController {

    private final AdvertService service;

    public AdvertController(AdvertService service) {
        this.service = service;
    }

    @GetMapping
    public List<Advert> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Advert> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Advert create(@RequestBody Advert advert) {
        return service.create(advert);
    }

    @PutMapping("/{id}")
    public Advert update(@PathVariable Long id, @RequestBody Advert advert) {
        return service.update(id, advert);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
