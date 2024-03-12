package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.TypeNoteCCRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service

public class TypeNoteCCServiceImpl implements ITypeNoteCC {
    @Autowired
    TypeNoteCCRepository typeNoteCCRepository;
    @Autowired
    IUtilisateur iUtilisateur;
    @Autowired
    IEnseignement iEnseignement;

    @Override
    public Long saveTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException {
        Utilisateur createur = iUtilisateur.getUtilisateurByCode(typeNoteCC.getCreateur().getCode());
        Utilisateur modificateur = createur;
        typeNoteCC.setCreateur(createur);
        typeNoteCC.setModificateur(modificateur);
        TypeNoteCC typeNoteCCSave = typeNoteCCRepository.save(typeNoteCC);
        if (typeNoteCCSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return typeNoteCCSave.getCode();
    }

    @Override
    public List<TypeNoteCC> listTypeNoteCC() {
        return typeNoteCCRepository.findAll();
    }

    @Override
    public int deleteTypeNoteCC(Long code) throws IsjException {
        typeNoteCCRepository.deleteById(typeNoteCCRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public TypeNoteCC searchTypeNoteCCByEnseignementOrNumero_CC(Long codeEnseignement, int numero_cc) throws IsjException {
        Enseignement enseignement = iEnseignement.getEnseignementByCode(codeEnseignement);
        return typeNoteCCRepository.retrouverTypeNoteCC(enseignement,numero_cc).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Long updateTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException {
        Utilisateur createur = iUtilisateur.getUtilisateurByCode(typeNoteCC.getCreateur().getCode());
        Utilisateur modificateur = iUtilisateur.getUtilisateurByCode(typeNoteCC.getModificateur().getCode());

        typeNoteCC.setCreateur(createur);
        typeNoteCC.setModificateur(modificateur);
        TypeNoteCC typeNoteCCUpdate = typeNoteCCRepository.save(typeNoteCC);
        if (typeNoteCCUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return typeNoteCCUpdate.getCode();
    }

    @Override
    public TypeNoteCC getTypeNoteCCByCode(Long code) throws IsjException {
        return typeNoteCCRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
