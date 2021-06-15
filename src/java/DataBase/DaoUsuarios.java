/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logic.usuario;


/**
 *
 * @author jamca
 */
public class DaoUsuarios {
    
    public usuario from (ResultSet rs){
        try{
            usuario r = new usuario();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            r.setContrasenna(rs.getString("contrasenna"));
            r.setRol(rs.getInt("rol"));
            return r;
                        
        }catch (SQLException ex){
            return null;
        }
    }
    
    public usuario create (usuario cl) throws SQLException, Exception{
        String sqlcommand = "insert into usuarios (id,nombre,contrasenna,rol)" + 
                "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, cl.getId());
        stm.setString(2, cl.getNombre());
        stm.setString(3, cl.getContrasenna());
        stm.setInt(4, cl.getRol());
        int count = Database.instance().executeUpdate(stm);
        if (count ==0){
            throw new Exception("Usuario ya existente");
        }
        return cl;
    }
        
    public usuario readbyId(String id) throws Exception{
    
        String sqlcommand = "select * from usuarios where id = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);

        if(rs.next()){
            return from(rs);
        }else{
            throw new Exception("Usuario Inexistente");
        }
    }
        
    public List<usuario> listaClientes() throws Exception{
        String sqlcommnad = "select * from usuarios";
        List<usuario> clientes = Collections.synchronizedList(new ArrayList<usuario>());
        
        
        

        PreparedStatement stm = Database.instance().prepareStatement(sqlcommnad);

        ResultSet rs = Database.instance().executeQuery(stm);
        
        
        while(rs.next()){
            usuario r = new usuario();
            r.setId(rs.getString("id"));
            r.setNombre(rs.getString("nombre"));
            r.setContrasenna(rs.getString("contrasenna"));
            r.setRol(rs.getInt("rol"));
            clientes.add(r);
        }
              

         return clientes;  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
