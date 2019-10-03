/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Documentos;

import br.Teofilo.Bean.GerarLogErro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KeyController {
    
    public static PublicKey getPublicKey() {
        PublicKey RSA_KEY = null;
        JFileChooser fl = new JFileChooser();
        fl.setDialogTitle("Selecione a chave publica.");
        fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int op = fl.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            try {
                //carrega a chave publica para variável pk
                ObjectInputStream inputStream = null;
                inputStream = new ObjectInputStream(new FileInputStream(fl.getSelectedFile()));
                RSA_KEY = (PublicKey) inputStream.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UploadJD.class.getName()).log(Level.SEVERE, null, ex);
                GerarLogErro.gerar(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Algo deu errado, chave não carregada.");
                RSA_KEY = null;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(UploadJD.class.getName()).log(Level.SEVERE, null, ex);
                GerarLogErro.gerar(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Algo deu errado, chave não carregada.");
                RSA_KEY = null;
            }
        }
        return RSA_KEY;
    }
    
    public static PrivateKey getPrivateKey() {
        PrivateKey RSA_KEY = null;
        JFileChooser fl = new JFileChooser();
        fl.setDialogTitle("Selecione a chave privada.");
        fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int op = fl.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            try {
                //carrega a chave publica para variável pk
                ObjectInputStream inputStream = null;
                inputStream = new ObjectInputStream(new FileInputStream(fl.getSelectedFile()));
                RSA_KEY = (PrivateKey) inputStream.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UploadJD.class.getName()).log(Level.SEVERE, null, ex);
                GerarLogErro.gerar(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Algo deu errado, chave não carregada.");
                RSA_KEY = null;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(UploadJD.class.getName()).log(Level.SEVERE, null, ex);
                GerarLogErro.gerar(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Algo deu errado, chave não carregada.");
                RSA_KEY = null;
            }
        }
        return RSA_KEY;
    }
    
    public static PublicKey carregarKey() {
        PublicKey key = null;
        try {
            File file = new File("res/public.key");
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(file));
            key = (PublicKey) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar carregar a chave Publica para encriptação de arquivos.");
        }
        return key;
    }
    
}
