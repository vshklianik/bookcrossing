package by.serhel.springwebapp.repositories;

import by.serhel.springwebapp.entities.Advert;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertRepository extends CrudRepository<Advert, Long> {
    List<Advert> findByGenre(String genre)  ;
}
