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
    String mensajeEnviar;
    
    public Mensaje(String m){
        mensajeEnviar=m;
    }

    public Mensaje() {
    }

    public String getMensajeEnviar() {
        return mensajeEnviar;
    }

    public void setMensajeEnviar(String mensajeEnviar) {
        this.mensajeEnviar = mensajeEnviar;
    }

  
    
    
    
}
