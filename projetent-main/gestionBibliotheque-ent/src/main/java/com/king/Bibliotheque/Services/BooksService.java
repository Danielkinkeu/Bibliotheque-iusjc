package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Repositories.BooksRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class BooksService {
    private BooksRepository booksRepository;

    public void addBooks( Books book){
        if (booksRepository.findByIsbn(book.getIsbn()) != null) {
            throw new RoleException("book  already exist");
        }
        Books doc = new Books();
        doc.setIsbn(book.getIsbn());
        doc.setTitle(book.getTitle());
        doc.setAuthorName(book.getAuthorName());
        doc.setEdition(book.getEdition());
        doc.setPublicationDate(book.getPublicationDate());
        //doc.setSize(book.getFile().getSize());
        //doc.setMimeType(book.getFile().getContentType());
        //doc.setFile(book.getFile());
        this.booksRepository.save(book);
    }
}
