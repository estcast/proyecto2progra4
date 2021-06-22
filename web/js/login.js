var url="http://localhost:8080/ProyectollProgralV/";
                                       
    function login(){

        usuario = {
            id: document.getElementById("usuarioLogin").value,
            contrasenna: document.getElementById("clave").value
        };
        
        let request = new Request(url+'api/login', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
        (async ()=>{
            const response = await fetch(request);           
            if (!response.ok) {

                return;}
            
            usuario = await response.json();
            sessionStorage.setItem('user', JSON.stringify(usuario));
            document.location = url;
                          
        })(); 
    
    }

    function loginValidar(){
        $("#loginForm").addClass("was-validated");
        return $("#loginForm").get(0).checkValidity(); 
    }

    function logout(){
        let request = new Request(url+'api/login', {method: 'DELETE', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {
                return;}
            sessionStorage.removeItem('user');
            document.location = url;                         
        })();          
    }

  function errorMessage(status,place){  
        switch(status){
            case 404: error= "Registro no encontrado"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="Usuario ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
    }  
  
  function load(){
        document.getElementById("logButton").addEventListener("click",login);
        document.getElementById("logoutDiv").addEventListener("click",logout);
           // $("#logoutButton").click(logout);                             
  }
  
  document.addEventListener("DOMContentLoaded", load);
