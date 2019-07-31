package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Documento;
import br.Teofilo.Bean.DocumentoPessoal;
import br.Teofilo.Bean.GerarLogErro;
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

    public boolean addDocumento(File f, int idCliente, int tipo, int processo) {
        sql = "INSERT INTO documentos (nome,ID_CLIENTE,modificacao,ID_TIPO,ID_PROCESSO) values (?,?,?,?,?)";
        try {
            InputStream is = new FileInputStream(f);
            //InputStream is_c = new FileInputStream(RSA.criptografa(f, RSA.));
            stmt = con.prepareStatement(sql);
            //stmt.setBlob(1, is);
            stmt.setString(1, f.getName());
            stmt.setInt(2, idCliente);
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(4, tipo);
            stmt.setInt(5, processo);
            stmt.execute();
            sql = "SELECT LAST_INSERT_ID() INTO @id";
            stmt = con.prepareStatement(sql);
            stmt.execute();
            sql = "INSERT INTO documentos_arq (id,arq) VALUES (@id,?)";
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.execute();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean addDocumentoPessoal(File f, int idCliente) {
        sql = "INSERT INTO documentos_pessoais (nome,ID_CLIENTE,alteracao) values (?,?,?)";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            //stmt.setBlob(1, is);
            stmt.setString(1, f.getName());
            stmt.setInt(2, idCliente);
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.execute();
            sql = "SELECT LAST_INSERT_ID() INTO @id";
            stmt = con.prepareStatement(sql);
            stmt.execute();
            sql = "INSERT INTO documentos_pessoais_arq (id,arq) VALUES (@id,?)";
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.execute();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    //retorna o file em si
    public File getArquivo(int id, String local, String tabela) {
        File f = null;
        sql = "SELECT id,nome from " + tabela + " where id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            String nome_temp = local + rs.getString("nome");
            sql = "SELECT * FROM " + tabela + "_arq WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            f = new File(nome_temp);
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(rs.getBytes("arq"));
                fos.close();
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
        return f;
    }

    //nao retorna o file em si
    public List<Documento> getDocumentosDeProcessoETipo(int idCliente, int processo, int tipo) {
        List<Documento> arquivos = new ArrayList<>();
        sql = "SELECT id,nome,modificacao,status,ID_PROCESSO,ID_TIPO FROM documentos WHERE ID_CLIENTE = ?"
                + " and ID_PROCESSO = ? and ID_TIPO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, processo);
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
                d.setID_PROCESSO(rs.getInt("ID_PROCESSO"));
                d.setID_TIPO(rs.getInt("ID_TIPO"));
                arquivos.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return arquivos;
    }

    //nao retorna o file em si
    public List<DocumentoPessoal> getDocumentosPessoais(int id) {
        List<DocumentoPessoal> pessoal = new ArrayList<>();
        sql = "SELECT * FROM documentos_pessoais WHERE ID_CLIENTE = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                DocumentoPessoal p = new DocumentoPessoal();
                p.setID_CLIENTE(rs.getInt("ID_CLIENTE"));
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                if (rs.getString("alteracao") != null) {
                    p.setAlteracao(CDate.DataMySQLtoDataStringPT(rs.getString("alteracao")));
                }
                pessoal.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return pessoal;
    }

    public boolean updateDocumento(File f, int idArquivo, int tipo, int processo) {
        sql = "UPDATE documentos SET nome = ?, modificacao = ? WHERE ID_TIPO = ? and ID_PROCESSO = ? and id = ?";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            //stmt.setBlob(1, is);
            stmt.setString(1, f.getName());
            stmt.setString(2, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(3, tipo);
            stmt.setInt(4, processo);
            stmt.setInt(5, idArquivo);
            stmt.executeUpdate();
            sql = "UPDATE documentos_arq SET arq = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.setInt(2, idArquivo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateDocumentoPessoal(File f, int idArquivo) {
        sql = "UPDATE documentos_pessoais SET nome = ?, alteracao = ? WHERE id = ?";
        try {
            InputStream is = new FileInputStream(f);
            stmt = con.prepareStatement(sql);
            //stmt.setBlob(1, is);
            stmt.setString(1, f.getName());
            stmt.setString(2, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(3, idArquivo);
            stmt.executeUpdate();
            sql = "UPDATE documentos_pessoais_arq SET arq = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, is);
            stmt.setInt(2, idArquivo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Informe o programador", JOptionPane.ERROR_MESSAGE);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public boolean removeDocumento(Documento d) {
        sql = "DELETE FROM documentos WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, d.getId());
            stmt.execute();
            sql = "DELETE FROM documentos_arq WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, d.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removeDocumentoPessoal(DocumentoPessoal dp) {
        sql = "DELETE FROM documentos_pessoais WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, dp.getId());
            stmt.execute();
            sql = "DELETE FROM documentos_pessoais_arq WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, dp.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

}
