/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

import funcoes.CDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class GerarLogErro {

    public static final String PATH = "C:\\JCR LOG\\log - ";

    public final static void gerar(String l) {
        try {
            File f = new File(PATH);
            if (f.getParentFile()!=null){
                f.getParentFile().mkdirs();
            }
            FileWriter arq = new FileWriter(PATH+"Data "+CDate.DataPTBRAtual().replaceAll("/", "-")+" Hora "+CDate.getHoraAtualPTBR().replaceAll(":", "-")+".txt");
            try (PrintWriter gravarArq = new PrintWriter(arq)) {
                gravarArq.append(l);
            }
        } catch (IOException ex) {
            Logger.getLogger(GerarLogErro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
