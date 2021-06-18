var url="http://localhost:8080/ProyectollProgralV/";


    function registroPelicula(){
      
        pelicula = {
            nombre: document.getElementById("peliNombre").value,
            precio: document.getElementById("peliPrecio").value
        };
        
        let request = new Request(url+'api/peliculas', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(pelicula)});
        (async ()=>{
            const response = await fetch(request);           
            if (!response.ok) {
                return;}
            addImagen();
            document.location = url;                        
        })(); 
    
    }
    
   function addImagen(){
    peli = document.getElementById("peliNombre").value;
    var imagenData = new FormData();
    imagenData.append("nombre",peli);
    variable2 = $("#peliImagen").get(0).files[0];
    imagenData.append("imagen", variable2); 
    let request = new Request(url+'api/peliculas/'+peli+'/imagen', {method:'POST',body:imagenData});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {
            return;}              
    })();    
  }
      
  function syncDelay(milliseconds){
    var start = new Date().getTime();
    var end=0;
    while( (end-start) < milliseconds){
         end = new Date().getTime();
    }
  }
      
  function registroUsuario(){
      
        usuario = {
            nombre: document.getElementById("nu_nombre").value,
            id: document.getElementById("nu_id").value,
            contrasenna: document.getElementById("nu_clave").value
        };
        
        let request = new Request(url+'api/usuarios', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
        (async ()=>{
            const response = await fetch(request);           
            if (!response.ok) {
                return;}
            document.location = url;                        
        })(); 
    
    }
      
      function load(){
        document.getElementById("peliculaButton").addEventListener("click",registroPelicula);      
        document.getElementById("nu_Button").addEventListener("click",registroUsuario); 
  }
  
  document.addEventListener("DOMContentLoaded", load);