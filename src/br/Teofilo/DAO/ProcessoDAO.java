/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import br.Teofilo.Bean.Processo;
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
 * @author Jonathan
 */
public class ProcessoDAO extends DAO{

    public ProcessoDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addProcesso(int id_cliente, String processo) {
        sql = "INSERT INTO processos (ID_CLIENTE,processo) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, processo);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean addProcesso(int id_cliente, String processo, String data) {
        sql = "INSERT INTO processos (ID_CLIENTE,processo,data) VALUES (?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, processo);
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(data));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public List<Processo> getProcessos(int idCliente) {
        List<Processo> processos = new ArrayList<>();
        sql = "SELECT * FROM processos WHERE ID_CLIENTE = ? ORDER BY processo";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Processo p = new Processo();
                p.setID_CLIENTE(rs.getInt("ID_CLIENTE"));
                p.setId(rs.getInt("id"));
                p.setN_processo(rs.getString("processo"));
                p.setStatus(rs.getString("status"));
                if (rs.getString("data") != null) {
                    p.setData(CDate.DataMySQLtoDataStringPT(rs.getString("data")));
                }
                processos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return processos;
    }

    public boolean existe(int idCliente, String processo) {
        sql = "SELECT * FROM processos WHERE ID_CLIENTE = ? and processo = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, processo);
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean editarStatus(String status, int id) {
        sql = "UPDATE processos SET status = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public Processo getProcesso(String processo) {
        sql = "SELECT * FROM processos WHERE processo = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, processo);
            rs = stmt.executeQuery();
            rs.first();
            if (!rs.isFirst()){
                return null;
            }
            Processo p = new Processo();
            p.setID_CLIENTE(rs.getInt("ID_CLIENTE"));
            p.setId(rs.getInt("id"));
            p.setN_processo(rs.getString("processo"));
            p.setStatus(rs.getString("status"));
            if (rs.getString("data") != null) {
                p.setData(CDate.DataMySQLtoDataStringPT(rs.getString("data")));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return null;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }
}
