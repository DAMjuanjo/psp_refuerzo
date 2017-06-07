/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author usuario
 */
public class Servidor {
    static int puerto=25000;
    static int ncli=1;
    public static void main(String args[]){
        
        System.out.println("[--------------------------------]");
        System.out.println("[--------------------------------]");
        System.out.println("[------Servidor abierto----------]");
        System.out.println("[--------------------------------]");
        System.out.println("[--------------------------------]");
             
             
        try{
            ServerSocket ser= new ServerSocket(puerto);
            
            do{
            HiloServidor mh=new HiloServidor(ser.accept(), ncli);
            Thread hilo = new Thread(mh);
            hilo.start();
            ncli++;
            }while(true);
            
        }catch(IOException ex){
             System.out.println("Algo paso en el servidor de archivo1: " +ex.getMessage());
        }
    }
    
}
