/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import br.Teofilo.Bean.User;
import funcoes.CDate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UserDAO {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public UserDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addUser(String nome){
        sql = "INSERT INTO users (ip,nome) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, InetAddress.getLocalHost().getHostAddress());
            stmt.setString(2, nome);
            stmt.execute();
            return true;
        } catch (SQLException | UnknownHostException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public User getUser(){
        sql = "SELECT * FROM users WHERE ip = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, InetAddress.getLocalHost().getHostAddress());
            rs = stmt.executeQuery();
            if (!rs.first()){
                return null;
            }
            rs.first();
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setIp(rs.getString("ip"));
            u.setNome(rs.getString("nome"));
            return u;
        } catch (SQLException | UnknownHostException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return null;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }
    
    public User getUltimoControle_Comentarios(User u){
        sql = "SELECT * FROM controle_comentarios WHERE ID_USER = ?";
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, u.getId());
            rs = stmt.executeQuery();
            if (!rs.first()){
                u.setData("01/01/2019");
                u.setHora("12:00:00");
                return u;
            }
            rs.beforeFirst();
            while (rs.next()){
                u.setData(CDate.DataMySQLtoDataStringPT(rs.getString("data")));
                u.setHora(rs.getString("hora"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return u;
    }
    
    public boolean setarControle_Comentarios(User u){
        sql = "INSERT INTO controle_comentarios(data,hora,ID_USER) VALUES (?,?,?)";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(u.getData()));
            stmt.setString(2, u.getHora());
            stmt.setInt(3, u.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
}
