/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import funcoes.CDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class EmailDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public EmailDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addRemetente(String email, String senha) {
        sql = "INSERT INTO email_from(email,senha) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    /*
        por enquanto só vai ter um remetente
     */
    public Email getRemetente() {
        sql = "SELECT * FROM email_from";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.first();
            Email e = new Email();
            e.setRemetente(rs.getString("email"));
            e.setSenha(rs.getString("senha"));
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return null;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public List<Email> getDestinatarios(int ID_CARTAO) {
        List<Email> emails = new ArrayList<>();
        sql = "SELECT * FROM email_for WHERE ID_CARTAO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Email e = new Email();
                e.setId(rs.getInt("id"));
                e.setID_CARTAO(ID_CARTAO);
                e.setDestinatario(rs.getString("destinatario"));
                emails.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return emails;
    }

    public boolean addDestinatario(int ID_CARTAO, String destinatario) {
        sql = "INSERT INTO email_for(ID_CARTAO,destinatario) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            stmt.setString(2, destinatario);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removerDestinatario(int id) {
        sql = "DELETE FROM email_for WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public void enviarLembretes() {
        if (lembretesNaoEnviados()) {
            enviarEmails();
            marcarComoEnviado();
        }
    }

    private boolean lembretesNaoEnviados() {
        sql = "SELECT * FROM controle_email WHERE data = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    private void marcarComoEnviado() {
        
    }

    private void enviarEmails() {
        List<Cartao> cartoes = new CartaoDAO().findAll();
        for (Cartao c : cartoes) {
            if (c.getEntrega() != null) {

            }
        }
    }
}
