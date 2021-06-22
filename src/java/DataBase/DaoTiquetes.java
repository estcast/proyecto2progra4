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
import logic.tiquete;

/**
 *
 * @author jamca
 */
public class DaoTiquetes {
    
    public tiquete from (ResultSet rs){
        try{
            tiquete p = new tiquete();
            p.setId(rs.getInt("id"));
            p.setAsiento(rs.getString("asiento"));
            p.setId_cliente(rs.getString("id_cliente"));
            p.setId_proyeccion(rs.getInt("id_proyeccion"));
            p.setTarjeta(rs.getString("tarjeta"));
            
            return p;
        } catch (SQLException ex) {
            return null;
        }
    } 
    /*
    public tiquete create(tiquete pel) throws SQLException, Exception {
        String sqlcommand = "insert into tiquetes (id_proyeccion,id_cliente,asiento) "
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, pel.getId_proyeccion());
        stm.setString(2, pel.getId_cliente());
        stm.setString(3, pel.getAsiento());
        stm.setString(4, pel.getTarjeta());
        
        int count = Database.instance().executeUpdate(stm);
        if(count == 0){
            throw new Exception("Proyeccion ya existe");
        }
        return pel;
    }
    */
    
    public tiquete create(tiquete pel) throws SQLException, Exception {
        String sqlcommand = "insert into tiquetes (id_proyeccion,id_cliente,tarjeta,asiento) "
                + "values(?,?,?,?)";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);

        stm.setInt(1, pel.getId_proyeccion());
        stm.setString(2, pel.getId_cliente());
        stm.setString(3, pel.getTarjeta());
        stm.setString(4, pel.getAsiento());


        int count = Database.instance().executeUpdate(stm);
        if(count == 0){
            throw new Exception("Proyeccion ya existe");
        }
        return pel;
    }
    
    public List<tiquete> readbyId (int id) throws Exception{
        List<tiquete> tiquetes = Collections.synchronizedList(new ArrayList<tiquete>());
        
        String sqlcommand = "select * from tiquetes where id_cliente =?";
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        while(rs.next()){
            tiquete p = new tiquete();
            p.setId(rs.getInt("id"));
            p.setAsiento(rs.getString("asiento"));
            p.setId_cliente(rs.getString("id_cliente"));
            p.setId_proyeccion(rs.getInt("id_proyeccion"));
            p.setTarjeta(rs.getString("tarjeta"));
            tiquetes.add(p);          
        }
        return tiquetes;
    }
    
    public List<tiquete> listaTiquetes() throws Exception{
        
        String sqlcommand = "select * from tiquetes";
        List<tiquete> tiquetes = Collections.synchronizedList(new ArrayList<tiquete>());
        
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
       
        ResultSet rs = Database.instance().executeQuery(stm);
        
        while(rs.next()){
            tiquete p = new tiquete();
            p.setId(rs.getInt("id"));
            p.setAsiento(rs.getString("asiento"));
            p.setId_cliente(rs.getString("id_cliente"));
            p.setId_proyeccion(rs.getInt("id_proyeccion"));
            p.setTarjeta(rs.getString("tarjeta"));
            tiquetes.add(p);
            
        }
        return tiquetes;
    }
    
    public List<tiquete> asientosOcupadosProyeccion(int id) throws Exception{
        String sqlcommand = "select * from tiquetes where id_proyeccion = ?";
        List<tiquete> asientos_ocupados = Collections.synchronizedList(new ArrayList<tiquete>());
        
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setInt(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
        
        while(rs.next()){
            tiquete p = new tiquete();
            p.setId(rs.getInt("id"));
            p.setAsiento(rs.getString("asiento"));
            p.setId_cliente(rs.getString("id_cliente"));
            p.setId_proyeccion(rs.getInt("id_proyeccion"));
            p.setTarjeta(rs.getString("tarjeta"));
            asientos_ocupados.add(p);
            
        }
        return asientos_ocupados;
    }
    
    
    public List<tiquete> tiquetesDeCliente(String id) throws Exception{
         String sqlcommand = "select * from tiquetes where id_cliente = ?";
          List<tiquete> asientos_cliente = Collections.synchronizedList(new ArrayList<tiquete>());
        PreparedStatement stm = Database.instance().prepareStatement(sqlcommand);
        stm.setString(1, id);
        ResultSet rs = Database.instance().executeQuery(stm);
      while(rs.next()){
          tiquete p = new tiquete();
            p.setId(rs.getInt("id"));
            p.setAsiento(rs.getString("asiento"));
            p.setId_cliente(rs.getString("id_cliente"));
            p.setId_proyeccion(rs.getInt("id_proyeccion"));
            p.setTarjeta(rs.getString("tarjeta"));
            asientos_cliente.add(p);
      }
      return asientos_cliente;
        
    }
    
    
}