package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryAdvertRepository implements AdvertRepository {
    private final Map<Long, Advert> map = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<Advert> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Advert> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Advert save(Advert advert) {
        if (advert.getId() == null) {
            advert.setId(idGen.getAndIncrement());
        }
        map.put(advert.getId(), advert);
        return advert;
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);
    }
}
