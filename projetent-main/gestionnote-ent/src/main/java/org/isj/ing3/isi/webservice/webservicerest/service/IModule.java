package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;

import java.util.List;

public interface IModule {

    int saveModule (Module module) throws IsjException;
    List<Module> listModules();
    int deleteModule(Long code) throws IsjException;
    Module getModuleByCode(Long code) throws IsjException;
    int updateModule (Module module) throws IsjException;
    Module getModuleByCodeModule(String codeModule) throws IsjException;
}
