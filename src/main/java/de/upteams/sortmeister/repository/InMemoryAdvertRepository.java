package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAdvertRepository implements AdvertRepository {

    private final Map<String, Advert> map = new ConcurrentHashMap<>();

    @Override
    public List<Advert> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Advert> findByTitle(String title) {
        return Optional.ofNullable(map.get(title));
    }

    @Override
    public Advert save(Advert advert) {
        map.put(advert.getTitle(), advert);
        return advert;
    }

    @Override
    public void deleteByTitle(String title) {
        map.remove(title);
    }
}
