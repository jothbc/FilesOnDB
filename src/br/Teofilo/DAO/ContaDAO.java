/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Conta;
import br.Teofilo.Bean.ContaSub;
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
public class ContaDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ContaDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addConta(Conta c) {
        sql = "INSERT INTO conta (ID_CLIENTE,descricao,valor,emissao,vencimento,ativo,parcelado) VALUES (?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getID_CLIENTE());
            stmt.setString(2, c.getDescricao());
            stmt.setDouble(3, c.getValor());
            stmt.setString(4, CDate.DataPTBRtoDataMySQL(c.getEmissao()));
            stmt.setString(5, CDate.DataPTBRtoDataMySQL(c.getVencimento()));
            stmt.setBoolean(6, true);
            stmt.setBoolean(7, c.isParcelado());
            stmt.execute();
            if (c.isParcelado()) {
                sql = "SELECT LAST_INSERT_ID() INTO @id";
                stmt = con.prepareStatement(sql);
                stmt.execute();
                sql = "INSERT INTO conta_sub (ID_CONTA,valor,vencimento) VALUES (@id,?,?)";
                stmt = con.prepareStatement(sql);
                for (ContaSub s : c.getConta_sub()) {
                    stmt.setDouble(1, s.getValor());
                    stmt.setString(2, CDate.DataPTBRtoDataMySQL(s.getVencimento()));
                    stmt.execute();
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public List<Conta> findAllByClienteID(int id) {
        List<Conta> contas = new ArrayList<>();
        sql = "SELECT * FROM conta WHERE ID_CLIENTE = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Conta c = new Conta();
                c.setID_CLIENTE(id);
                c.setId(rs.getInt("id"));
                c.setAtivo(rs.getBoolean("ativo"));
                c.setParcelado(rs.getBoolean("parcelado"));
                c.setValor(rs.getDouble("valor"));
                c.setEmissao(CDate.DataMySQLtoDataStringPT(rs.getString("emissao")));
                c.setVencimento(CDate.DataMySQLtoDataStringPT(rs.getString("vencimento")));
                c.setDescricao(rs.getString("descricao"));
                c.setValor_ja_pago(rs.getDouble("valor_ja_pago"));
                if (rs.getString("data_pagamento_final") != null) {
                    c.setData_pagamento_final(CDate.DataMySQLtoDataStringPT(rs.getString("data_pagamento_final")));
                }
                contas.add(c);
            }
            for (Conta c : contas) {
                if (c.isParcelado()) {
                    sql = "SELECT * FROM conta_sub WHERE ID_CONTA = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, c.getId());
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        ContaSub s = new ContaSub();
                        s.setCONTA_ID(rs.getInt("ID_CONTA"));
                        s.setId(rs.getInt("id"));
                        s.setValor(rs.getDouble("valor"));
                        s.setVencimento(CDate.DataMySQLtoDataStringPT(rs.getString("vencimento")));
                        if (rs.getString("data_pago") != null) {
                            s.setData_pago(CDate.DataMySQLtoDataStringPT(rs.getString("data_pago")));
                        }
                        c.addConta_sub(s);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contas;
    }

    public Conta getContaSubByIDCONTA(int id) {
        sql = "SELECT * FROM conta_sub WHERE ID_CONTA = ?";
        Conta c = new Conta();
        c.setId(id);
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ContaSub s = new ContaSub();
                s.setCONTA_ID(id);
                s.setId(rs.getInt("id"));
                s.setValor(rs.getDouble("valor"));
                s.setVencimento(CDate.DataMySQLtoDataStringPT(rs.getString("vencimento")));
                if (rs.getString("data_pago") != null) {
                    s.setData_pago(CDate.DataMySQLtoDataStringPT(rs.getString("data_pago")));
                }
                c.addConta_sub(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return c;
    }

    public boolean pagarParcela(int id) {
        sql = "UPDATE conta_sub SET data_pago = ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
}
