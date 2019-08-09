/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import funcoes.AES;
import funcoes.RSA;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class ValorClienteDAOTest {

    final protected static char[] HEXARRAY = "0123456789ABCDEF".toCharArray();
    static String IV = "AAAAAAAAAAAAAAAA";

    @Test
    @Ignore
    public void testeDeCriptografia() {
        try {
            //Passa como parametro o tamanho da chave de 128, 192 ou 256 bits
            SecretKey chaveAES = AES.gerarChave(256);                      //a chave em si
            System.out.println("Tamanho da chave: "+chaveAES.getEncoded().length);  //tamanho da chave
            System.out.println(AES.bytesToHex(chaveAES.getEncoded()));          //escreve a chave
            
            File chave = new File("C:\\JCR LOG\\AES.key");                      //cria um arquivo para a chave
            OutputStream outStream = new FileOutputStream(chave);               //
            outStream.write(chaveAES.getEncoded());                             //
            outStream.close();                                                  //escreve no arquivo a chave
            
            File chaveEntrada = new File("C:\\JCR LOG\\AES.key");               //cria um novo arquivo para recuperar a chave
            InputStream inpStream = new FileInputStream(chaveEntrada);          //
            byte[] chave_entrada_bytes = inpStream.readAllBytes();              //
            System.out.println("Tamanho da chave obtida: "+chave_entrada_bytes.length); //escreve o tamanho da chave obtida
            System.out.println(AES.bytesToHex(chave_entrada_bytes));            //escreve a chave obtida no arquivo
            
            SecretKey chaveAESResgatada = new SecretKeySpec(chave_entrada_bytes, "AES");
            System.out.println("Chave AES resgatada:");
            System.out.println(AES.bytesToHex(chaveAESResgatada.getEncoded()));
            //testes com a escrita e obtenção da chave realizados com sucesso
            //ambos resultaram na mesma chave sem nenhuma alteração.
            
            
            
            //tentando encriptar a chave com o algoritmo RSA
            File RSA_CHAVE_PUBLICA = new File("C:\\Users\\User\\Desktop\\chaves\\public.key");  //carregar para arquivos as chaves RSA
            File RSA_CHAVE_PRIVADA = new File("C:\\Users\\User\\Desktop\\chaves\\private.key"); 
            
            ObjectInputStream objInpPulic = new ObjectInputStream(new FileInputStream(RSA_CHAVE_PUBLICA)); //carregar para Classes as chaves RSA
            PublicKey publicKey = (PublicKey) objInpPulic.readObject();
            
            ObjectInputStream objInpPrivate = new ObjectInputStream(new FileInputStream(RSA_CHAVE_PRIVADA)); //carregar para Classes as chaves RSA
            PrivateKey privateKey = (PrivateKey) objInpPrivate.readObject();
            
            String chave_AES_em_String = AES.bytesToHex(chave_entrada_bytes);   //chave AES transformada em String
            
            byte[] crip = RSA.criptografa(chave_AES_em_String, publicKey);      //criptografa usando chave Publica
            System.out.println("Chave AES criptografada com rsa, tamanho :"+crip.length);
            System.out.println(AES.bytesToHex(crip));
            
            
            String decrip = RSA.decriptografa(crip, privateKey);                //descriptografa usando chave Privada
            System.out.println("Chave AES descriptografada com rsa, tamanho: "+decrip.length()*4);
            System.out.println(AES.bytesToHex(AES.hexStringToByteArray(decrip)));

            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @Ignore
    public void testTamanho(){
        new DocumentoDAO().ifNotExistTam();
    }
}
