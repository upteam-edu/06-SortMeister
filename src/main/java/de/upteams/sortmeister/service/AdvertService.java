package de.upteams.sortmeister.service;

import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public List<Advert> getAll() {
        return advertRepository.findAll();
    }

    public Optional<Advert> getByTitle(String title) {
        return advertRepository.findByTitle(title);
    }

    public Advert create(Advert advert) {
        return advertRepository.save(advert);
    }

    public Advert update(String title, Advert advert) {
        return advertRepository.save(advert);
    }

    public void delete(String title) {
        advertRepository.deleteByTitle(title);
    }
}
