/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Cliente;
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
public class ClienteDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ClienteDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean insertCliente(Cliente c) {
        sql = "INSERT INTO clientes(nome,email,telefone,telefone2,sexo,cpf,rg,nascimento,data_cadastro,endereco,n_casa,complemento,ativo)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getTelefone2());
            stmt.setString(5, c.getSexo());
            stmt.setString(6, c.getCpf());
            stmt.setString(7, c.getRg());
            stmt.setString(8, CDate.DataPTBRtoDataMySQL(c.getNascimento()));
            stmt.setString(9, CDate.DataPTBRtoDataMySQL(c.getData_cadastro()));
            stmt.setString(10, c.getEndereco());
            stmt.setString(11, c.getN_casa());
            stmt.setString(12, c.getComplemento());
            stmt.setBoolean(13, true);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }

    }

    public List<Cliente> getClintes() {
        List<Cliente> clientes = new ArrayList<>();
        sql = "SELECT * FROM clientes";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                if (rs.getString("nome") != null) {
                    c.setNome(rs.getString("nome"));
                }
                if (rs.getString("email") != null) {
                    c.setEmail(rs.getString("email"));
                }
                if (rs.getString("telefone") != null) {
                    c.setTelefone(rs.getString("telefone"));
                }
                if (rs.getString("telefone2") != null) {
                    c.setTelefone2(rs.getString("telefone2"));
                }
                if (rs.getString("cpf") != null) {
                    c.setCpf(rs.getString("cpf"));
                }
                if (rs.getString("rg") != null) {
                    c.setRg(rs.getString("rg"));
                }
                if (rs.getString("sexo") != null) {
                    c.setSexo(rs.getString("sexo"));
                }
                if (rs.getString("nascimento") != null) {
                    c.setNascimento(CDate.DataMySQLtoDataStringPT(rs.getString("nascimento")));
                }
                if (rs.getString("data_cadastro") != null) {
                    c.setData_cadastro(CDate.DataMySQLtoDataStringPT(rs.getString("data_cadastro")));
                }
                if (rs.getString("endereco") != null) {
                    c.setEndereco(rs.getString("endereco"));
                }
                if (rs.getString("n_casa") != null) {
                    c.setN_casa(rs.getString("n_casa"));
                }
                if (rs.getString("complemento") != null) {
                    c.setComplemento(rs.getString("complemento"));
                }
                c.setValor(rs.getDouble("valor"));
                c.setAtivo(rs.getBoolean("ativo"));
                clientes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return clientes;
    }

}
