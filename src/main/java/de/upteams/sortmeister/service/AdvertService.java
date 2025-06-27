package de.upteams.sortmeister.service;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository repository;

    public AdvertService(AdvertRepository repository) {
        this.repository = repository;
    }

    public List<Advert> findAll() {
        return repository.findAll();
    }


    public Optional<Advert> findById(Long id) {
        return repository.findById(id);
    }


    public Advert save(Advert advert) {
        return repository.save(advert);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public Advert update(Long id, Advert advert) {
        if (repository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Advert not found");
        }
        return repository.update(id, advert);
    }
}
