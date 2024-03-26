// var token = '[[${accessToken}]]'
var fileName = 'load'
var listeObjets = [ ];


// Appeler la fonction pour charger les options lors du chargement de la page
$(document).ready(function() {
    // token=$('#token').text();
    console.log(token);
    // console.log(matricule);
    listeIntervention();
    $('#yourFormId').submit(function(event) {
        event.preventDefault(); // Empêche la soumission normale du formulaire

        var codeEtudiant = 1 // Récupérez la valeur du champ code étudiant
        var idCategorie = 2// Récupérez la valeur du champ id catégorie

        poserintervention(codeEtudiant, idCategorie);
    });

});
// Attacher un gestionnaire d'événement à l'élément select
$('#intervention').change(
    function() {
    // Récupérer la valeur sélectionnée
    var idCategorie = $(this).val();
    // Afficher la valeur dans la console (vous pouvez faire autre chose avec cette valeur)
    console.log("La valeur sélectionnée est : " + idCategorie);
    listesousIntervention(idCategorie)
}

);
$('#file').change(function (event) {
    loadFile(event);
});



function chargerOptionsSelectIntervention(liste,id) {
    var select = $(id);
    // Effacer les options existantes
    // select.empty();
    // Ajouter les nouvelles options à partir de la liste d'objets
    $.each(liste, function(index, objet) {
        select.append('<option value="' + objet.idCategorie + '">' + objet.intituleCategorie + '</option>');
    });
}
function chargerOptionsSelectSIntervention(liste,id) {
    var select = $(id);
    // Effacer les options existantes
    select.empty();
    // Ajouter les nouvelles options à partir de la liste d'objets
    $.each(liste, function(index, objet) {
        select.append('<option value="' + objet.idSousIntervention + '">' + objet.intituleSousIntervention + '</option>');
    });
}
function listeIntervention(){
    $.ajax({
        url: 'http://localhost:9090/api/interventions/categorie/Liste',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            listeObjets=data
            console.log('Réponse de l\'API:', data);
            chargerOptionsSelectIntervention(listeObjets,'#intervention');
            // Vous pouvez manipuler les données ici
        },
        error: function(xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
// var accessToken = ''  ;
function listesousIntervention(idCategorie){
    $.ajax({
        url: 'http://localhost:9090/api/interventions/categorie/sousIntervention/'+idCategorie,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            listeObjets=data
            console.log('Réponse de l\'API:', data);
            chargerOptionsSelectSIntervention(listeObjets,'#sous-intervention');
            // Vous pouvez manipuler les données ici
        },
        error: function(xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
function interventionbyId(id){
    $.ajax({
        url: 'http://localhost:9090/api/interventions/'+id,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            console.log('Réponse de l\'API:', data);
            // Vous pouvez manipuler les données ici
        },
        error: function(xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
function afficherIntervention(intervention){
    console.log(intervention)
}

// var accessToken = '
var selectedFiles
function loadFile(event){
    selectedFiles = event.target.files;
    console.log(selectedFiles);

    if (selectedFiles.length > 0) {
        fileName = selectedFiles[0].name + " ' et " + (selectedFiles.length - 1) + " autres fichier(s)'";
        console.log(fileName);

        $('#download').text(fileName);
        // Vous pouvez utiliser la variable fileName comme nécessaire ici
    }
}
function poserintervention(codeEtudiant, idCategorie,dataS){
    var url = 'http://localhost:9090/api/interventions/soumettre/' + codeEtudiant + '/' + idCategorie;
    $.ajax({
        url: url,
        type: 'POST',
        data: dataS,
        processData: false, // empêche jQuery de transformer les données en chaîne de requête
        contentType: false,
        success: function(data) {
            console.log('Réponse de l\'API:', data);
            console.log('Réponse de l\'API: Réponse de l\'API: Réponse de l\'API: Réponse de l\'API:');
            // Manipuler les données ici
        },
        error: function(xhr, status, error) {
            console.error('Erreur lors de la requête API:', status, error);
        }
    });
}
function envoyer(){
    console.log(matricule)
    console.log('session')
    var intervention = document.getElementById('intervention').value;
    var idSousIntervention = document.getElementById('sous-intervention').value;
    var DescriptionIntervention = document.getElementById('exampleInputUsername1').value;
    var piecesJointes = document.getElementById('file').files;

    var formData = new FormData();

// Ajout des valeurs au FormData
    formData.append('intervention', intervention);
    formData.append('idSousIntervention', idSousIntervention);
    formData.append('DescriptionIntervention', DescriptionIntervention);

    for (var key in selectedFiles) {
        if (selectedFiles.hasOwnProperty(key)) {
            var file = selectedFiles[key];
            // Faites quelque chose avec chaque fichier
            formData.append('pieceJointe', file);
            console.log(file)
        }
    }

    console.log(DescriptionIntervention);
    console.log(selectedFiles);
    poserintervention(matricule,intervention,formData)
}
