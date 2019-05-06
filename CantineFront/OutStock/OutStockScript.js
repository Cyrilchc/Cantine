var listProducts = [];
class Produit{
    constructor(libelle, marque, reference, dateperemption, prix){
        this.libelle = libelle;
        this.marque= marque;
        this.reference = reference;
        this.dateperemption = dateperemption;
        this.prix = prix;
    }
}

const app = document.getElementById('root');
function getOutStockProducts(){
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/ProjetJavaEEService/cantine/outStock/OutOfStock', true);
    request.onload = function () {
        // Begin accessing JSON data here
        var data = JSON.parse(this.response);
        if (request.status >= 200 && request.status < 400) {
            data.forEach(product => {
                produit = new Produit(product.libelle, product.marque, product.reference, product.dateperemption, product.prix);
                if(!containsProduct(produit, listProducts)) {
                    listProducts.push(produit);
                }
            });
        } else {
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Une erreur est survenue`;
            app.appendChild(errorMessage);
        }

        displayAllProducts();
    };

    request.send();
}

function getNearlyOutStockProducts(){
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/ProjetJavaEEService/cantine/outStock/NearlyOutOfStock', true);
    request.onload = function () {
        // Begin accessing JSON data here
        var data = JSON.parse(this.response);
        if (request.status >= 200 && request.status < 400) {
            data.forEach(product => {
                produit = new Produit(product.libelle, product.marque, product.reference, product.dateperemption, product.prix);
                if(!containsProduct(produit, listProducts)) {
                    listProducts.push(produit);
                }
            });
        } else {
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Une erreur est survenue`;
            app.appendChild(errorMessage);
        }

        displayAllProducts();
    };

    request.send();
}

function displayAllProducts(){
    const container = document.createElement('div');
    container.setAttribute('class', 'container');
    app.appendChild(container);
    listProducts.forEach(product =>{
        const card = document.createElement('div');
        card.setAttribute('class', 'card');
        const h1 = document.createElement('h1');
        h1.textContent = product.libelle;
        const p = document.createElement('p');
        p.textContent = "Marque : " + product.marque;
        const p2 = document.createElement('p');
        p2.textContent = "Référence : " + product.reference;
        const p3 = document.createElement('p');
        p3.textContent = "Date de préremption : " + new Date(product.dateperemption).toLocaleDateString();
        const p4 = document.createElement('p');
        p4.textContent = "Prix : " + product.prix;
        container.appendChild(card);
        card.appendChild(h1);
        card.appendChild(p);
        card.appendChild(p2);
        card.appendChild(p3);
        card.appendChild(p4);
        card.onclick = function () {
            localStorage.setItem("selectedProduct", product.reference);
            window.location.href = "../EditProduct/EditProduct.html";
        }
    });
}

function filterProducts() {
    while (app.firstChild) {
        app.removeChild(app.firstChild);
    }

    const container = document.createElement('div');
    container.setAttribute('class', 'container');
    app.appendChild(container);
    var input = document.getElementById("searcher").value;
    listProducts.forEach(product =>{
        if(product.libelle.toUpperCase().includes(input.toUpperCase())
            || product.marque.toUpperCase().includes(input.toUpperCase())
            || product.reference.toUpperCase().includes(input.toUpperCase())
            || product.prix.toString().includes(input.toString())
        ){
            const card = document.createElement('div');
            card.setAttribute('class', 'card');
            const h1 = document.createElement('h1');
            h1.textContent = product.libelle;
            const p = document.createElement('p');
            p.textContent = "Marque : " + product.marque;
            const p2 = document.createElement('p');
            p2.textContent = "Référence : " + product.reference;
            const p3 = document.createElement('p');
            p3.textContent = "Date de préremption : " + new Date(product.dateperemption).toLocaleDateString();
            const p4 = document.createElement('p');
            p4.textContent = "Prix : " + product.prix;
            container.appendChild(card);
            card.appendChild(h1);
            card.appendChild(p);
            card.appendChild(p2);
            card.appendChild(p3);
            card.appendChild(p4);
            card.onclick = function () {
                localStorage.setItem("selectedProduct", product.reference);
                window.location.href = "../EditProduct/EditProduct.html";
            }
        }
    });
}

function switchtoggled(){
    var outStocktoggled = document.getElementById("horsStock").checked;
    var nearlyOutStocktoggled = document.getElementById("StockMini").checked;
    listProducts = [];
    while (app.firstChild) {
        app.removeChild(app.firstChild);
    }

    if(nearlyOutStocktoggled){
        getNearlyOutStockProducts();
    }else if(outStocktoggled){
        getOutStockProducts();
    }
}

function containsProduct(obj, list) {
    var i;
    for (i = 0; i < list.length; i++) {
        if (list[i].reference === obj.reference) {
            return true;
        }
    }
    return false;
}

function checkAuth(){
    var Auth = localStorage.getItem("isAuth");
    if(!Auth) {
        alert("Veuillez vous connecter à l'application");
        window.location.href = "../Auth/Connexion.html";
    }
}
