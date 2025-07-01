package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public List<Advert> findByTitleContains(String query) {
        return map.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
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