package de.upteams.sortmeister.service;

import de.upteams.sortmeister.model.Advert;
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

    public List<Advert> getAll() {
        return repository.findAll();
    }

    public Optional<Advert> getById(Long id) {
        return repository.findById(id);
    }

    public Advert create(Advert advert) {
        return repository.save(advert);
    }

    public Advert update(Long id, Advert advert) {
        advert.setId(id);
        return repository.save(advert);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
