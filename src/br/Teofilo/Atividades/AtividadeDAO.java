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
public class AtividadeDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public AtividadeDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addAtividade(String nome) {
        sql = "INSERT INTO listas_atividades(nome) VALUES (?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean updateAtividade(int id, String nome) {
        sql = "UPDATE listas_atividades SET nome = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public List<Atividade> findAll() {
        List<Atividade> atividades = new ArrayList<>();
        sql = "SELECT * FROM listas_atividades";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Atividade atv = new Atividade();
                atv.setId(rs.getInt("id"));
                atv.setNome(rs.getString("nome"));
                atividades.add(atv);
            }
            if (atividades.isEmpty()) { //caso o banco de dados tenha sido resetado e ainda não tenha as atividades
                criarAtividades();
                sql = "SELECT * FROM listas_atividades";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Atividade atv = new Atividade();
                    atv.setId(rs.getInt("id"));
                    atv.setNome(rs.getString("nome"));
                    atividades.add(atv);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return atividades;
    }

    private void criarAtividades() {
        sql = "INSERT INTO listas_atividades(id,nome) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            for (int x = 0; x < 4; x++) {
                stmt.setInt(1, x + 1);
                stmt.setString(2, Integer.toString(x + 1));
                stmt.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
