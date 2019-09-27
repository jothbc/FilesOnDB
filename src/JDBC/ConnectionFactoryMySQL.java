/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import br.Teofilo.Bean.GerarLogErro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author JCR
 *
 * Classe responsável pela conexão com o banco de dados MySQL
 *
 * User: root Password: ""
 *
 */
public class ConnectionFactoryMySQL {

    private static final String DATABASE = "teofilo";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://" + ip() + "/" + DATABASE;
//    private static final String URL = "jdbc:mysql://localhost/" + DATABASE;
    private static final String USER = "root";
    //private static final String USER = "teofilo";
    private static final String PASS = "";
    //private static final String PASS = "35v2l3x6AtWIglMg";
    public static final String PATH = "C:\\JCR\\";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro na conexão com o Banco de dados.","Erro",JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
            throw new RuntimeException("Erro na conexão MySQL", ex);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("ERRO MySQL: " + ex);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("ERRO MySQL: " + ex);
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("ERRO MySQL: " + ex);
            }
        }
        closeConnection(con, stmt);
    }

    public static String ip() {
        //File f = new File(ConnectionFactoryMySQL.class.getResource("/JDBC/ip_db.txt").getFile());
        //InputStream test = Thread.currentThread().getClass().getResourceAsStream("/JDBC/ip_db.txt");
        //System.out.println(test);
        //File f = new File(test.toString());
        //File f = new File("ip_db.txt");
        File f = new File(PATH + "ip_db.txt");
        try {
            InputStream os = new FileInputStream(f);
            byte[] dados = os.readAllBytes();
            os.close();
            String ipString = new String(dados);
            System.out.print("IP do servidor: " + ipString);
            return ipString;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactoryMySQL.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactoryMySQL.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }
        return "localhost";
    }
}
