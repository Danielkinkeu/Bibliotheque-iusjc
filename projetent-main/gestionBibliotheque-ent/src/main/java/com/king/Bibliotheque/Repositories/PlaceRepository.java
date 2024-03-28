package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByLine(String line);
    Place findByColumn(String column);
}
