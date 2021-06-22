/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.DaoPeliculas;
import DataBase.DaoProyeccion;
import DataBase.DaoSalas;
import DataBase.DaoTiquetes;
import DataBase.DaoUsuarios;
import java.util.ArrayList;
import java.util.List;
import logic.pelicula;
import logic.proyeccion;
import logic.sala;
import logic.tiquete;
import logic.usuario;

/**
 *
 * @author jamca
 */
public class Service {
    
    private DaoUsuarios usuariosDAO;
    private DaoPeliculas peliculasDAO;
    private DaoSalas salasDAO;
    private DaoProyeccion proyeccionesDAO;
    private DaoTiquetes tiquetesDAO;
    
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
        this.salasDAO = new DaoSalas();
        this.proyeccionesDAO = new DaoProyeccion();
        this.tiquetesDAO = new DaoTiquetes();
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
    
    public void crearPelicula(pelicula peli){
        try{
            peliculasDAO.create(peli);
        }catch(Exception e){            
        }
    }
    
   public void crearSala(sala s){
        try{
            salasDAO.create(s);
        }catch(Exception e){            
        }
    } 
    
    public List<sala> listSalas(){        
        List<sala> result = new ArrayList<>();        
        try{
            result = salasDAO.listaSalas();
            return result;
        }catch (Exception e) {
            return result;
        }         
    }
    
    public void crearProyeccion(proyeccion p){
        try{
            proyeccionesDAO.create(p);
        }catch(Exception e){            
        }
    } 
    
    public List<tiquete> listTiquetes(int id){        
        List<tiquete> result = new ArrayList<>();        
        try{
            result = tiquetesDAO.readbyId(id);
            return result;
        }catch (Exception e) {
            return result;
        }         
    }
    
    //Poyecciones
    public List<proyeccion> listProyeccionesPorPelicula(int id){
        List<proyeccion> result = new ArrayList<>();
        try{
            result = proyeccionesDAO.proyeccion_porPelicula(id);
            return result;
        }catch(Exception e){
            return result;
        }
    }
    
    public List<proyeccion> listProyecciones(){
        List<proyeccion> result = new ArrayList<>();
        try{
            result = proyeccionesDAO.listaProyecciones();
            return result;
        }catch(Exception e){
            return result;
        }
    }
    
    
    public List<tiquete> listTiquetes(){        
        List<tiquete> result = new ArrayList<>();        
        try{
            result = tiquetesDAO.listaTiquetes();
            return result;
        }catch (Exception e) {
            return result;
        }         
    }
    
    public void crearTiquete(tiquete t){
        try{
            tiquetesDAO.create(t);
        }catch(Exception e){            
        }
    }
}
