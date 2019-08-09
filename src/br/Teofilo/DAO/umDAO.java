/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
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
public class umDAO {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public umDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean createTable(){
        sql = "CREATE TABLE IF NOT EXISTS umatabela("
                + "id int not null"
                + ")";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(umDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
}
