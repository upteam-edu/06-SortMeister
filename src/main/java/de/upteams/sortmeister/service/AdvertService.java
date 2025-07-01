package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.ContainerDto;
import de.upteams.sortmeister.dto.ExtendedItemDto;
import de.upteams.sortmeister.dto.ItemResult;
import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.model.Container;
import de.upteams.sortmeister.model.Item;
import de.upteams.sortmeister.repository.AdvertRepository;
import de.upteams.sortmeister.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Advert> search(String title) {
        return repository.findByTitleContains(title);
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

    public List<Advert> getResults(String title) {
        return search(title).stream().collect(Collectors.toList());
    }
}
