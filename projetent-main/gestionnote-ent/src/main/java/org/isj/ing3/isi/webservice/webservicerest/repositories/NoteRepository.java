package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query (value = "SELECT * FROM Note n WHERE n.est_inscrit=:inscrit AND n.evaluation=:eval", nativeQuery = true)
    public Optional<Note> retrouverNote(@Param("inscrit") Long inscrit, @Param("eval") Long eval);

    @Query(value = "select * from note limit 5", nativeQuery = true)
    List<Note>  listeNotes();

    @Query (value = "select * from note " +
            "where code in (select note.code\n" +
            "from note\n" +
            "         inner join evaluation on note.evaluation=evaluation.code\n" +
            "         inner join type_evaluation on evaluation.type_evaluation=type_evaluation.code\n" +
            "         inner join est_inscrit on note.est_inscrit=est_inscrit.code\n" +
            "         inner join candidat on est_inscrit.candidat_inscrit=candidat.code\n" +
            "         inner join etudiant on candidat.code=etudiant.code\n" +
            "         inner join enseignement on type_evaluation.enseignement=enseignement.code\n" +
            "         inner join ue on enseignement.ue=ue.code\n" +
            "         inner join module on ue.module=module.code\n" +
            "         inner join niveau on ue.niveau=niveau.code\n" +
            "         inner join specialite on ue.specialite=specialite.code\n" +
            "         inner join filiere on specialite.filiere=filiere.code\n" +
            "         inner join semestre on enseignement.semestre=semestre.code\n" +
            "         inner join annee_academique on semestre.annee_academique=annee_academique.code\n" +
            " where filiere.libelle=:libellefiliere\n" +
            "  and specialite.libelle=:libellespecialite\n" +
            "  and niveau.numero=:numero\n" +
            "  and extract(year from annee_academique.date_debut)=:anneDebut\n" +
            "  and semestre.libelle=:sem\n" +
            "and type_evaluation.libelle=:typeNot\n" +
            "and ue.code_ue=:codeUe\n" +
            "order by candidat.nom) ", nativeQuery = true)
    List<Note> listNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(@Param("libellefiliere") String libellefiliere, @Param("libellespecialite") String libellespecialite, @Param("numero") int numero, @Param("anneDebut") int anneDebut, @Param("sem") String libelleSemestre, @Param("typeNot") String libelleTypeNote, @Param("codeUe") String codeUe);

    @Query (value = "select * from note " +
            "where code in (select note.code\n" +
            "from note\n" +
            "         inner join evaluation on note.evaluation=evaluation.code\n" +
            "         inner join type_evaluation on evaluation.type_evaluation=type_evaluation.code\n" +
            "         inner join est_inscrit on note.est_inscrit=est_inscrit.code\n" +
            "         inner join candidat on est_inscrit.candidat_inscrit=candidat.code\n" +
            "         inner join etudiant on candidat.code=etudiant.code\n" +
            "         inner join enseignement on type_evaluation.enseignement=enseignement.code\n" +
            "         inner join ue on enseignement.ue=ue.code\n" +
            "         inner join module on ue.module=module.code\n" +
            "         inner join niveau on ue.niveau=niveau.code\n" +
            "         inner join specialite on ue.specialite=specialite.code\n" +
            "         inner join filiere on specialite.filiere=filiere.code\n" +
            "         inner join semestre on enseignement.semestre=semestre.code\n" +
            "         inner join annee_academique on semestre.annee_academique=annee_academique.code\n" +
            " where filiere.libelle=:libellefiliere\n" +
            "  and specialite.libelle=:libellespecialite\n" +
            "  and niveau.numero=:numero\n" +
            "  and extract(year from annee_academique.date_debut)=:anneDebut\n" +
            "order by candidat.nom) ", nativeQuery = true)
    List<Note> listNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(@Param("libellefiliere") String libellefiliere, @Param("libellespecialite") String libellespecialite, @Param("numero") int numero, @Param("anneDebut") int anneDebut);



}