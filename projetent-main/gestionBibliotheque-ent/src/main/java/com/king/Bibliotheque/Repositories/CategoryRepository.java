package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByTitle(String title);
}
