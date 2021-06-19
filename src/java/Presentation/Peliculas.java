/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import logic.pelicula;
import logic.proyeccion;
import logic.sala;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 *
 * @author jamca
 */
@Path("/peliculas")
public class Peliculas {
    String location="C:/Users/esteb/OneDrive/Escritorio/ProyectollProgralV/web/images/";
    //String location="../../../web/images/";
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<pelicula> allPeliculas(){
        List<pelicula> todos = Service.Service.instance().listPeliculas();
        return todos;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(pelicula peli){
        try {
            Service.Service.instance().crearPelicula(peli);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{nombre}/imagen")
    public void addImage(@PathParam("nombre") String nombre, @FormDataParam("imagen") InputStream imagenStream){
        try{
                int read = 0;
                byte[] bytes = new byte[1024];

                OutputStream out = new FileOutputStream(new File(location + nombre + ".jpg"));
                while ((read = imagenStream.read(bytes)) != -1){out.write(bytes, 0, read);}
                out.flush();
                out.close();
            } catch (Exception ex) {
                throw new NotAcceptableException(); 
            }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sala")
    public void addSala(sala sal){
        try {
            Service.Service.instance().crearSala(sal);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salas")
    public List<sala> allSalas(){
        List<sala> todos = Service.Service.instance().listSalas();
        return todos;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/proyeccion")
    public void addProyeccion(proyeccion pro){
        try {
            Service.Service.instance().crearProyeccion(pro);
        } catch (Exception ex) {
            throw new NotAcceptableException(); 
        }
    }
}
