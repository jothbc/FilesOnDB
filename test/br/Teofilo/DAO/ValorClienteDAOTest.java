/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import br.Teofilo.Bean.GerarLogErro;
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
        OutputStream outStream = null;
        try {
            //Passa como parametro o tamanho da chave de 128, 192 ou 256 bits
            SecretKey chaveAES = EncriptaDecriptaAES(256);
            File chave = new File("C:\\JCR LOG\\AES.key");
            outStream = new FileOutputStream(chave);
            outStream.write(chaveAES.getEncoded());
            encrypt("C:\\Users\\User\\Desktop\\chaves\\ex.png", "C:\\Users\\User\\Desktop\\chaves\\encrip.png",chaveAES);
            decrypt("C:\\Users\\User\\Desktop\\chaves\\encrip.png", "C:\\Users\\User\\Desktop\\chaves\\decrip.png",chaveAES);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SecretKey EncriptaDecriptaAES(int valorKey) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
            keygenerator.init(valorKey);
            return keygenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
            return null;
        }
    }

    public void encrypt(String srcPath, String destPath, SecretKey chaveAES) {
        try {
            File rawFile = new File(srcPath);
            File imagemEncriptada = new File(destPath);
            InputStream inStream = null;
            OutputStream outStream = null;
            Cipher cifraAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cifraAES.init(Cipher.ENCRYPT_MODE, chaveAES,
                    //Inicializa a cifra para o processo de encriptacao
                    new IvParameterSpec(IV.getBytes("UTF-8")));

            //Inicializa o input e o output streams
            inStream = new FileInputStream(rawFile);
            outStream = new FileOutputStream(imagemEncriptada);

            byte[] buffer = new byte[256];
            int len;

            while ((len = inStream.read(buffer)) > 0) {
                //Para criptografar/descriptografar varios blocos usa-se o metodo update().
                outStream.write(cifraAES.update(buffer, 0, len));
                outStream.flush();
            }

            //Depois de tudo feito chamamos o metodo doFinal().
            outStream.write(cifraAES.doFinal());
            inStream.close();
            outStream.close();
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println(e);
        }
    }

    public void decrypt(String srcPath, String destPath, SecretKey chaveAES) {
        try {
            File encryptedFile = new File(srcPath);
            File decryptedFile = new File(destPath);
            InputStream inStream = null;
            OutputStream outStream = null;

            //Inicializa o cipher para decriptografar
            Cipher cifraAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cifraAES.init(Cipher.DECRYPT_MODE, chaveAES, new IvParameterSpec(IV.getBytes("UTF-8")));

            //Inicializa o input e o output streams
            inStream = new FileInputStream(encryptedFile);
            outStream = new FileOutputStream(decryptedFile);

            byte[] buffer = new byte[256];
            int len;

            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cifraAES.update(buffer, 0, len));
                outStream.flush();
            }

            outStream.write(cifraAES.doFinal());
            inStream.close();
            outStream.close();
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println(e);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEXARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEXARRAY[v & 0x0F];
        }
        return new String(hexChars);
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
