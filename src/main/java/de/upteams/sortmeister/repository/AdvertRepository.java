package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository {
    List<Advert> findAll();

    Optional<Advert> findById(Long id);

    Advert save(Advert advert);

    void deleteById(Long id);

    Advert update(Long id, Advert advert);
}
