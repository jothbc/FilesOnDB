/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Comentario;
import br.Teofilo.Bean.GerarLogErro;
import funcoes.CDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ComentarioDAO {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ComentarioDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addComentario(String coment,int ID_USER){
        sql = "INSERT INTO comentarios (data,hora,ID_USER,comentario) VALUES (?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setString(2, CDate.getHoraAtualPTBR());
            stmt.setInt(3, ID_USER);
            stmt.setString(4, coment);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public List<Comentario> getComentarios(){
        List<Comentario> comentarios = new ArrayList<>();
        sql = "SELECT * FROM coments_vw";
        try {
            stmt= con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setID_USER(rs.getInt("ID_USER"));
                c.setHora(rs.getString("hora"));
                c.setData(CDate.DataMySQLtoDataStringPT(rs.getString("data")));
                c.setComentario(rs.getString("comentario"));
                c.setNome(rs.getString("nome"));
                comentarios.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return comentarios;
    }
}
