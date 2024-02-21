package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Repositories.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BooksService {
    private BooksRepository booksRepository;

    public void addBooks( Books book){
        if (booksRepository.findByIsbn(book.getIsbn()) != null) {
            throw new RoleException("book  already exist");
        }
        this.booksRepository.save(book);
    }
}
