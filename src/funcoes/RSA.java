package funcoes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {

    public static final String ALGORITHM = "RSA";
    public static final String PATH_CHAVE_PRIVADA = "C:/keys/private.key";      //Local da chave privada no sistema de arquivos.
    public static final String PATH_CHAVE_PUBLICA = "C:/keys/public.key";       //Local da chave pública no sistema de arquivos.
    static String IV = "AAAAAAAAAAAAAAAA";
    
    //Gera a chave que contém um par de chave Privada e Pública usando 2048
    // bytes. Armazena o conjunto de chaves nos arquivos private.key e public.key
    public static void geraChave() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();
            File chavePrivadaFile = new File(PATH_CHAVE_PRIVADA);
            File chavePublicaFile = new File(PATH_CHAVE_PUBLICA);
            // Cria os arquivos para armazenar a chave Privada e a chave Publica
            if (chavePrivadaFile.getParentFile() != null) {
                chavePrivadaFile.getParentFile().mkdirs();
            }
            chavePrivadaFile.createNewFile();
            if (chavePublicaFile.getParentFile() != null) {
                chavePublicaFile.getParentFile().mkdirs();
            }
            chavePublicaFile.createNewFile();
            //escreve no arquivo a chave publica
            ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
                    new FileOutputStream(chavePublicaFile));                    // Salva a Chave Pública no arquivo
            chavePublicaOS.writeObject(key.getPublic());
            chavePublicaOS.close();
            //escreve no arquivo a chave privada
            ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
                    new FileOutputStream(chavePrivadaFile));                    // Salva a Chave Privada no arquivo
            chavePrivadaOS.writeObject(key.getPrivate());
            chavePrivadaOS.close();

        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e);
        }

    }

    public static boolean verificaSeExisteChavesNoSO() {                        //Verifica se o par de chaves Pública e Privada já foram geradas.
        File chavePrivada = new File(PATH_CHAVE_PRIVADA);
        File chavePublica = new File(PATH_CHAVE_PUBLICA);
        return chavePrivada.exists() && chavePublica.exists();
    }

    public static byte[] criptografa(String texto, PublicKey chave) {           //Criptografa o texto puro usando chave pública.
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, chave);                            // Criptografa o texto puro usando a chave Púlica
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static File criptografa(File f, PublicKey chave) {                   //Criptografa o texto puro usando chave pública.
        byte[] cipherText = null;
        File fileCrip = null;
        try {
            byte[] fileContent = Files.readAllBytes(f.toPath());                //transforma o file em byte[]
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, chave);                            // Criptografa o texto puro usando a chave Púlica
            cipherText = cipher.doFinal(fileContent);
            fileContent = cipherText;
            fileCrip = new File(f.getPath());                            //Criamos um nome para o arquivo
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileCrip)); //Criamos o arquivo
            bos.write(fileContent); //Gravamos os bytes lá
            bos.close(); //Fechamos o stream.
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.err.println(e);
        }
        return fileCrip;
    }

    public static File decriptografa(File f, PrivateKey chave) {        // Criptografa o texto puro usando a chave Púlica
        byte[] dectyptedText = null;
        File fileCrip = null;
        try {
            byte[] fileContent = Files.readAllBytes(f.toPath());
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, chave);                            // Decriptografa o texto puro usando a chave Privada
            dectyptedText = cipher.doFinal(fileContent);
            fileCrip = new File(f.getPath());                            //Criamos um nome para o arquivo
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileCrip)); //Criamos o arquivo
            bos.write(dectyptedText); //Gravamos os bytes lá
            bos.close(); //Fechamos o stream.
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            System.err.println(ex);
        }
        return fileCrip;
    }

    public static String decriptografa(byte[] texto, PrivateKey chave) {        // Criptografa o texto puro usando a chave Púlica
        byte[] dectyptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, chave);                            // Decriptografa o texto puro usando a chave Privada
            dectyptedText = cipher.doFinal(texto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(dectyptedText);
    }

     public static byte[] encrypt(File bytes_puros, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(Files.readAllBytes(bytes_puros.toPath()));
    }

    public static byte[] decrypt(File bytes_encriptados, String chaveencriptacao) throws Exception {
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return decripta.doFinal(Files.readAllBytes(bytes_encriptados.toPath()));
    }
    
    public static void main(String[] args) {
        try {
            if (!verificaSeExisteChavesNoSO()) {                                //// Verifica se já existe um par de chaves, caso contrário gera as chaves..
                geraChave();
            }
            final String msgOriginal = "Quem estiver lendo é gay";
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA)); // Criptografa a Mensagem usando a Chave Pública
            final PublicKey chavePublica = (PublicKey) inputStream.readObject();
            final byte[] textoCriptografado = criptografa(msgOriginal, chavePublica);

            inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));   // Decriptografa a Mensagem usando a Chave Pirvada
            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
            final String textoPuro = decriptografa(textoCriptografado, chavePrivada);

            System.out.println("Mensagem Original: " + msgOriginal);            //texto original
            System.out.println("Mensagem Criptografada: " + textoCriptografado.toString()); //texto criptografado
            System.out.println("Mensagem Decriptografada: " + textoPuro);       //texto descriptografado

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
