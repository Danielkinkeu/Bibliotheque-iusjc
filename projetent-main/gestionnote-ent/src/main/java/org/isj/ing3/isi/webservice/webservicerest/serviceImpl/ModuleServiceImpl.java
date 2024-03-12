package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.ModuleRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ModuleServiceImpl implements IModule {
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveModule(Module module) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(module.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        module.setCreateur(createur);
        module.setModificateur(modificateur);
        Module moduleSave = moduleRepository.save(module);
        if (moduleSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return moduleSave.getCode().intValue();

    }

    @Override
    public int updateModule (Module module) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(module.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(module.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        module.setCreateur(createur);
        module.setModificateur(modificateur);
        Module moduleUpdate = moduleRepository.save(module);
        if (moduleUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return moduleUpdate.getCode().intValue();
    }

    @Override
    public List<Module> listModules() {
        return moduleRepository.findAll();
    }

    @Override
    public int deleteModule(Long code) throws IsjException {
        moduleRepository.deleteById(getModuleByCode(code).getCode());
        return 1;
    }

    @Override
    public Module getModuleByCode(Long code) throws IsjException {

        return moduleRepository.findById(code).orElseThrow(() -> new IsjException("Module introuvable", Status.NOT_FOUND));
    }

    @Override
    public Module getModuleByCodeModule(String codeModule) throws IsjException {
        return moduleRepository.retrouverModule(codeModule).orElseThrow(() -> new IsjException("Module introuvable", Status.NOT_FOUND)) ;   }
}
