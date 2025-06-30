
package de.upteams.sortmeister.service;

import de.upteams.sortmeister.dto.AdvertDto;
import de.upteams.sortmeister.model.Advert;
import de.upteams.sortmeister.repository.AdvertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;


    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public List<AdvertDto> getAllAdverts() {
        return advertRepository.findAll().stream()
                .map(advert -> new AdvertDto(advert.getId(), advert.getTitle(), advert.getDescription(), advert.getPhotoUrl()))
                .collect(Collectors.toList());
    }

    public Optional<AdvertDto> getAdvertById(Long id) {
        return advertRepository.findById(id)
                .map(advert -> new AdvertDto(advert.getId(), advert.getTitle(), advert.getDescription(), advert.getPhotoUrl()));
    }

    @Transactional
    public AdvertDto createAdvert(AdvertDto advertDto) {
    Advert advert = new Advert(advertDto.title(), advertDto.description(), advertDto.photoUrl());
    Advert savedAdvert = advertRepository.save(advert);
        return new AdvertDto(savedAdvert.getId(), savedAdvert.getTitle(), savedAdvert.getDescription(), savedAdvert.getPhotoUrl());
}

    @Transactional
    public AdvertDto updateAdvert(Long id, AdvertDto updatedAdvertDto) {
        return advertRepository.findById(id)
                .map(advert -> {
                    advert.setTitle(updatedAdvertDto.title());
                    advert.setDescription(updatedAdvertDto.description());
                    advert.setPhotoUrl(updatedAdvertDto.photoUrl());
                    Advert updatedAdvert = advertRepository.save(advert);
                    return new AdvertDto(updatedAdvert.getId(), updatedAdvert.getTitle(), updatedAdvert.getDescription(), updatedAdvert.getPhotoUrl());
                })
                .orElseThrow(() -> new IllegalArgumentException("Advert not found with id " + id));
    }

    @Transactional
    public void deleteAdvert(Long id) {

        advertRepository.deleteById(id);
    }
}