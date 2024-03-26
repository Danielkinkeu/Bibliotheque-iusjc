package com.king.Bibliotheque.Models;
import jakarta.mail.Multipart;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Books {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private String authorName;
    private String edition;
    private Date publicationDate;
    private String fileType;
    //private MultipartFile file;
    @ManyToOne
    @JoinColumn(name = "category_Id", referencedColumnName = "id")
    private Category category;

    @Lob
    private byte[] data;
    public Books(String title, String authorName, String edition, Date publicationDate, String fileType, byte[] data) {
        this.title = title;
        this.authorName = authorName;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.fileType = fileType;
        this.data = data;

    }
}
