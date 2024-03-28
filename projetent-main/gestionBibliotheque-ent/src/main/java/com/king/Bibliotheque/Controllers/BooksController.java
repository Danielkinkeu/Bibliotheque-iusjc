package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Books;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Services.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import com.king.Bibliotheque.ResponseData;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
//@RequestMapping(path = "books")
@AllArgsConstructor
public class BooksController {
    private BooksService booksService;

    @PostMapping()
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file, @RequestBody Books book) throws Exception {
        System.out.println("Upload"+ file);
        System.out.println("books"+ book);
        Books attachment = null;
        String downloadURl = "";
        attachment = booksService.saveAttachment(file,book);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getTitle(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Books attachment = null;
        attachment = booksService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getTitle()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));

    }

    @GetMapping(path = "book", produces = APPLICATION_JSON_VALUE)
    public List<Books> getBooks(){
        return this.booksService.search();
    }

    @GetMapping(path = "book/{id}", produces = APPLICATION_JSON_VALUE)
    public Books getBookById(@PathVariable int id){
        return this.booksService.getBookById(id);
    }

    @DeleteMapping(path = "book/delete/{id}")
    public void deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
    }

}
