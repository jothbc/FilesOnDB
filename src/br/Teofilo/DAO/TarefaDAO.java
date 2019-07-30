/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import br.Teofilo.Bean.Tarefa;
import funcoes.CDate;
import java.awt.Color;
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
public class TarefaDAO {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public TarefaDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }
    
    public boolean addTarefa(Tarefa t){
        sql = "INSERT INTO tarefas (inicio,fim,anotacao,vinculado,nome_cliente,processo,marcador,concluido,titulo) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(t.getInicio()));
            stmt.setString(2, CDate.DataPTBRtoDataMySQL(t.getFim()));
            stmt.setString(3, t.getAnotacoes());
            stmt.setBoolean(4, t.isVinculado());
            stmt.setString(5, null);
            stmt.setString(6, null);
            if(t.isVinculado()){
                if (t.getClienteNome()!=null){
                    stmt.setString(5, t.getClienteNome());
                }else{
                    stmt.setString(6, t.getProcesso());
                }
            }
            stmt.setString(7, Integer.toString(t.getMarcador().getRGB()));
            stmt.setBoolean(8, t.isConcluido());
            stmt.setString(9, t.getTitulo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public List<Tarefa> findAllNoConcluidas(){
        List<Tarefa> tarefas = new ArrayList<>();
        sql = "SELECT * FROM tarefas WHERE concluido = false"; //ver se é = ou is
        try {
            stmt=con.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id"));
                t.setInicio(CDate.DataMySQLtoDataStringPT(rs.getString("inicio")));
                t.setFim(CDate.DataMySQLtoDataStringPT(rs.getString("fim")));
                t.setAnotacoes(rs.getString("anotacao"));
                t.setVinculado(rs.getBoolean("vinculado"));
                t.setClienteNome(rs.getString("nome_cliente"));
                t.setProcesso(rs.getString("processo"));
                t.setMarcador(new Color(Integer.parseInt(rs.getString("marcador"))));
                t.setTitulo(rs.getString("titulo"));
                tarefas.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return tarefas;
    }
}
