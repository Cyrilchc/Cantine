var productID;
function CreateProduct() {
    libelle = document.getElementById("libelle").value;
    marque = document.getElementById("marque").value;
    reference = document.getElementById("reference").value;
    dateperemption = document.getElementById("dateperemption").value;
    prix = document.getElementById("prix").value;
    qte = document.getElementById("qte").value;
    qtemini = document.getElementById("qtemini").value;
    fetch('http://localhost:8080/ProjetJavaEEService/cantine/stocks', {
        method: 'post',
        body: JSON.stringify({
            produit:{
                libelle:libelle,
                marque:marque,
                reference:reference,
                dateperemption:dateperemption,
                prix:prix
            },
            qte:qte,
            qtemini:qtemini}),
        headers:{
            "Content-Type": "application/json"
        }
    }).then(function (result) {
        var data = result.json();
        if (request.ok) {
            alert("Le produit a été créé avec succès");
            productID = data.id;
            console.log(productID);
            alert("Le produit a été créé avec succès");
        } else {
            alert("Impossible de créer le produit")
        }
    });
}

function checkAuth(){
    var Auth = localStorage.getItem("isAuth");
    if(!Auth) {
        alert("Veuillez vous connecter à l'application");
        window.location.href = "../Auth/Connexion.html";
    }
}

