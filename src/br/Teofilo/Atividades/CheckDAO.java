/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
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
public class CheckDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public CheckDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addCheck(int idCartao, String nome) {
        sql = "INSERT INTO cartao_check (ID_CARTAO,concluido,nome) VALUES (?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCartao);
            stmt.setBoolean(2, false);
            stmt.setString(3, nome);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public boolean removeCheck(int id){
        sql = "DELETE FROM cartao_check WHERE id = ?";
        try {
            stmt =con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public List<Check> findAll(){
        List<Check> checks = new ArrayList<>();
        sql = "SELECT * FROM cartao_check";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Check c = new Check();
                c.setId(rs.getInt("id"));
                c.setID_CARTAO(rs.getInt("ID_CARTAO"));
                c.setConcluido(rs.getBoolean("concluido"));
                c.setNome(rs.getString("nome"));
                checks.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return checks;
    }
    
}
