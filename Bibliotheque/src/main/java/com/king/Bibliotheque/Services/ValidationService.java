package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.User;
import com.king.Bibliotheque.Models.Validation;
import com.king.Bibliotheque.Repositories.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void enregistre(User user) {
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randominteger = random.nextInt(999999);
        String code = String.format("%06d", randominteger);
        validation.setCode(code);

        this.validationRepository.save(validation);
        this.notificationService.send(validation);

    }
    public Validation lireFctDuCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("your validation code is incorrect "));
    }
}
