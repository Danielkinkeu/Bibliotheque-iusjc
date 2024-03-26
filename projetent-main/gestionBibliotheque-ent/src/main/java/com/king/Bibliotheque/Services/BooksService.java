package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.Books;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BooksService {
    Books saveAttachment(MultipartFile file,Books book) throws Exception;

    Books getAttachment(String fileId) throws Exception;

    List<Books> search();
}
