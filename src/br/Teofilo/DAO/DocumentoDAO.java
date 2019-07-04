package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Documento;
import br.Teofilo.Bean.InfoArquivo;
import funcoes.CDate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public boolean addArquivo(File f, int idCliente, int tipo, String processo) {
        sql = "INSERT INTO documentos (documento,nome,id_cliente,modificacao,id_tipo,processo) values (?,?,?,?,?,?)";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.setString(2, f.getName());
            stmt.setInt(3, idCliente);
            stmt.setString(4, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(5, tipo);
            stmt.setString(6, processo);
            stmt.execute();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public File getArquivo(int id, String local, String tabela) {
        File f = null;
        sql = "SELECT id,nome,documento from "+tabela+" where id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            f = new File(local + rs.getString("nome"));
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(rs.getBytes("documento"));
                fos.close();
            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
        return f;
    }

    public List<Documento> getDocumentosDeProcessoETipo(int idCliente, String processo, int tipo) {
        List<Documento> arquivos = new ArrayList<>();
        sql = "SELECT id,nome,modificacao,status,processo,id_tipo FROM documentos WHERE id_cliente = ?"
                + " and processo = ? and id_tipo = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, processo);
            stmt.setInt(3, tipo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Documento d = new Documento();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                if (rs.getString("modificacao") != null) {
                    d.setModificacao(CDate.DataMySQLtoDataStringPT(rs.getString("modificacao")));
                }
                d.setStatus(rs.getString("status"));
                d.setProcesso(rs.getString("processo"));
                d.setID_TIPO(rs.getInt("id_tipo"));
                arquivos.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return arquivos;
    }

    public boolean updateArquivo(File f, int idArquivo, int tipo, String processo) {
        sql = "UPDATE documentos SET documento = ?, nome = ?, modificacao = ?, id_tipo = ?,processo = ? WHERE id = ?";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.setString(2, f.getName());
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(4, idArquivo);
            stmt.setInt(5, tipo);
            stmt.setString(6, processo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

}
