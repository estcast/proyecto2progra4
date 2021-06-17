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
public class pelicula {
    
    private int id;
    private String nombre;
    private Double precio;
    private Boolean disponible;


    public pelicula(){
        disponible = false;
    }
    
    public pelicula(int id,String nombre, Double precio, Boolean disponible){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;    
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
    

}
