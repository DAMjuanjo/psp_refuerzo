/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author prohibido
 */
public class Cliente {
    static int puerto=25000;
    static InetAddress ip=null;
    
    public static void main(String args[]){
        if(args.length< 2){
           System.out.println("Error, debes especificar direccion  y puerto!!");
           System.out.println("#javac Cliente [Dir IP] ");
           System.exit(0);
        }
        
        try{
            ip=InetAddress.getByName("localhost");
        
        }catch(UnknownHostException ex){
          System.out.println("Error en la direccion ip del cliente: " +ex.getMessage());
        }
    
      //nuevos cambios
    }
    
}
