
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class ArrayBits {

    public InputStream fazerByte(File f) {
        try {
            InputStream is = new FileInputStream(f);
//            byte[] bytes = new byte[(int) f.length()];
//            int offset = 0;
//            int numRead = 0;
//            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
//                offset += numRead;
//            }
            return is;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public File pegarByte(byte[] bytes, String local, String nomeArquivo,String extensao) {
        File f = null;
        try {
            f = new File(local + nomeArquivo+extensao);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return f;
    }
}
