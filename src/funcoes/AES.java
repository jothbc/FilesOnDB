/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcoes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author User
 */
public class AES {

    final protected static char[] HEXARRAY = "0123456789ABCDEF".toCharArray();
    static String IV = "AAAAAAAAAAAAAAAA";

    //exemplo de instanciação
//    public void main() {
//        try {
//            //Passa como parametro o tamanho da chave de 128, 192 ou 256 bits
//            SecretKey chaveAES = gerarChave(256);
//            File chave = new File("C:\\JCR LOG\\AES.key");
//            OutputStream outStream = new FileOutputStream(chave);
//            outStream.write(chaveAES.getEncoded());
//            outStream.close();
//            encrypt("C:\\Users\\User\\Desktop\\chaves\\ex.png", "C:\\Users\\User\\Desktop\\chaves\\encrip.png", chaveAES);
//            decrypt("C:\\Users\\User\\Desktop\\chaves\\encrip.png", "C:\\Users\\User\\Desktop\\chaves\\decrip.png", chaveAES);
//        } catch (FileNotFoundException ex) {
//        } catch (IOException ex) {
//        }
//    }
    public final static File salvarChaveAES(String local, SecretKey aes) {
        try {
            File chave = new File(local + "AES.key");
            OutputStream outStream = new FileOutputStream(chave);
            outStream.write(aes.getEncoded());
            outStream.close();
            return chave;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public final static SecretKey gerarChave(int valorKey) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
            keygenerator.init(valorKey);
            return keygenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
            return null;
        }
    }

    public final static File encrypt(String srcPath, String destPath, SecretKey chaveAES) {
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
            return imagemEncriptada;
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println(e);
            return null;
        }
    }

    public final static File decrypt(String srcPath, String destPath, SecretKey chaveAES, boolean deletarCriptografado) {
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
            if (deletarCriptografado) {
                encryptedFile.delete();
            }
            //a linha a seguir retira o "decrip" do nome do arquivo
            decryptedFile.renameTo(new File(decryptedFile.getParent()+"\\"+decryptedFile.getName().substring(6)));
            return decryptedFile;
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println(e);
            return null;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int x = 0; x < bytes.length; x++) {
            int i = bytes[x] & 0xFF;
            hexChars[x * 2] = HEXARRAY[i >>> 4];
            hexChars[x * 2 + 1] = HEXARRAY[i & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] dado = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            dado[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return dado;
    }
}
