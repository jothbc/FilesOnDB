/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Cliente;
import br.Teofilo.Bean.Processo;
import br.Teofilo.Bean.TipoDoc;
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
public class TipoDocDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public TipoDocDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public List<TipoDoc> findAll() {
        List<TipoDoc> tipos = new ArrayList<>();
        sql = "SELECT * from tipo";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoDoc t = new TipoDoc();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("descricao"));
                tipos.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return tipos;
    }

    public boolean addTipo(String tipo) {
        sql = "INSERT INTO tipo (descricao) VALUES (?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removeTipo(int id) {
        sql = "DELETE FROM tipo WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean existe(String tipodoc, int idProcesso, int idCliente) {
        sql = "SELECT * from tipo WHERE descricao = ? AND ID_PROCESSO = ? AND ID_CLIENTE =?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipodoc);
            stmt.setInt(2, idProcesso);
            stmt.setInt(3, idCliente);
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean addTipoDoc(String tipodoc, int idProcesso, int idCliente) {
        sql = "INSERT INTO tipo (descricao,ID_PROCESSO,ID_CLIENTE) VALUES (?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipodoc);
            stmt.setInt(2, idProcesso);
            stmt.setInt(3, idCliente);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public List<TipoDoc> findAllByClienteEProcesso(Cliente c, Processo p) {
        List<TipoDoc> tipos = new ArrayList<>();
        sql = "SELECT * from tipo WHERE ID_CLIENTE = ? AND ID_PROCESSO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.setInt(2, p.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoDoc t = new TipoDoc();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("descricao"));
                tipos.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDocDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return tipos;
    }
}
