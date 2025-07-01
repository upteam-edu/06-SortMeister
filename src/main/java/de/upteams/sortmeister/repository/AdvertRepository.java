package de.upteams.sortmeister.repository;

import de.upteams.sortmeister.dto.AdvertDto;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository {
    List<AdvertDto> findAll();
    Optional<AdvertDto> findById(Long id);
    AdvertDto save(AdvertDto advert);
    boolean deleteById(Long id);
} 