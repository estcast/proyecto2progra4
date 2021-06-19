
var peliculas = [{nombre: "Totoro",id: 2}, {nombre: "JohnWick",id: 3},{nombre: "Fast&Furious6",id: 4},{nombre: "JurassicPark",id: 5},{nombre: "Jaws",id: 6}];
var asientos = [];
var proyecciones = [{Dia: "May 29", Hora: "03pm", Sala: "B2"}, {Dia: "May 28", Hora: "11am", Sala: "A5"}, {Dia: "May 28", Hora: "05pm", Sala: "A5"}];
var precioPelicula = 1000;
var url="http://localhost:8080/proyecto11progra1V/";
var listPeliculas = new Array();
var listSalas = new Array();

var searchButton = document.getElementById("search-button");
var searchInput = document.getElementById("search-input");
function render() {

}

function buscar() {
    //var searchButton = document.getElementById("search-button");
    var searchInput = document.getElementById("search-input");
    console.log(searchInput);
    console.log(searchInput.value);
    
    var inputValue = searchInput.value;
    
    pels = listPeliculas.filter(p => p.nombre.includes(inputValue));
    console.log(listPeliculas);
    list(pels);
    console.log(pels);
    alert(inputValue);
}

function fetchPeliculas(){
    let request = new Request(url+'api/peliculas', {method: 'GET', headers: { }});
    console.log('Entrando a Fech Peliculas');
    console.log(request);
    (async ()=>{
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

function list(pel) {
    var listado = document.getElementById("listado");
    listado.innerHTML = "";
  
   // listPeliculas.forEach((p) =>{
   //     row(listPeliculas, p);
   // });
    console.log(peliculas);
    console.log('--------');
    console.log(listPeliculas);
      
    pel.forEach((p) =>{
        row(listado, p);
    });
}

function row(listado, p){
    var div = document.createElement("div");
    var div2 = document.createElement("div");

    div.setAttribute("class", "col");
    div.setAttribute("colspan", "1");

    var peliImg = document.createElement("img");
    peliImg.setAttribute("src", "images/" + p.nombre + ".jpg");
    peliImg.setAttribute("class", "icon");
    peliImg.setAttribute("onmouseover", "bigImg(this)");
    peliImg.setAttribute("onmouseout", "normalImg(this)");
    /*
    var nombreLabel = document.createElement("label");
    nombreLabel.appendChild(document.createTextNode(p.nombre));
    */
    var proyUl = document.createElement("ul");
    
    var proyLi = document.createElement("li");
    proyecciones.forEach(pro =>{
        proyLi = pro.Dia + pro.Hora + pro.Sala;
            
            //proyUl.appendChild(document.createTextNode(pro.Dia + pro.Hora + pro.Sala));
            //div2.appendChild(proyUl);
            proyUl.append(proyLi);
            });
    div2.appendChild(proyUl);
    div.append(peliImg);
    peliImg.addEventListener("click", displayPop);
    div.append(peliImg);
    div.appendChild(div2);
    listado.appendChild(div);
}

function loaded(){
    fetchPeliculas();
   // list();
    document.getElementById("pic").addEventListener("click",hidePop);
    document.getElementById("pic2").addEventListener("click",hidePop);
    document.getElementById("pic3").addEventListener("click",hidePop);
    document.getElementById("pic4").addEventListener("click",hidePop);
    document.getElementById("pic5").addEventListener("click",hidePop);
    document.getElementById("pic6").addEventListener("click",hidePop);
    addEventSeats();
    document.getElementById("loginDiv").addEventListener("click",displayLogin);
    menuDisplay();
    document.getElementById("registrarDiv").addEventListener("click",registroPeliculaDisplay);
    document.getElementById("registrarUsuarioDiv").addEventListener("click",registroUsuarioDisplay);
    document.getElementById("registrarSalaDiv").addEventListener("click",registroSalaDisplay);
    document.getElementById("registrarProyeccionDiv").addEventListener("click",registroProyeccionDisplay);
    
    document.getElementById("search-button").addEventListener('click', buscar);
}

function bigImg(x) {
  x.style.width = "420px";
  x.style.height = "520x";
}

function normalImg(x) {
  x.style.width = "400px";
  x.style.height = "500px";
}

function displayPop(){                                        //muestra el popUp
    document.getElementById("over").className = "overlay";
    document.getElementById("pop").style.display='block';
}

function hidePop(){                                                   //oculta el popUP
    document.getElementById("over").classList.remove("overlay");
    document.getElementById("pop").style.display='none';
    document.getElementById("login").style.display='none';
    document.getElementById("registroPelicula").style.display='none';
    document.getElementById("registroUsuario").style.display='none';
    document.getElementById("registroSala").style.display='none';
    document.getElementById("registroProyeccion").style.display='none';
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
        document.getElementById("registrarUsuarioDiv").style.display = 'none';
        if(usuario.rol !== 1){
            document.getElementById("registrarDiv").style.display = 'none';
        }
        else{
            
        }
    }
    else{
        document.getElementById("logoutDiv").style.display = 'none';
        document.getElementById("registrarDiv").style.display = 'none';
        document.getElementById("registrarSalaDiv").style.display = 'none';
        document.getElementById("registrarProyeccionDiv").style.display = 'none';
    }
}


function registroPeliculaDisplay(){
    document.getElementById("over").className = "overlay";
    document.getElementById("registroPelicula").style.display='block';
}

function registroUsuarioDisplay(){
    document.getElementById("over").className = "overlay";
    document.getElementById("registroUsuario").style.display='block';
}

function registroSalaDisplay(){
    document.getElementById("over").className = "overlay";
    document.getElementById("registroSala").style.display='block';
}

function registroProyeccionDisplay(){
    fechSalas();
     listSelectPeliculas();
    document.getElementById("over").className = "overlay";
    document.getElementById("registroProyeccion").style.display='block';
}

function fechSalas(){
    url2="http://localhost:8080/ProyectollProgralV/";
    
    let request = new Request(url2+'api/peliculas/salas', {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
         if (!response.ok) {
            return;
         }
        listSalas = await response.json(); 
        listSelectSalas();
    })(); 
}

function listSelectSalas(){
    selectSala = document.getElementById("selectSala");
    selectSala.innerHTML = "";
    
    listSalas.forEach((p) =>{
        option = document.createElement("option");
        option.setAttribute("value",p.id);
        option.appendChild(document.createTextNode(p.nombre));
        
        selectSala.appendChild(option);
    });
}

function listSelectPeliculas(){
    selectPelicula = document.getElementById("selectPeliculas");
    selectPelicula.innerHTML = "";
    
    listPeliculas.forEach((p) =>{
        option = document.createElement("option");
        option.setAttribute("value",p.id);
        option.appendChild(document.createTextNode(p.nombre));
        
        selectPelicula.appendChild(option);
    });
}
//$(loaded);
document.addEventListener("DOMContentLoaded", loaded);
