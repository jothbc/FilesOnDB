/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
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
public class CartaoDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public CartaoDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addCartao(Cartao c) {
        sql = "INSERT INTO cartao (ID_LISTAS_ATIVIDADES,titulo,cor,entrega,descricao) VALUES (?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getID_LISTA_ATIVIDADES());
            stmt.setString(2, c.getTitulo());
            stmt.setString(3, c.getCor());
            if (c.getEntrega() != null) {
                stmt.setString(4, CDate.DataPTBRtoDataMySQL(c.getEntrega()));
            } else {
                stmt.setString(4, null);
            }
            stmt.setString(5, c.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateCartao_Lista(int id, int lista) {
        sql = "UPDATE cartao SET ID_LISTAS_ATIVIDADES = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, lista);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateCartao_Titulo(int id, String titulo) {
        sql = "UPDATE cartao SET titulo = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateCartao_Cor(int id, String cor) {
        sql = "UPDATE cartao SET cor = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateCartao_Entrega(int id, String entrega) {
        sql = "UPDATE cartao SET entrega = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, entrega);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateCartao_Descricao(int id, String descricao) {
        sql = "UPDATE cartao SET ID_LISTAS_ATIVIDADES = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removeCartao(int id) {
        sql = "DELETE FROM cartao WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    private List<Cartao> getCartoesSemComentarios() {
        List<Cartao> cartoes = new ArrayList<>();
        sql = "SELECT * FROM cartao";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cartao c = new Cartao();
                c.setId(rs.getInt("id"));
                c.setID_LISTA_ATIVIDADES(rs.getInt("ID_LISTAS_ATIVIDADES"));
                c.setTitulo(rs.getString("titulo"));
                c.setCor(rs.getString("cor"));
                if (rs.getString("entrega") != null) {
                    c.setEntrega(CDate.DataMySQLtoDataStringPT(rs.getString("entrega")));
                }
                c.setDescricao(rs.getString("descricao"));
                cartoes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return cartoes;
    }

    /*
     Já retorna a lista de cartoes contendo seus respectivos comentairos e checks.
     */
    public List<Cartao> findAll() {
        List<Cartao> cartoes = getCartoesSemComentarios();
        List<Comentario> comentarios = new ComentarioDAO().findAll();
        List<Check> checks = new CheckDAO().findAll();
        for (Cartao c : cartoes) {
            for (Comentario cc : comentarios) {
                if (c.getId() == cc.getID_CARTAO()) {
                    c.addComentario(cc);
                }
            }
            for (Check cc : checks) {
                if (c.getId() == cc.getID_CARTAO()) {
                    c.addCheck(cc);
                }
            }
        }
        return cartoes;
    }
    
    
}
