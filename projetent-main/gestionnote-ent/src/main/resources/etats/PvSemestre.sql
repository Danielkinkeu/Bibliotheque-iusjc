create
    definer = bd_user@`%` procedure PvSemestre(IN fil varchar(255), IN niv int, IN an int, IN sem varchar(255),
                                            IN specialites varchar(255))
begin

    delete from etudiant_semestre;
delete from etudiant_module;
delete from etudiant_moyenne_ue;
delete from etudiant_ue_note;
delete from tmp_ue;


INSERT INTO etudiant_ue_note(matricule, nom_prenom, code_module,module_libelle, code_ue, ue_libelle, credit, type_evaluation, pourcentage, valeur_note, semestre, annee, filiere, numero)
select etudiant.matricule as matricule,CONCAT(UPPER(nom)," ",UPPER(prenom)) as nom_prenom,module.code_module as code_module,module.libelle as module,ue.code_ue as code_ue
     ,ue.libelle as ue_libelle ,ue.credits as credit,type_evaluation.libelle as type_evaluation,type_evaluation.pourcentage as pourcentage,valeur_note,semestre.libelle as semestre,extract(year from annee_academique.date_debut) as annee,filiere.libelle as filiere,niveau.numero as numero
from note
inner join evaluation on note.evaluation=evaluation.code
inner join type_evaluation on evaluation.type_evaluation=type_evaluation.code
inner join est_inscrit on note.est_inscrit=est_inscrit.code
inner join candidat on est_inscrit.candidat_inscrit=candidat.code
inner join etudiant on candidat.code=etudiant.code
inner join enseignement on type_evaluation.enseignement=enseignement.code
inner join ue on enseignement.ue=ue.code
inner join module on ue.module=module.code
inner join niveau on ue.niveau=niveau.code
inner join specialite on ue.specialite=specialite.code
inner join filiere on specialite.filiere=filiere.code
inner join semestre on enseignement.semestre=semestre.code
inner join annee_academique on semestre.annee_academique=annee_academique.code
where filiere.libelle=fil
and specialite.libelle=specialites
and niveau.numero=niv
and extract(year from annee_academique.date_debut)=an
and semestre.libelle=sem
order by candidat.nom,module.code_module,ue.code_ue,type_evaluation.libelle;


#moyenne par ue
INSERT INTO etudiant_moyenne_ue (matricule, nom_prenom, code_module, ue_libelle, code_ue, credit, moyenne, credit_obtenu, grade, decision_ue)
Select matricule, nom_prenom,code_module,ue_libelle,code_ue,credit,(ROUND(sum(valeur_note*pourcentage/100),2)- penalites(matricule,an,sem))as moyenne,(case when ((sum(valeur_note*pourcentage/100)- penalites(matricule,an,sem))>=9) then credit else 0 end )as credit_obtenu,grade((sum(valeur_note*pourcentage/100)- penalites(matricule,an,sem))) as grade,
decision((sum(valeur_note*pourcentage/100)- penalites(matricule,an,sem))) decision_ue
from etudiant_ue_note
group by nom_prenom,matricule,code_module,code_ue,ue_libelle,credit;


#moyenne par module
INSERT INTO etudiant_module(nom_prenom, matricule, code_module, credit_module, moyenne_module,grade_module, credit_obtenu, decision_module)
select nom_prenom,matricule,code_module,sum(credit) as credit_module,ROUND(sum(moyenne*credit)/sum(credit),2) as moyenne_module,grade(ROUND(sum(moyenne*credit)/sum(credit),2)) as grade_module,sum(credit_obtenu) as credit_obtenu,case when COUNT(case when moyenne<=9.99 then 1 else null end)=0 then "CA"
when ROUND(sum(moyenne*credit)/sum(credit),2)>=10  and COUNT(case when moyenne<=8.99 then 1 else null end)=0 then "CPC"
 when (ROUND(sum(moyenne*credit)/sum(credit),2))>=9 and (ROUND(sum(moyenne*credit)/sum(credit),2))<10 and COUNT(case when moyenne<=8.99 then 1 else null end)=0 then "CANT"
 when COUNT(case when moyenne<7 then 1 else null end)=0  then "NC"
					else "EL" end as decision_module
from etudiant_moyenne_ue
group by nom_prenom,matricule,code_module;



#etudiant semestre
INSERT INTO etudiant_semestre(matricule, moyenne_sem, mgp_sem, grade_semestre, credit_semestre,penalites)
select matricule,ROUND(sum(moyenne*credit)/sum(credit),2) as moyenne_sem, ROUND(sum(mgp(moyenne)*credit)/sum(credit),2) as mgp_sem,
       grade(ROUND(sum(moyenne*credit)/sum(credit),2)) as grade_sem,sum(credit) as credit_sem,penalites(matricule,an,sem) as penalites
from etudiant_moyenne_ue
group by matricule,nom_prenom
order by nom_prenom;

select en.matricule,en.nom_prenom,en.code_ue,en.valeur_note as note ,en.code_module as codemo,en.module_libelle as module ,en.ue_libelle as ue,en.type_evaluation as intitule,
CONCAT(en.pourcentage,"%")as pourcentage,(em.moyenne) as moyenne,em.decision_ue as deci,grade(em.moyenne) as grade,
(case when en.type_evaluation="SN" then add_tmp2(en.code_ue,en.credit) else 0 END) as credit,en.credit as credi,em.credit as credit_total,(case when type_evaluation="SN" and moyenne>=9 then en.credit else 0 end) as cred2,(case when type_evaluation="SN"then en.credit else 0 end) as cred3 ,em.credit_obtenu as cred,etMo.moyenne_module
,etMo.grade_module,etMo.decision_module as decision,es.mgp_sem as mgp_annuel,es.moyenne_sem,es.penalites,es.grade_semestre
from etudiant_ue_note en inner join etudiant_moyenne_ue em on en.matricule=em.matricule AND en.code_ue = em.code_ue
inner join etudiant_module etMo on em.matricule=etMo.matricule AND em.code_module = etMo.code_module
inner join etudiant_semestre es on en.matricule = es.matricule;

  end;

