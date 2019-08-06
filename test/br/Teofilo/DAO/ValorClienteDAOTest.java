/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import br.Teofilo.Bean.GerarLogErro;
import funcoes.AES;
import funcoes.CDate;
import funcoes.RSA;
import static funcoes.RSA.ALGORITHM;
import static funcoes.RSA.PATH_CHAVE_PRIVADA;
import static funcoes.RSA.PATH_CHAVE_PUBLICA;
import static funcoes.RSA.decriptografa;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
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
    public void main() {
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
            System.out.println("Chave AES criptografada com rsa, tamanho :"+crip.length*8);
            System.out.println(AES.bytesToHex(crip));
            
            
//            String decrip = RSA.decriptografa(crip, privateKey);                //descriptografa usando chave Privada
//            System.out.println("Chave AES descriptografada com rsa, tamanho: "+decrip.length()*4);
//            System.out.println(decrip);
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


//    @Test
//    public void testRSAAES() {
//        try {
//            File AES = gerarChaveAES();
//            JFileChooser fl = new JFileChooser();
//            fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            fl.setDialogTitle("Selecione o arquivo a ser criptografado");
//            int op = fl.showOpenDialog(null);
//            if (op == JFileChooser.APPROVE_OPTION) {
//                File arquivo = fl.getSelectedFile();
//                String caminho, nome;
//                caminho = fl.getSelectedFile().getParent() + "\\";
//                nome = fl.getSelectedFile().getName();
//                byte[] arq_crip = RSA.encryptAES(arquivo, AES);
//                File fileCrip = new File(caminho + "encrip" + nome);
//                fileCrip.createNewFile();//Criamos um nome para o arquivo
//                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileCrip));    //Criamos o arquivo
//                bos.write(arq_crip);                                                                    //Gravamos os bytes lá
//                bos.close();                                                                            //Fechamos o stream.
//            }
//
////            ObjectInputStream inputStream = null;
////            inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\User\\Desktop\\chaves\\public.key"));
////            final PublicKey chavePublica = (PublicKey) inputStream.readObject();
////            File crip = RSA.criptografa(AES, chavePublica);
////            crip.createNewFile();
////            
////            
////            inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\User\\Desktop\\chaves\\private.key"));
////            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
////            File decrip = decriptografa(crip, chavePrivada);
////            decrip.createNewFile();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    public void testAES() {
//        JFileChooser fl = new JFileChooser();
//        fl.setDialogTitle("Escolha o arquivo para encriptar");
//        fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int op = fl.showOpenDialog(null);
//        if (op == JFileChooser.APPROVE_OPTION) {
//            String local = fl.getSelectedFile().getParent() + "\\";
//            String arquivo = fl.getSelectedFile().getName();
//            try {
//                //arquivo original
//                File f = new File(local + arquivo);
//                byte[] file_bytes = Files.readAllBytes(f.toPath());
//
//                //cript
//                byte[] file_encrip_byte = encrypt(f, chaveencriptacao);
//                File file_encrip_FILE = new File(local + "cript" + arquivo);
//                FileOutputStream ins = new FileOutputStream(file_encrip_FILE);
//                ins.write(file_encrip_byte);
//                ins.close();
//
//                //decrip
//                File file_descr_FILE = new File(local + "desc" + arquivo);
//                FileOutputStream ind = new FileOutputStream(file_descr_FILE);
//                ind.write(decrypt(file_encrip_FILE, chaveencriptacao));
//                ind.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
}
