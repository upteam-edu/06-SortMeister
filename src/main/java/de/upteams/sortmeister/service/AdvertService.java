package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.dto.CreateAdvertRequest;
import de.upteams.sortmeister.repository.AdvertRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository repository;

    public AdvertService(AdvertRepository repository) {
        this.repository = repository;
    }

    public List<AdvertDto> findAll() {
        return repository.findAll();
    }

    public Optional<AdvertDto> findById(Long id) {
        return repository.findById(id);
    }

    public AdvertDto create(CreateAdvertRequest request) {
        AdvertDto advert = new AdvertDto(null, request.getTitle(), request.getDescription(), request.getPhoto());
        return repository.save(advert);
    }

    public Optional<AdvertDto> update(Long id, CreateAdvertRequest request) {
        Optional<AdvertDto> existing = repository.findById(id);
        if (existing.isEmpty()) return Optional.empty();
        AdvertDto advert = existing.get();
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setPhoto(request.getPhoto());
        return Optional.of(repository.save(advert));
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
} 