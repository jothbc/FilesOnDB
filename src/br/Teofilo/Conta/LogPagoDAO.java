/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Conta;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import br.Teofilo.DAO.ContaDAO;
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
public class LogPagoDAO {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public LogPagoDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addLogPago(){
        /*
            tirar a responsabilidade do contaDAO de realizar o log
        */
        return true;
    }
    
    public List<LogPago> getLog_ID_CLIENTE(int ID_CLIENTE,String data_inicial,String data_final){
        List<LogPago> log = new ArrayList<>();
        sql = "SELECT * FROM logpago WHERE ID_CLIENTE = ? AND data_pago>=? AND data_pago<=?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CLIENTE);
            stmt.setString(2, CDate.DataPTBRtoDataMySQL(data_inicial));
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(data_final));
            rs = stmt.executeQuery();
            while (rs.next()) {
                LogPago l = new LogPago();
                l.setId(rs.getInt("id"));
                l.setID_CLIENTE(ID_CLIENTE);
                l.setNome(rs.getString("nome_cliente"));
                l.setDesc(rs.getString("descricao"));
                l.setValor_pago(rs.getDouble("valor_pago"));
                l.setData_pago(CDate.DataMySQLtoDataStringPT(rs.getString("data_pago")));
                log.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return log;
    }
    
    public List<LogPago> getLog_GERAL(String data_inicial,String data_final){
        List<LogPago> log = new ArrayList<>();
        sql = "SELECT * FROM logpago WHERE ID_CLIENTE = ? AND data_pago>=? AND data_pago<=?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(data_inicial));
            stmt.setString(2, CDate.DataPTBRtoDataMySQL(data_final));
            rs = stmt.executeQuery();
            while (rs.next()) {
                LogPago l = new LogPago();
                l.setId(rs.getInt("id"));
                l.setID_CLIENTE(rs.getInt("ID_CLIENTE"));
                l.setNome(rs.getString("nome_cliente"));
                l.setDesc(rs.getString("descricao"));
                l.setValor_pago(rs.getDouble("valor_pago"));
                l.setData_pago(CDate.DataMySQLtoDataStringPT(rs.getString("data_pago")));
                log.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return log;
    }
    
    
}
