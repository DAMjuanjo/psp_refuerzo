/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author usuario
 */
public class HiloServidor implements Runnable{
    static Socket con;
    static int id_copia;
    ObjectInputStream IN;
    ObjectOutputStream OUT;
    FileInputStream ABRIR;
    FileOutputStream LEER;
    static String sha256;
    File fichero= new File("confidencial.txt");
    
    
    
    public HiloServidor(Socket c, int ncli){
        con=c;
        id_copia=ncli;
    }

    @Override
    public void run() {
        try{
           
            ObjectInputStream in=new ObjectInputStream(con.getInputStream());
            ObjectOutputStream salida= new ObjectOutputStream(con.getOutputStream());
            FileInputStream abrir=new FileInputStream(fichero);
            FileOutputStream leer=new FileOutputStream(fichero);
            
            IN=in;
            OUT=salida;
            ABRIR=abrir;
            LEER=leer;
            
            
            System.out.println("LLegando peticion de una maquina "+con.getLocalAddress());
            
            System.out.println("Haciendo suma SHA256 al fichero: "+fichero.getName()+ ".des_"+id_copia);
            checkSum();
            System.out.println("SUMA: "+sha256);

            System.out.println("creando fichero.des_"+id_copia);
            encriptarFichero();
            
            
            
        }catch(IOException ex){
            System.out.println("Error en la clase hilo servidor.." +ex.getMessage());
        }
        
    }
    
    public void checkSum() {
        byte textBytes[]=new byte[1024];
        MessageDigest md=null;
        int read=0;
       
        try{     
            md=MessageDigest.getInstance("SHA-256");
            while((read=ABRIR.read(textBytes))>0){
                md.update(textBytes,0,read);
            }
            byte []sha256sum=md.digest();
            Base64.Encoder codi = Base64.getEncoder();
            String ba = codi.encodeToString(sha256sum);
            System.out.println("Suma SHA-256: " + ba +"\n");
            
        
        }catch(IOException | NoSuchAlgorithmException ex){
               System.out.println("Algo paso en el checkSum: " +ex.getMessage());
        }
  
        
    }

    public void encriptarFichero() {
        String password="pass2017";
        byte[] contenedor_clave=null;
        byte[] contenedor_clavePri;
        byte[] bfrase=null;
        
        
        KeySpec ks;
        SecretKeyFactory skf;
        Cipher cifra;
        SecretKey clave_simetrica;
        
        try{
            contenedor_clave=password.getBytes("UTF8");
            contenedor_clavePri=copyOf(contenedor_clave, 24);
             ks= new DESedeKeySpec(contenedor_clavePri);
            skf=SecretKeyFactory.getInstance("DESede");
            clave_simetrica=skf.generateSecret(ks);
            cifra=Cipher.getInstance("DESede");
            
            cifra.init(Cipher.ENCRYPT_MODE, clave_simetrica);
            
           BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
           CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(fichero), cifra);
            int lectura=0;
            while((lectura=bis.read(bfrase))>0){
                cos.write(bfrase, 0, lectura);
                cos.flush();
            }
            cos.close();
            
        }catch(InvalidKeyException ex){
            System.out.println("Algo paso en el cifrado de archivo1: " +ex.getMessage());
        }catch(InvalidKeySpecException ex){
            System.out.println("Algo paso en el cifrado de archivo2: " +ex.getMessage());
        }catch(NoSuchAlgorithmException ex){
            System.out.println("Algo paso en el cifrado de archivo3: " +ex.getMessage());
        }catch(NoSuchPaddingException ex){
            System.out.println("Algo paso en el cifrado de archivo4: " +ex.getMessage());
        }catch(UnsupportedEncodingException ex){
            System.out.println("Algo paso en el cifrado de archivo5: " +ex.getMessage());
        }catch(IOException ex){
            System.out.println("Algo paso en el cifrado de archivo6: " +ex.getMessage());
        }
       
}
    public void enviaFichero(ObjectOutputStream oos){
        
        try {
            //Abrimos fichero
            FileInputStream fis = new FileInputStream(fichero);
            Fichero_Envio f= new Fichero_Envio();
            f.setNombre(fichero.getName());
            //leemos los primeros bytes del fichero
            int leidos;
            do{
                leidos = fis.read(f.contenidoFichero);
                if(leidos<1024){
                    f.setUltimobyte(true);
                    f.bytesvalidos=leidos;
                }
                else {
                    f.setUltimobyte(false);
                    f.bytesvalidos=1024;
                }
                //enviamos por el socket
                oos.writeObject(f);
                if(f.isUltimobyte()) 
                    break;
                //Creamos un NUevo mensaje
                f = new Fichero_Envio();
               f.setNombre(fichero.getName());
                //volvemos a lerr
             }while(leidos>-1);
            
        } catch (FileNotFoundException ex) {
             System.out.println("Algo paso en el envio del fichero 1: " +ex.getMessage());
        } catch (IOException ex) {
             System.out.println("Algo paso en el envio del fichero 1: " +ex.getMessage());

        }
    }
    
}

