function enviarSin() {
    var parm = document.getElementById("sin1").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/sin?angle=" + encodeURIComponent(parm), true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resultado = xhr.responseText;
            console.log(resultado);
            document.getElementById("resultado1").innerText = resultado;
        }
    };
    xhr.send();
}
