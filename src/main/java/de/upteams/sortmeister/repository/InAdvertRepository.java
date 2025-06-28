package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InAdvertRepository implements AdvertRepository{

    private final Map<Long, Advert> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Advert save(Advert advert) {
        if (advert.getId() == null) {
            advert.setId(idGenerator.incrementAndGet());
        }
        storage.put(advert.getId(), advert);
        return advert;
    }

    @Override
    public List<Advert> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Advert> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public Advert update(Long id, Advert advert) {
        if (!storage.containsKey(id)) {
            throw new NoSuchElementException("Advert with id: " + id + " not found");
        }
        advert.setId(id);
        storage.put(id, advert);
        return advert;
    }
}
