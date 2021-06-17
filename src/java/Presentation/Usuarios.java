/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.usuario;

/**
 *
 * @author jamca
 */

@Path("/usuarios")
public class Usuarios {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<usuario> allUsuarios(){
        List<usuario> todos = Service.Service.instance().listUsuarios();
        return todos;
    }
    
    
    
    
}
