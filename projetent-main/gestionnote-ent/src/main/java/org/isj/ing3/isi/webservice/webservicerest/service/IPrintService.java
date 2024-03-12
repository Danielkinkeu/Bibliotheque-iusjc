package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.*;

import java.io.File;

public interface IPrintService {

    File generateCarteEtudiant(CarteEtudiant carteEtudiant) throws Exception;
    byte[] generateAttestationReusite(AttestationEtDiplome attestation) throws Exception;
    byte[] generateDiplome(AttestationEtDiplome diplome) throws Exception;
    byte[] generateFicheAbsences(FicheAbsence ficheAbsence) throws Exception;
    byte[] generatePv(Pv pv) throws Exception;

    byte[] generateListe(Pv pv) throws Exception;

    byte[] generateNotes() throws Exception;
    byte[] generateReleveNote(ReleveNote note) throws Exception;
    byte[] generatePVSynthese(PVSynthese pvSynthese) throws Exception;

    byte[] generateCertificatScolarite(ReleveNote releveNote) throws Exception;
}
