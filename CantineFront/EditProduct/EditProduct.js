reference = localStorage.getItem("selectedProduct");
function popuateFields(){
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/ProjetJavaEEService/cantine/prods/' + reference, true);
    request.onload = function () {
        var data = JSON.parse(this.response);
        if (request.status >= 200 && request.status < 400) {
                document.getElementById('libelle').value = data.libelle;
                document.getElementById('marque').value = data.marque;
                document.getElementById('reference').value = reference;
                document.getElementById('dateperemption').value = new Date(data.dateperemption).toLocaleDateString();
                document.getElementById('prix').value = data.prix;
        } else {
            alert("Impossible de récupérer les informations de ce produit");
        }
    };
    request.send();

    getStock();
}

function getStock() {
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/ProjetJavaEEService/cantine/stocks/' + reference, true);
    request.onload = function () {
        var data = JSON.parse(this.response);
        if (request.status >= 200 && request.status < 400) {
            document.getElementById('qte').value = data.qte;
            document.getElementById('qtemini').value = data.qtemini;
        } else {
            alert("Impossible de récupérer les informations de stock");
        }
    };
    request.send();
}


function editProduct(){
    currentreference = reference;
    libelle = document.getElementById("libelle").value;
    marque = document.getElementById("marque").value;
    reference = document.getElementById("reference").value;
    dateperemption = document.getElementById("dateperemption").value;
    prix = document.getElementById("prix").value;
    qte = document.getElementById("qte").value;
    qtemini = document.getElementById("qtemini").value;
    fetch('http://localhost:8080/ProjetJavaEEService/cantine/stocks/' + currentreference, {
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
            alert("Le produit a été modifié avec succès");
        } else {
            alert("Impossible de modifier le produit")
        }
    });
}

function deleteProduct(){
    var request = new XMLHttpRequest();
    request.open('DELETE', 'http://localhost:8080/ProjetJavaEEService/cantine/prods/' + reference, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            alert("Produit supprimé avec succès");
            //window.location.href = "../DashBoard/DashBoard.html";
        } else {
            alert("Impossible de supprimer le produit")
        }
    };
    request.send();
}

function deleteStock() {
    var request = new XMLHttpRequest();
    request.open('DELETE', 'http://localhost:8080/ProjetJavaEEService/cantine/prods/' + reference, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
        } else {
            alert("Impossible de supprimer l'entrée stock")
        }
        window.location.href = "../DashBoard/DashBoard.html";
    };
    request.send();

    deleteProduct();
}

function checkAuth(){
    var Auth = localStorage.getItem("isAuth");
    if(!Auth) {
        alert("Veuillez vous connecter à l'application");
        window.location.href = "../Auth/Connexion.html";
    }
}
