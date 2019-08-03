/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcoes;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class AES {
    static String IV = "AAAAAAAAAAAAAAAA";
    static String chaveencriptacao = "0123456789abcdef";
    
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
