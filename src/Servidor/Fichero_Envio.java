/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Fichero_Envio implements Serializable{
    String nombre;
    boolean ultimobyte;
    byte[] contenidoFichero=new byte[10];
    int bytesvalidos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isUltimobyte() {
        return ultimobyte;
    }

    public void setUltimobyte(boolean ultimobyte) {
        this.ultimobyte = ultimobyte;
    }

    public byte[] getContenidoFichero() {
        return contenidoFichero;
    }

    public void setContenidoFichero(byte[] contenidoFichero) {
        this.contenidoFichero = contenidoFichero;
    }

    public int getBytesvalidos() {
        return bytesvalidos;
    }

    public void setBytesvalidos(int bytesvalidos) {
        this.bytesvalidos = bytesvalidos;
    }
    
    
    
}
