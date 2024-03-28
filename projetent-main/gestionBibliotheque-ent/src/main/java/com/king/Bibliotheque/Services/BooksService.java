package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BooksService {
    Books saveAttachment(MultipartFile file,Books book) throws Exception;

    Books getAttachment(String fileId) throws Exception;

    List<Books> search();

    Books getBookById(int id);

    void deleteBook(int id);
}
