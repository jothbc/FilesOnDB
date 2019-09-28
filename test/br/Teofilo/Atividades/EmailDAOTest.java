/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class EmailDAOTest {

    public EmailDAOTest() {
    }

    @Test
    @Ignore
    public void testSomeMethod() {
        List<File> files = new ArrayList<>();
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jf.showOpenDialog(null);
        if (jf.getSelectedFile().isDirectory()) { //verifica se é diretório
            File diretorio = jf.getSelectedFile();  //somente diretório sem \\ no fim
            //caminhotxt.setText(diretorio.getPath() + "\\");
            String[] arq_string = jf.getSelectedFile().list(); //contém o caminho completo de todos os arquivos dentro da pasta arquivo
            for (String f : arq_string) {
                File f_temp = new File(diretorio + "\\" + f);
                if (f_temp.isFile()) {
                    files.add(f_temp);
                }
            }
        } else { //senao é arquivo
            File f_temp = jf.getSelectedFile();
            if (f_temp.isFile()) {
                files.add(f_temp);
            }
        }

        for (File f : files) {
            System.out.println(f.getPath());
        }

    }

}
