package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.dto.AdvertDto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryAdvertRepository implements AdvertRepository {
    private final Map<Long, AdvertDto> adverts = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<AdvertDto> findAll() {
        return new ArrayList<>(adverts.values());
    }

    @Override
    public Optional<AdvertDto> findById(Long id) {
        return Optional.ofNullable(adverts.get(id));
    }

    @Override
    public AdvertDto save(AdvertDto advert) {
        if (advert.getId() == null) {
            advert.setId(idGenerator.getAndIncrement());
        }
        adverts.put(advert.getId(), advert);
        return advert;
    }

    @Override
    public boolean deleteById(Long id) {
        return adverts.remove(id) != null;
    }
} 