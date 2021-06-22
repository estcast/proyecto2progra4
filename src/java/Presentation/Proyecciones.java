/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.proyeccion;
import logic.tiquete;

/**
 *
 * @author danie
 */

@Path("/proyecciones")
public class Proyecciones {
    
    @GET
    @Produces((MediaType.APPLICATION_JSON))
    
    @Path("{id}")
    public List<proyeccion> allUsuarios(@PathParam("id") String id){
        List<proyeccion> todos = Service.Service.instance().listProyeccionesPorPelicula(Integer.parseInt(id));
        return todos;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<proyeccion> allProyecciones(){
        List<proyeccion> todos = Service.Service.instance().listProyecciones();
        return todos;
    }
    
    
    @GET
    @Path("/tiquetes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<tiquete> allTiquetes(){
        List<tiquete> todos = Service.Service.instance().listTiquetes();
        return todos;
    }
    
    @POST
    @Path("/tiquetes")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTiqute(tiquete u){
        Service.Service.instance().crearTiquete(u);
    }
    
}