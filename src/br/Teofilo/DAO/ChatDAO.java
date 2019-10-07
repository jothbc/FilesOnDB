/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public String getHistorico(String usuario) {
        sql = "SELECT * FROM historico_chat";
        String retorno = "";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (usuario.equals(rs.getString("usuario"))) {
                    retorno += "                                                                  [Você]\n" + 
                            "                                                                  "+rs.getString("texto") + "\n";
                } else {
                    retorno += "[" + rs.getString("usuario") + "]\n" + rs.getString("texto") + "\n";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return retorno;
    }

}
