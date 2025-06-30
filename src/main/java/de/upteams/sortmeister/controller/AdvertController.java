package de.upteams.sortmeister.controller;

import de.upteams.sortmeister.model.Advert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/adverts")
@CrossOrigin(origins = "http://localhost:5173")

public class AdvertController {

    private final List<Advert> adverts = new ArrayList<>();
    private final Map<Long, Advert> advertMap = new HashMap<>();
    private long currentId = 1;

    @GetMapping("/api/adverts")
    public List<Advert> getAdverts() {
        return adverts;
    }


    @GetMapping
    public List<Advert> getAll() {
        return new ArrayList<>(advertMap.values());
    }

    @GetMapping("/{id}")
    public Advert getById(@PathVariable Long id) {
        return advertMap.get(id);
    }

    @PostMapping
    public Advert create(@RequestBody Advert advert) {
        advert.setId(currentId);
        advertMap.put(currentId, advert);
        currentId++;
        return advert;
    }

    @PutMapping("/{id}")
    public Advert update(@PathVariable Long id, @RequestBody Advert advert) {
        advert.setId(id);
        advertMap.put(id, advert);
        return advert;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        advertMap.remove(id);
    }

}
