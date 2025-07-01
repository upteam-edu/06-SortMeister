package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.model.Item;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository {
    List<Advert> findAll();
    Optional<Advert> findById(Long id);
    List<Advert> findByTitleContains(String query);
    Advert save(Advert advert);
    void deleteById(Long id);
}