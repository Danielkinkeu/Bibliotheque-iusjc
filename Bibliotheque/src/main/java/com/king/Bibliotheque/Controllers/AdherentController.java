package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Adherent;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Services.AdherentService;
import com.king.Bibliotheque.Services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
public class AdherentController {
    private AdherentService adherentService;
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "adherent")
    public void addAdherent(@RequestBody Adherent adherent){
        adherentService.addAdherent(adherent);
    }

}
