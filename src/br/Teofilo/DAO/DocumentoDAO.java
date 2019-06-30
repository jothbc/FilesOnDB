package br.Teofilo.DAO;


import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.InfoArquivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class DocumentoDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public DocumentoDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addArquivo(File f,int idCliente) {
        sql = "INSERT INTO documentos (documento,nome,id_cliente) values (?,?,?)";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.setString(2, f.getName());
            stmt.setInt(3, idCliente);
            stmt.execute();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public File getArquivo(int id,String local) {
        File f = null;
        sql = "SELECT * from documentos where id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            f = new File(local+rs.getString("nome"));
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(rs.getBytes("documento"));
                fos.close();
            }
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
        return f;
    }
    
    public List<InfoArquivo> getInfoArquivos(int idCliente){
        List<InfoArquivo> arquivos = new ArrayList<>();
        sql = "SELECT id,nome FROM documentos WHERE id_cliente = ?";
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while (rs.next()){
                InfoArquivo i = new InfoArquivo();
                i.setId(rs.getInt("id"));
                i.setNome(rs.getString("nome"));
                arquivos.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return arquivos;
    }

}
