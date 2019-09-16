/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Documentos;

import JDBC.ConnectionFactoryMySQL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class DocumentoJFTest {

    public DocumentoJFTest() {
    }

    @Test
    public void test_verificarLeituraDoParamTXT() {
        boolean cartao = false;
        boolean email = false;

        File f = new File("src\\param.txt");
        try {
            InputStream os = new FileInputStream(f);
            byte[] dados = os.readAllBytes();
            os.close();
            String param = new String(dados);

            //processo para obter se esta ativo ou inativo os parametros
            //inicio primeiro parametro
            int p1 = param.indexOf("[debito_automatico:"); //obtem o local onde esta contido essa frase
            //obtem o que vem a seguir de : (no caso tem que ser 1 ou 0, representando ativo ou inativo)
            String p1s = param.substring(p1 + "[debito_automatico:".length(), p1 + "[debito_automatico:".length() + 1);
            if (p1s.equals("1")) {
                cartao = true;
            }
            //fim primeiro parametro

            int p2 = param.indexOf("[lembrete_via_email_automatico:");
            String p2s = param.substring(p2 + "[lembrete_via_email_automatico:".length(), p2 + "[lembrete_via_email_automatico:".length() + 1);
            if (p2s.equals("1")) {
                email = true;
            }

            System.out.println(cartao);
            System.out.println(email);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactoryMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactoryMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
