package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;

import java.util.List;

public interface ITypeNoteCC {
    Long saveTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException;
    List<TypeNoteCC> listTypeNoteCC();
    int deleteTypeNoteCC(Long code) throws IsjException;
    TypeNoteCC searchTypeNoteCCByEnseignementOrNumero_CC(Long enseignement, int numero_cc) throws IsjException;
    Long updateTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException;
    TypeNoteCC getTypeNoteCCByCode(Long code) throws IsjException;

}
