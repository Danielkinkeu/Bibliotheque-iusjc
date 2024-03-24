package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Services.BooksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = "books")
@AllArgsConstructor
public class BooksController {
    private static final Logger LOG = LoggerFactory.getLogger(BooksController.class);
    private final  BooksService booksService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addBooks(@ModelAttribute Books book){
        LOG.debug("Adding document");
        this.booksService.addBooks(book);
        LOG.debug("document Added");
    }
}
