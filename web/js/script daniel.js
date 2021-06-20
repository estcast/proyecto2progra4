
var peliculas = [{nombre: "Totoro", id: 2}, {nombre: "JohnWick", id: 3}, {nombre: "Fast&Furious6", id: 4}, {nombre: "JurassicPark", id: 5}, {nombre: "Jaws", id: 6}];
var asientos = [];
var proyecciones = [{fecha: "May 29", hora: "03pm", sala_id: "B2"}, {fecha: "May 28", hora: "11am", sala_id: "A5"}, {fecha: "May 28", hora: "05pm", sala_id: "A5"}];
var precioPelicula = 1000;
var url = "http://localhost:8080/proyecto2progra4//";
var listPeliculas = new Array();
var listProyecciones = new Array();


function render() {

}

function buscar() {
    var inputValue = document.getElementById('search-input').value;
    pels = listPeliculas.filter(p => p.nombre.includes(inputValue));
    console.log(peliculas);
    list(pels);
    console.log("ResultadoBusqueda")
    console.log(pels);
    //alert(inputValue);
}

function fetchPeliculas() {
    let request = new Request(url + 'api/peliculas', {method: 'GET', headers: {}});
    console.log('Entrando a Fech Peliculas');
    console.log(request);
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            console.log('Fallo en el response');
            return;
        }
        listPeliculas = await response.json();
        console.log(listPeliculas);
        list(listPeliculas);
    })();
}

function fetchProyecciones() {
    let request = new Request(url + 'api/proyecciones', {method: 'GET', headers: {}})
    console.log('Entrando fetch proyecciones');
    console.log(request);
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            console.log('Fallo en el response');
            return;
        }
        listProyecciones = await response.json();
        console.log(listProyecciones);
    })();
}

function list(pel) {
    var listado = document.getElementById("listado");
    listado.innerHTML = "";

    console.log(pel);
    console.log('--------');
    console.log(listPeliculas);

    pel.forEach((p) => {
        row(listado, p);
    });
}

function row(listado, p) {
    let request = new Request(url + 'api/proyecciones', {method: 'GET', headers: {}});
    console.log('Request Proyecciones');
    console.log(request);
    console.log('listProyecciones');
    console.log(listProyecciones);
    var div = document.createElement("div");
    var div2 = document.createElement("div");

    div.setAttribute("class", "col");
    div.setAttribute("colspan", "1");

    var peliImg = document.createElement("img");
    peliImg.setAttribute("src", "images/" + p.nombre + ".jpg");
    peliImg.setAttribute("class", "icon");

    div.setAttribute("onmouseover", "bigImg(this)");
    div.setAttribute("onmouseout", "normalImg(this)");
    var proyUl = document.createElement("ul");
    proyUl.setAttribute("class", "hided");

    //proyecciones.forEach(pro => {
    listProyecciones.forEach(pro => {
        var idPString = p.id.toString();
        var idProString = pro.pelicula_id.toString();
        console.log(idPString);
        console.log(idProString);
        if (idPString == idProString) {
            var proyLi = document.createElement("li");
            var anchor = document.createElement("a");
            anchor.setAttribute("href", "api/proyecciones/" + p.id);
            var text = document.createTextNode(pro.fecha + " " + pro.hora + " " + pro.sala_id);
            anchor.append(text);
            proyLi.append(anchor);
            proyUl.append(proyLi);
        }
    });

    div2.setAttribute("display", "none");
    div2.appendChild(proyUl);
    div.append(peliImg);
    peliImg.addEventListener("click", displayPop);
    div.append(peliImg);
    div.appendChild(div2);
    listado.appendChild(div);
}


function bigImg(x) {
    //x.style.width = "420px";
    //x.style.height = "520x";
    x.firstChild.nextSibling.firstChild.setAttribute("class", "showed");
    x.firstChild.nextSibling.firstChild.display = "inherit";
}

function normalImg(x) {
    //x.style.width = "400px";
    //x.style.height = "500px";
    x.firstChild.nextSibling.firstChild.setAttribute("class", "hided");
}

function loaded() {
    console.log("Loaded");
    fetchPeliculas();
    fetchProyecciones();
    document.getElementById("pic").addEventListener("click", hidePop);
    document.getElementById("pic2").addEventListener("click", hidePop);
    addEventSeats();
    document.getElementById("loginDiv").addEventListener("click", displayLogin);
    document.getElementById("search-button").addEventListener('click', buscar);
    menuDisplay();
}

function displayPop() {                                        //muestra el popUp
    document.getElementById("over").className = "overlay";
    document.getElementById("pop").style.display = 'block';
}

function hidePop() {                                                   //oculta el popUP
    document.getElementById("over").classList.remove("overlay");
    document.getElementById("pop").style.display = 'none';
    document.getElementById("login").style.display = 'none';
}

function colorChanger(e) {                               //Evento que cambia de color los asientos
    if (e.classList.contains("occupied"))
        return;

    if (!e.classList.contains("selected")) {
        e.classList.add("selected");
        asientos.push(e.getAttribute("id"));
        updateInfo();
    } else {
        e.classList.remove("selected");
        asientos.splice(asientos.indexOf(e.getAttribute("id")), 1);    //Elimina el asiento del array
        updateInfo();
    }
}

function addEventSeats() {                                   //Agrega el evento a cada asiento                           
    container = document.querySelectorAll(".seat");
    container.forEach(box =>
        box.addEventListener("click", (b) => {
            colorChanger(box);
        })
    );
}

function updateInfo() {
    datos = document.getElementById("datos");
    datos.textContent = "Asientos seleccionados: " + asientos.length.toString();

    div = document.createElement("div");
    div.textContent += "Butacas: " + asientos;
    datos.appendChild(div);

    div2 = document.createElement("div");
    div2.textContent = "Total: " + (asientos.length * precioPelicula).toString() + " colones";
    datos.appendChild(div2);
}


function displayLogin() {                                        //muestra el popUp de login
    document.getElementById("over").className = "overlay";
    document.getElementById("login").style.display = 'block';
}

function menuDisplay() {
    let usuarioJson = sessionStorage.getItem('user');

    if (usuarioJson !== null) {
        let usuario = JSON.parse(usuarioJson);
        document.getElementById("loginDiv").style.display = 'none';
        if (usuario.rol !== 1) {
            document.getElementById("registrarDiv").style.display = 'none';
        }
    } else {
        document.getElementById("logoutDiv").style.display = 'none';
        document.getElementById("registrarDiv").style.display = 'none';
    }
}

function registroPeliculaDisplay() {
    document.getElementById("over").className = "overlay";
    document.getElementById("registroPelicula").style.display = 'block';
}

function registroUsuarioDisplay() {
    document.getElementById("over").className = "overlay";
    document.getElementById("registroUsuario").style.display = 'block';
}

//$(loaded);
document.addEventListener("DOMContentLoaded", loaded);
