package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.UE;

import java.util.List;

public interface IUE{

    int saveUE (UE ue) throws IsjException;
    List<UE> listUEs();
    int deleteUE(Long code) throws IsjException;
    UE getUEByCode(Long code) throws IsjException;

}
