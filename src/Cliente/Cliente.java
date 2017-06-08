/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.File;
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
        File f= new File("confidencial.des.lol");
        
        if(args.length< 2){
           System.out.println("Error, debes especificar direccion  y puerto!!");
           System.out.println("#javac Cliente [Dir IP] ");
           System.exit(0);
        }
        
        try{
            ip=InetAddress.getByName(args[0]);
            puerto=Integer.parseInt(args[1]);
        
        }catch(UnknownHostException ex){
          System.out.println("Error en la direccion ip del cliente: " +ex.getMessage());
         }catch(NumberFormatException ex){
          System.out.println("Error en el formato del puerto: " +ex.getMessage());
          System.exit(0);
        }
    
        //Inicializamos el socket y la salida y entrada.
      
    }
    
}
