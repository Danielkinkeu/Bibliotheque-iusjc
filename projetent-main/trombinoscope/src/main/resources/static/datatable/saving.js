$(document).ready(function () {
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };

    console.log(localStorage.getItem('account'));
    if (window.location.pathname !== '/') {
        if (localStorage.getItem('account') !== null) {
            var account = JSON.parse(localStorage.getItem('account'));
            if(account.statut == 'etudiant'){
                $("#item-link-personnel1").addClass("d-none");
                $("#item-link-personnel2").addClass("d-none");
                $("#item-link-personnel3").addClass("d-none");
                $("#item-link-personnel4").addClass("d-none");
                $("#item-link-personnel5").addClass("d-none");
                $("#item-link-personnel6").addClass("d-none");
            }
            else if(account.statut == 'personnel'){
                $("#item-link-etudiant1").addClass("d-none");
                $("#item-link-etudiant2").addClass("d-none");
            }
        }
        else {
            window.location.href = '/';
            Swal.fire({
                position: 'center',
                title: 'Connexion illégale',
                html: "Il semblerait que votre accès soit illégal. Vous devez absolument vous connecter.",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "D'accord, merci.",
                allowOutsideClick: false,
                timerProgressBar: true,
            })
        }
    }
});


$('#logout-id-button').on('click',function(e){
    Swal.fire({
        position: 'center',
        title: 'Êtes vous sûres ?',
        html: "Souhaitez vous réelement terminer votre session ?, sachez que cette action est irréversible.",
        confirmButtonText: "D'accord, merci.",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        allowOutsideClick: false,
    }).then(function (result) {
        if (result.isConfirmed) {
            localStorage.removeItem('account');
            window.location.href = '/';
            Swal.fire({
                position: 'center',
                title: 'Félicitation',
                html: 'Déconnexion éffectuée avec succès.',
                icon: 'success',
                timer: 3000,
                allowOutsideClick: false, // Prevent closing by clicking outside the alert
                showConfirmButton: false
            })
        }
    });
})