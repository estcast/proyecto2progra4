/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author jamca
 */
public class usuario {
    
    private String id;
    private String nombre;
    private String contrasenna;
    private int rol;
    
    public usuario(){
    }
    
    public usuario(String id, String nombre, String contrasenna, int rol){
    this.id = id;
    this.nombre = nombre;
    this.contrasenna = contrasenna;
    this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public int getRol() {
        return rol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
    
    
    
    
}
