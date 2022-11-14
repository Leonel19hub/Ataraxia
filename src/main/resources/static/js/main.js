// Verificamos si la bandera es TRUE o FALSE
var bandUser2 = "[[${userLogin}]]";
console.log("bandUser2: "+bandUser2);

// Inicializamoc la variable "elem" con el id del header "changeLogin"
var elem = document.getElementById("changeLogin")
if(bandUser2){
    // Si es TRUE redirecciona a:
    elem.setAttribute("href", "/Mi-Perfil");
}
else{
    // Si es FALSE redirecciona a:
    elem.setAttribute("href", "/login");
}
