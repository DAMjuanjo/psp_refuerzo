/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensajes;
import java.io.Serializable;

/**
 *
 * @author prohibido
 */
public class Mensaje implements Serializable{
    String nombre;
    
    public Mensaje(String m){
        nombre=m;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
