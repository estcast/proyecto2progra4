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
public class proyeccion {
    
    private int id;
    private int sala_id;
    private int pelicula_id;
    private String fecha;
    private String hora;
    
    public proyeccion(){
        
    }
    
    public proyeccion(int id,int sala_id,int pelicula_id,String fecha,String hora){
        this.id = id;
        this.sala_id = sala_id;
        this.pelicula_id = pelicula_id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public int getSala_id() {
        return sala_id;
    }

    public int getPelicula_id() {
        return pelicula_id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

    public void setPelicula_id(int pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
        
}
