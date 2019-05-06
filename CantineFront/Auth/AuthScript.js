function Authentification(){
    log = document.getElementById("log").value;
    console.log(log);
    pw = document.getElementById("pw").value;
    console.log(pw);
    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:8080/ProjetJavaEEService/cantine/co/connexion', true);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            localStorage.setItem("isAuth", true);
            window.location.href = "../DashBoard/DashBoard.html";
        } else {
            alert("Impossible de se connecter")
        }
    };
    request.send(JSON.stringify({user:log, password:pw}));
}
