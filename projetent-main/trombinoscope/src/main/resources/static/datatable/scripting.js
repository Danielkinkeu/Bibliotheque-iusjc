
$(document).ready(function() {
  $('#categorie').DataTable();
  $('#departement').DataTable();
  $('#souscategorie').DataTable();
});



$('#btn-submit').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var nom = $("#nom").val();
    if(!nom){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom de la catégorie avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement enregistrer la catégorie <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/saveCat",
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant l'insertion.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
            console.error("An error occurred:", error);
        });
    }
});


function readyUpdate(id){
    var result;
    $.ajax({
        url: "/getOneCat/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        $("#update-id").val(response.id);
        $("#update-nom").val(response.nom);
        console.log(response);
    })
}


$('#btn-update').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var id = $("#update-id").val();
    var nom = $("#update-nom").val();
    if(!nom){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom de la catégorie avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement modifier cette catégorie en : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                url: "/updateCat/"+ id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant la modification.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
            console.error("An error occurred:", error);
        });
    }
});







$('#btn-submit-dep').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var nom = $("#departement_nom").val();
    if(!nom){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom du département avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement enregistrer le département <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/addDep",
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant l'insertion.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
            console.error("An error occurred:", error);
        });
    }
});

function readyUpdateDep(id){
    var result;
    $.ajax({
        url: "/oneDep/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        $("#update_departement_id").val(response.id);
        $("#update_departement_nom").val(response.nom);
        console.log(response);
    })
}

$('#btn-update-dep').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var id = $("#update_departement_id").val();
    var nom = $("#update_departement_nom").val();
    if(!nom){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom du département avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement modifier ce département en : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                url: "/updateDep/"+ id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant la modification.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
        });
    }
});







$('#btn-add-souscategorie').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var categorie_id = $("#categorie_id_save").val();
    var departement_id = $("#departement_id_save").val();
    var nom = $("#souscategorie_nom_save").val();

    console.log(nom, departement_id, categorie_id)

    if((!categorie_id) || (!departement_id) || (!nom)){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné toutes les informations avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement enregistrer la sous-catégorie : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/addsCat/"+categorie_id+"/"+departement_id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant l'insertion.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
            console.error("An error occurred:", error);
        });
    }
});


function readyUpdateSousCat(id){
    var result;
    $.ajax({
        url: "/onesCat/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        $('#update_categorie_id').append("<option selected value="+ response.categorie.id +">"+ response.categorie.nom +"</option>")
        $('#update_departement_id').append("<option selected value="+ response.departement.id +">"+ response.departement.nom +"</option>")
        $('#update_souscategorie_nom').val(response.nom)
        $('#update_souscategorie_id').val(response.id)
        console.log(response);
    })
}


$('#btn-update-souscategorie').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var id = $("#update_souscategorie_id").val();
    var categorie_id = $("#update_categorie_id").val();
    var departement_id = $("#update_departement_id").val();
    var nom = $("#update_souscategorie_nom").val();
    if((!categorie_id) ||(!departement_id) ||(!nom)){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom du département avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement modifier cette sous-catégorie en : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                url: "/updatesCat/"+ id + "/"+ categorie_id + "/"+ departement_id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant la modification.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
        });
    }
});







$('#btn-add-personnel').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var categorie_id = $("#categorie_id_save").val();
    var departement_id = $("#departement_id_save").val();
    var nom = $("#souscategorie_nom_save").val();

    console.log(nom, departement_id, categorie_id)

    if((!categorie_id) || (!departement_id) || (!nom)){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné toutes les informations avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement enregistrer la sous-catégorie : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/addsCat/"+categorie_id+"/"+departement_id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant l'insertion.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
            console.error("An error occurred:", error);
        });
    }
});


function readyActivePeronnel(id){
    $.ajax({
        url: "/onePers/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        console.log(response);
    })
}


function readyInactivePeronnel(id){
    $.ajax({
        url: "/onePers/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        console.log(response);
    })
}


function readyUpdateSousCat(id){
    var result;
    $.ajax({
        url: "/onesCat/"+id,
        method: "GET",
        dataType : "json",
    }).done(function(response){
        $('#update_categorie_id').append("<option selected value="+ response.categorie.id +">"+ response.categorie.nom +"</option>")
        $('#update_departement_id').append("<option selected value="+ response.departement.id +">"+ response.departement.nom +"</option>")
        $('#update_souscategorie_nom').val(response.nom)
        $('#update_souscategorie_id').val(response.id)
        console.log(response);
    })
}


$('#btn-update-souscategorie').on('click',function(e){
    e.preventDefault();
    var form = $(this).parents('form');
    var id = $("#update_souscategorie_id").val();
    var categorie_id = $("#update_categorie_id").val();
    var departement_id = $("#update_departement_id").val();
    var nom = $("#update_souscategorie_nom").val();
    if((!categorie_id) ||(!departement_id) ||(!nom)){
        Swal.fire({
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            title: "Erreur...",
            text: "Veillez d'abord renseigné le nom du département avant de continuer!",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, c'est compris.",
        });
    }
    else{
        Swal.fire({
            title: "Êtes vous sûres?",
            html: "Souhaitez vous réelement modifier cette sous-catégorie en : <b> " + nom + " </b>?",
            hideOnOverlayClick: false,
            hideOnContentClick: false,
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Je le souhaite",
            closeOnConfirm: false
        }).then(() => {
            return $.ajax({
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                url: "/updatesCat/"+ id + "/"+ categorie_id + "/"+ departement_id,
                data: JSON.stringify({'nom' : nom}),
                cache: false
            })
        }).then((response) => {
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: response,
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            }).then(function (result) {
                if (result.isConfirmed) {
                    location.reload();
                }
            });
        }).catch((error) => {
            Swal.fire({
                position: 'center',
                title: 'Erreur',
                html: "Des erreurs sont survenue durant la modification.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
        });
    }
});



function readyToTakeChargeOfRequest(personnel_id, demande_id){
    console.log(personnel_id, demande_id)
    Swal.fire({
        title: "Êtes vous sûres?",
        html: "Souhaitez vous réelement prendre en charge cette demande ?",
        hideOnOverlayClick: false,
        hideOnContentClick: false,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Je le souhaite",
        closeOnConfirm: false
    }).then(() => {
        return $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/prisEnchargeDem/"+personnel_id+"/"+demande_id,
            cache: false
        })
    }).then((response) => {
        Swal.fire({
            position: 'center',
            title: 'Félicitation',
            html: response,
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        }).then(function (result) {
            if (result.isConfirmed) {
                location.reload();
            }
        });
    }).catch((error) => {
        Swal.fire({
            position: 'center',
            title: 'Erreur',
            html: "Des erreurs sont survenue durant la prise en charge de la demande.",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        })
        console.error("An error occurred:", error);
    });
}



function readyToApproveChargeOfRequest(personnel_id, demande_id){
    console.log(personnel_id, demande_id)
    Swal.fire({
        title: "Êtes vous sûres?",
        html: "Souhaitez vous réelement marqué cette demande comme une demande finalisée ?",
        hideOnOverlayClick: false,
        hideOnContentClick: false,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Je le souhaite",
        closeOnConfirm: false
    }).then(() => {
        return $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/approuverDem/"+personnel_id+"/"+demande_id,
            cache: false
        })
    }).then((response) => {
        Swal.fire({
            position: 'center',
            title: 'Félicitation',
            html: response,
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        }).then(function (result) {
            if (result.isConfirmed) {
                location.reload();
            }
        });
    }).catch((error) => {
        Swal.fire({
            position: 'center',
            title: 'Erreur',
            html: "Des erreurs sont survenue durant l'approbation de la demande.",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        })
        console.error("An error occurred:", error);
    });
}



function readyToRejetChargeOfRequest(personnel_id, demande_id){
    console.log(personnel_id, demande_id)
    Swal.fire({
        title: "Êtes vous sûres?",
        html: "Souhaitez vous réelement rejetté cette demande d'intervenction ?",
        hideOnOverlayClick: false,
        hideOnContentClick: false,
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Je le souhaite",
        closeOnConfirm: false
    }).then(() => {
        return $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/rejetterDem/"+personnel_id+"/"+demande_id,
            cache: false
        })
    }).then((response) => {
        Swal.fire({
            position: 'center',
            title: 'Félicitation',
            html: response,
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        }).then(function (result) {
            if (result.isConfirmed) {
                location.reload();
            }
        });
    }).catch((error) => {
        Swal.fire({
            position: 'center',
            title: 'Erreur',
            html: "Des erreurs sont survenue durant le rejet de la demande.",
            confirmButtonColor: "#3085d6",
            confirmButtonText: "D'accord, merci.",
        })
        console.error("An error occurred:", error);
    });
}



function readyUploadFile(id){
    $("#id-demande-file-upload").val(id);
}







$(document).ready(function () {
     $("#login-submit").on("click", function (e) {
        e.preventDefault();
        var login = $('#login-input').val();
        var password = $('#password-input').val();

        if((login=='') || (password == '')){
            Swal.fire({
                position: 'center',
                title: 'Authentification Echouée',
                html: "S'il vous plait renseignez votre <b>Identifiant</b> et votre <b>Mot de passe</b> avant de continuer.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
            })
        }
        else{
            Swal.fire({
                title: "Êtes vous sûres?",
                html: "Souhaitez vous réelement continuer votre connexion  ?",
                hideOnOverlayClick: false,
                hideOnContentClick: false,
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Je le souhaite",
                closeOnConfirm: false
            }).then(() => {
                return $.ajax({
                    type: "GET",
                    url: "/authentification/"+login+"/"+password,
                    contentType: "application/json; charset=utf-8",
                    cache: false
                })
            }).then((response) => {
                localStorage.setItem('account', JSON.stringify(response));
                window.location.href = '/home';
            }).catch((error) => {
                Swal.fire({
                    position: 'center',
                    title: 'Connexion échouée',
                    html: "Des erreurs sont survenue durant votre connexion veillez réeseillez plutard.",
                    confirmButtonColor: "#3085d6",
                    confirmButtonText: "D'accord, merci.",
                })
            });
        }
     });
});