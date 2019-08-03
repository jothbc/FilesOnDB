/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import br.Teofilo.Bean.GerarLogErro;
import funcoes.CDate;
import static funcoes.RSA.PATH_CHAVE_PUBLICA;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
    public void testSomeMethod() {
        GerarLogErro.gerar("um teste ja q ainda nao foi nada");
    }

    @Test
    public void testAES() {
        JFileChooser fl = new JFileChooser();
        fl.setDialogTitle("Escolha o arquivo para encriptar");
        fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int op = fl.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            String local = fl.getSelectedFile().getParent()+"\\";
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
