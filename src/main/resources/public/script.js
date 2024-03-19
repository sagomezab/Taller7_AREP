function enviarSin() {
    var user = document.getElementById("user").value;
    var psswd = document.getElementById("pssd").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/login?user=" + encodeURIComponent(user) + "&password=" + encodeURIComponent(psswd), true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resultado = xhr.responseText;
            console.log(resultado);
            document.getElementById("envio").innerText = resultado;
            if (resultado.includes("Inicio de sesion exitoso para el usuario")) {
                setTimeout(function() {
                    window.location.href = "operaciones.html";;
                }, 10000);
                
            }
        }
    };
    xhr.send();
}