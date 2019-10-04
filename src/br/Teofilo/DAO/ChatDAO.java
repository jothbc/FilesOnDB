/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class ChatDAO extends DAO {
    
    public ChatDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public void addComentario(String user, String comentario) {
        sql = "INSERT INTO historico_chat(usuario,texto) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, comentario);
            stmt.execute();
        } catch (SQLException ex) {
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
}
