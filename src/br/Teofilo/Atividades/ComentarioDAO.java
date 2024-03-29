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
public class ComentarioDAO {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ComentarioDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addComentario(Comentario c){
        sql = "INSERT INTO cartao_comentario(ID_CARTAO,usuario,data_hora,comentario) VALUES (?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getID_CARTAO());
            stmt.setString(2, c.getUsuario());
            stmt.setString(3, c.getData_hora());
            stmt.setString(4, c.getComentario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public boolean removeComentario(int id){
        sql = "DELETE FROM cartao_comentario WHERE id = ?";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public boolean removeComentariosDoCartao(int ID_CARTAO){
        sql = "DELETE FROM cartao_comentario WHERE ID_CARTAO = ?";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public List<Comentario> findAll(){
        List<Comentario> comentarios = new ArrayList<>();
        sql = "SELECT * FROM cartao_comentario";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setID_CARTAO(rs.getInt("ID_CARTAO"));
                c.setUsuario(rs.getString("usuario"));
                c.setData_hora(rs.getString("data_hora"));
                c.setComentario(rs.getString("comentario"));
                comentarios.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return comentarios;
    }
    
    public List<Comentario> getComentarioCartao(int ID_CARTAO){
        List<Comentario> comentarios = new ArrayList<>();
        sql = "SELECT * FROM cartao_comentario WHERE id_CARTAO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            rs = stmt.executeQuery();
            while (rs.next()){
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setID_CARTAO(rs.getInt("ID_CARTAO"));
                c.setUsuario(rs.getString("usuario"));
                c.setData_hora(rs.getString("data_hora"));
                c.setComentario(rs.getString("comentario"));
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
