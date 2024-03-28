package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleNotFoundException;
import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Repositories.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BooksServiceImpl implements BooksService{
    private BooksRepository booksRepository;

    @Override
    public Books saveAttachment(MultipartFile file, Books book) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String authorName = book.getAuthorName();
        String edition = book.getEdition();
        Date publicationDate = book.getPublicationDate();
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Books attachment = new Books(fileName,authorName, edition, publicationDate, file.getContentType(), file.getBytes());

            return booksRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Books getAttachment(String fileId) throws Exception {
        return booksRepository
                .findById(fileId);
                //.orElseThrow(
                  //      () -> new Exception("File not found with Id: " + fileId));
    }

    public List<Books> search(){
        return this.booksRepository.findAll();
    }

    public Books getBookById(int id) {
        Optional<Books> optionalBook = this.booksRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public void deleteBook(int id) {
        Optional<Books> category = booksRepository.findById(id);
        if (category.isPresent()) {
            booksRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Book not found");
        }
    }
}
