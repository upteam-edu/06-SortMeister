package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository {

    List<Advert> findAll();

    Optional<Advert> findByTitle(String title);

    Advert save(Advert advert);

    void deleteByTitle(String title);
}
