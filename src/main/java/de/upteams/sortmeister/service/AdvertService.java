package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.dto.CreateAdvertRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AdvertService {
    private final Map<Long, AdvertDto> adverts = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<AdvertDto> findAll() {
        return new ArrayList<>(adverts.values());
    }

    public AdvertDto create(CreateAdvertRequest request) {
        Long id = idGenerator.getAndIncrement();
        AdvertDto advert = new AdvertDto(id, request.getTitle(), request.getDescription(), request.getPhoto());
        adverts.put(id, advert);
        return advert;
    }

    public Optional<AdvertDto> update(Long id, CreateAdvertRequest request) {
        AdvertDto advert = adverts.get(id);
        if (advert == null) {
            return Optional.empty();
        }
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setPhoto(request.getPhoto());
        return Optional.of(advert);
    }

    public boolean delete(Long id) {
        return adverts.remove(id) != null;
    }
} 