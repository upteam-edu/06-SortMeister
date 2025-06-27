package de.upteams.sortmeister.repository;
import de.upteams.sortmeister.model.Advert;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository {
    public List<Advert> findAll();
    public Optional<Advert>  findById(Long id);
    public Advert save(Advert advert);
    public void deleteById(Long id);
    public Advert update(Long id, Advert advert);
}
