package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {
    Books findByIsbn(String isbn);
}
