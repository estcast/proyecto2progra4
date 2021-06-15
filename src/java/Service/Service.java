/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.DaoPeliculas;
import DataBase.DaoUsuarios;
import java.util.ArrayList;
import java.util.List;
import logic.pelicula;
import logic.usuario;

/**
 *
 * @author jamca
 */
public class Service {
    
    private DaoUsuarios usuariosDAO;
    private DaoPeliculas peliculasDAO;
    
    
    private static Service theInstance;
    
    
    public static Service instance(){
        if(theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }
    
    public Service(){
        this.usuariosDAO = new DaoUsuarios();
        this.peliculasDAO = new DaoPeliculas();
    }
    
    //USUARIOS------------------------------------------
    
    public usuario crearUsuario(usuario u){
        usuario result = null;
        try{
            result = usuariosDAO.create(u);
        }catch(Exception ex){
            //Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public List<usuario> listUsuarios(){
        
        List<usuario> result = new ArrayList<>();
        
        try{
            result = usuariosDAO.listaClientes();
            return result;
        }catch (Exception e) {
            return null;
        } 
        
    }
    
    
    public usuario getUsuario(String id){
        usuario u = null;
        try{
            u = usuariosDAO.readbyId(id);
            return u;
        }catch(Exception e){     
            return u;
        }
    }
    
    //PELICULAS--------------------------------------
    public List<pelicula> listPeliculas(){
        List<pelicula> result = new ArrayList<>();
        
        try{
            result = peliculasDAO.listaPeliculas();
            return result;
        }catch (Exception e) {
            return null;
        } 
    }
    
}
