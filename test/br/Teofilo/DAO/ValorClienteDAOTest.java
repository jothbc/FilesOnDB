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
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
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

    static String IV = "AAAAAAAAAAAAAAAA";
    static String chaveencriptacao = "0123456789abcdef";

    @Test
    @Ignore
    public void gerarRSAde128() {
        try {
            KeyGenerator keyGen;
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            File chave = new File("C:\\Users\\User\\Desktop\\chaves\\AES.key");
            chave.createNewFile();
            ObjectOutputStream chaveOs = new ObjectOutputStream(
                    new FileOutputStream(chave));
            chaveOs.writeObject(keyGen.generateKey());
            chaveOs.close();
        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    //@Ignore
    public void testRSAAES() {
        try {
            File AES = new File("C:\\Users\\User\\Desktop\\chaves\\AES.key");
            
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\User\\Desktop\\chaves\\public.key"));
            final PublicKey chavePublica = (PublicKey) inputStream.readObject();
            File crip = RSA.criptografa(AES, chavePublica);
            crip.createNewFile();
            
            
            inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\User\\Desktop\\chaves\\private.key"));
            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
            File decrip = decriptografa(crip, chavePrivada);
            decrip.createNewFile();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ValorClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    @Ignore
    public void testSomeMethod() {
        GerarLogErro.gerar("um teste ja q ainda nao foi nada");
    }

    @Test
    @Ignore
    public void testAES() {
        JFileChooser fl = new JFileChooser();
        fl.setDialogTitle("Escolha o arquivo para encriptar");
        fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int op = fl.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            String local = fl.getSelectedFile().getParent() + "\\";
            String arquivo = fl.getSelectedFile().getName();
            try {
                //arquivo original
                File f = new File(local + arquivo);
                byte[] file_bytes = Files.readAllBytes(f.toPath());

                //cript
                byte[] file_encrip_byte = encrypt(f, chaveencriptacao);
                File file_encrip_FILE = new File(local + "cript" + arquivo);
                FileOutputStream ins = new FileOutputStream(file_encrip_FILE);
                ins.write(file_encrip_byte);
                ins.close();

                //decrip
                File file_descr_FILE = new File(local + "desc" + arquivo);
                FileOutputStream ind = new FileOutputStream(file_descr_FILE);
                ind.write(decrypt(file_encrip_FILE, chaveencriptacao));
                ind.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static byte[] encrypt(File textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(Files.readAllBytes(textopuro.toPath()));
    }

    public static byte[] decrypt(File textoencriptado, String chaveencriptacao) throws Exception {
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return decripta.doFinal(Files.readAllBytes(textoencriptado.toPath()));
    }

}
