package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Anonymat;


import java.util.List;

public interface IAnonymat {

    int saveAnonymat (Anonymat anonymatDto) throws  IsjException;
    List<Anonymat> listAnonymat();
    int deleteAnonymat(Long code) throws  IsjException;
    Anonymat getAnonymatByCode(Long code) throws IsjException;
    int UpdateAnonymat (Anonymat anonymat ) throws IsjException;
    Anonymat getAnonymatByNumeroAnonymat(String num_anonymat) throws  IsjException;
    List<Anonymat> ListAnonymatByEvaluation(Long code) throws  IsjException;
    /*Anonymat getAnonymatByCodeInscritAndCodeEvaluaion(Long code,Long code1) throws  IsjException;*/
}
