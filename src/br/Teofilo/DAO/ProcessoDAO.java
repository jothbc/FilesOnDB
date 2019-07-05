/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Processo;
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
public class ProcessoDAO {
    Connection con;
    String sql;
    PreparedStatement stmt;
    ResultSet rs;

    public ProcessoDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addProcesso(int id_cliente,String processo){
        sql = "INSERT INTO processos (ID_CLIENTE,processo) VALUES (?,?)";
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, processo);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    public List<Processo> getProcessos(int idCliente) {
        List<Processo> processos = new ArrayList<>();
        sql = "SELECT * FROM processos WHERE ID_CLIENTE = ?";
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
                processos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return processos;
    }

    public boolean existe(int idCliente,String processo) {
        sql = "SELECT * FROM processos WHERE ID_CLIENTE = ? and processo = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, processo);
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return false;
    }
    
}
