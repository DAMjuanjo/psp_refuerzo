/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.Serializable;

/**
 *
 * @author juanjo
 */
public class MensajesCliente implements Serializable{
    String mensajeCliente;

    public MensajesCliente() {
    }

    public MensajesCliente(String mensajeCliente) {
        this.mensajeCliente = mensajeCliente;
    }

    public String getMensajeCliente() {
        return mensajeCliente;
    }

    public void setMensajeCliente(String mensajeCliente) {
        this.mensajeCliente = mensajeCliente;
    }
    
    
    
}
