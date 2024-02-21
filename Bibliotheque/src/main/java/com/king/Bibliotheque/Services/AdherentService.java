package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Models.Adherent;
import com.king.Bibliotheque.Models.Role;
import com.king.Bibliotheque.Models.User;
import com.king.Bibliotheque.Repositories.AdherentRepository;
import com.king.Bibliotheque.Repositories.UserRepository;
import com.king.Bibliotheque.RoleType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class AdherentService {
    private AdherentRepository adherentRepository;
    private UserRepository userRepository;
    private ValidationService validationService;
    private BCryptPasswordEncoder passwordEncoder;
    public AdherentService(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public void addAdherent( Adherent adherent){
        System.out.println(adherent.getName());
        if (adherentRepository.findByPseudo(adherent.getPseudo()) != null) {
            throw new RoleException("pseudo is already taken");
        }
        try{
        Adherent ad = new Adherent();
        ad.setPseudo(adherent.getPseudo());
        ad.setAdherentType(adherent.getAdherentType());
        ad.setSubscriptionDate(adherent.getSubscriptionDate());
        ad.setMembershipExpirationDate(adherent.getMembershipExpirationDate());
        ad.setNumberLoansAuthorized(adherent.getNumberLoansAuthorized());
        ad.setMaximumLoanDuration(adherent.getMaximumLoanDuration());

        this.adherentRepository.save(ad);
        }catch(Exception e){
            System.out.println("Error: Save  adherent error!");
        }

        try{
            User user = new User();
            user.setName(adherent.getName());
            user.setMatricule(adherent.getMatricule());
            //user.setMdp(adherent.getMdp());
            user.setSubName(adherent.getSubName());
            user.setEmail(adherent.getEmail());
            user.setBirthDate(adherent.getBirthDate());
            user.setPhone(adherent.getPhone());
            user.setAddress(adherent.getAddress());

            String mdpCrypte = this.passwordEncoder.encode(adherent.getMdp());
            user.setMdp(mdpCrypte);

            Role userRole = new Role();
            userRole.setTitle(RoleType.STUDENT);
            user.setRole(userRole);
            user = this.userRepository.save(user);
            this.validationService.enregistre(user);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
