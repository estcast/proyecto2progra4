
var peliculas = [{nombre: "Totoro"}, {nombre: "JohnWick"},{nombre: "Fast&Furious6"},{nombre: "JurassicPark"},{nombre: "Jaws"}];
var asientos = [];
var precioPelicula = 1000;

function render() {

}

function list() {
    var listado = document.getElementById("listado");
    listado.innerHTML = "";
    peliculas.forEach((p) =>{
        row(listado, p);
    });
}

function row(listado, p){
    var div = document.createElement("div");
    
    div.setAttribute("class", "col");
    div.setAttribute("colspan","1");
    /*
    var nombreLabel = document.createElement("label");
    nombreLabel.appendChild(document.createTextNode("pelicula"));
    */
    var peliImg = document.createElement("img");
    peliImg.setAttribute("src", "images/" + p.nombre + ".jpg");
    peliImg.setAttribute("class", "icon");
    
    //div.append(nombreLabel, peliImg);
    peliImg.addEventListener("click",displayPop);
    div.append(peliImg);
    listado.appendChild(div);
}

function loaded(event){
    list();
    document.getElementById("pic").addEventListener("click",hidePop);
    document.getElementById("pic2").addEventListener("click",hidePop);
    addEventSeats();
     document.getElementById("loginDiv").addEventListener("click",displayLogin);
     menuDisplay();
}

function displayPop(){                                        //muestra el popUp
    document.getElementById("over").className = "overlay";
    document.getElementById("pop").style.display='block';
}

function hidePop(){                                                   //oculta el popUP
    document.getElementById("over").classList.remove("overlay");
    document.getElementById("pop").style.display='none';
    document.getElementById("login").style.display='none';
}

function colorChanger(e){                               //Evento que cambia de color los asientos
    if(e.classList.contains("occupied"))
        return;
    
    if(!e.classList.contains("selected")){ 
        e.classList.add("selected");
        asientos.push(e.getAttribute("id"));
        updateInfo();
    } 
    else {
        e.classList.remove("selected");
        asientos.splice(asientos.indexOf(e.getAttribute("id")),1);    //Elimina el asiento del array
        updateInfo();
    }
}

function addEventSeats(){                                   //Agrega el evento a cada asiento                           
    container = document.querySelectorAll(".seat");
    container.forEach(box =>
        box.addEventListener("click",(b)=>{colorChanger(box);})
    );
}

function updateInfo(){
    datos = document.getElementById("datos");
    datos.textContent = "Asientos seleccionados: " + asientos.length.toString();
    
    div = document.createElement("div");
    div.textContent += "Butacas: " + asientos;
    datos.appendChild(div);
    
    div2 = document.createElement("div");
    div2.textContent = "Total: " + (asientos.length * precioPelicula).toString() + " colones";
    datos.appendChild(div2);
}


function displayLogin(){                                        //muestra el popUp de login
    document.getElementById("over").className = "overlay";
    document.getElementById("login").style.display='block';
}

function menuDisplay(){
    let usuarioJson = sessionStorage.getItem('user');
    
    if(usuarioJson !== null){
        let usuario = JSON.parse(usuarioJson);
        document.getElementById("loginDiv").style.display = 'none';
        if(usuario.rol !== 1){
            document.getElementById("registrarDiv").style.display = 'none';
        }
    }
    else{
        document.getElementById("logoutDiv").style.display = 'none';
        document.getElementById("registrarDiv").style.display = 'none';
    }
}

document.addEventListener("DOMContentLoaded", loaded);