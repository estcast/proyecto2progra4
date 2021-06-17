/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.pelicula;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jamca
 */
public class DaoPeliculas {
    
    public pelicula from (ResultSet rs){
        try{
            pelicula p = new pelicula();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setNombre(rs.getString("nombre"));
            p.setDisponible(rs.getBoolean("disponible"));
            p.setPrecio(Double.parseDouble(rs.getString("precio")));
            return p;
        } catch (SQLException ex) {
            return null;
        }
        
    }
    
    public pelicula create (pelicula pel) throws  SQLException, Exception {
        String sqlcommand = "insert into peliculas(nombre,precio,disponible) "
                +"values(?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        
        stm.setString(1, pel.getNombre());
        stm.setDouble(2, pel.getPrecio());
        stm.setBoolean(3, pel.getDisponible());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0){
            throw new Exception("Pelicula Existente");
        }
        return pel;
    }
    
    public pelicula readbyId (int id) throws Exception {
        
        String sqlcommand = "select * from peliculas where id = ?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1,id);
        ResultSet rs = Database.instance().executeQuery(stm);
        if(rs.next()){
            return from (rs);
        }else{
            throw new Exception("Pelicula no Existe");
        }
    }
    
    public List<pelicula> listaPeliculas() throws Exception {
        String sqlcommand = "select * from peliculas ";
        List<pelicula> peliculas = Collections.synchronizedList(new ArrayList<>());
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        ResultSet rs = Database.instance().executeQuery(stm);
        
        while(rs.next()){
            pelicula p = new pelicula();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setNombre(rs.getString("nombre"));
            p.setDisponible(rs.getBoolean("disponible"));
            p.setPrecio(Double.parseDouble(rs.getString("precio")));
            peliculas.add(p);
        }
        
        return peliculas;
        
    }
    
    public void update_disponible(int id) throws SQLException, Exception {
        String sqlcommnad = "update peliculas set disponible=? where id=?";
        boolean estado;
        estado = !this.readbyId(id).getDisponible();
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommnad);
        stm.setBoolean(1, estado);
        stm.setInt(2, id);
        Database.instance().executeUpdate(stm);
    }
    
    
}
