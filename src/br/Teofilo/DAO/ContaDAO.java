/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Conta;
import br.Teofilo.Bean.ContaSub;
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
public class ContaDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ContaDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addConta(Conta c) {
        sql = "INSERT INTO conta (ID_CLIENTE,descricao,valor,emissao,vencimento,ativo,parcelado,cartao) VALUES (?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getID_CLIENTE());
            stmt.setString(2, c.getDescricao());
            stmt.setDouble(3, c.getValor());
            stmt.setString(4, CDate.DataPTBRtoDataMySQL(c.getEmissao()));
            stmt.setString(5, CDate.DataPTBRtoDataMySQL(c.getVencimento()));
            stmt.setBoolean(6, true);
            stmt.setBoolean(7, c.isParcelado());
            stmt.setBoolean(8, c.isCartao());
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
            GerarLogErro.gerar(ex.getMessage());
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
                c.setCartao(rs.getBoolean("cartao"));
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
            GerarLogErro.gerar(ex.getMessage());
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
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return c;
    }

    public boolean pagarParcela(int id) {
        //variaveis para logpago
        String hoje = CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual());
        double valor = 0;
        int ID_CLIENTE =0;
        String nome_cliente;
        String descricao = null;
        //fim variaveis
        sql = "UPDATE conta_sub SET data_pago = ? WHERE id = ?";
        try {
            //seta a conta passada por "id" como pago no dia atual
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            //busca se todas as parcelas ja foram pagas
            sql = "SELECT * FROM conta_sub WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            valor = rs.getDouble("valor");//valor para fazer o logpago
            int id_conta = rs.getInt("ID_CONTA");
            sql = "SELECT * FROM conta_sub WHERE ID_CONTA = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_conta);
            rs = stmt.executeQuery();
            boolean todos = true;
            while (rs.next()) {
                if (rs.getString("data_pago") == null) {
                    todos = false;
                }
            }
            //caso todas as parcelas ja tenham sido pagas faz a verificação pra definir o dia final desse pagamento
            if (todos) {
                sql = "SELECT * FROM conta WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id_conta);
                rs = stmt.executeQuery();
                rs.first();
                ID_CLIENTE = rs.getInt("ID_CLIENTE");
                descricao = rs.getString("descricao");
                if (rs.getString("data_pagamento_final") == null) {
                    sql = "UPDATE conta SET data_pagamento_final = ? WHERE id = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
                    stmt.setInt(2, id_conta);
                    stmt.executeUpdate();
                }
            }
            sql = "SELECT nome FROM clientes WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CLIENTE);
            rs.first();
            nome_cliente =rs.getString("nome");
            sql = "INSERT INTO logpago (ID_CLIENTE,nome_cliente,descricao,valor_pago,data_pago) VALUES (?,?,?,?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, ID_CLIENTE);
            stmt.setString(2, nome_cliente);
            stmt.setString(3, descricao);
            stmt.setDouble(4, valor);
            stmt.setString(5, hoje);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public boolean debitarValor(double valor, int id) {
        //variaveis para logpago
        String loghoje = CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual());
        double logvalor = valor;
        int logID_CLIENTE;
        String lognome_cliente;
        String logdescricao;
        //fim variaveis
        sql = "UPDATE conta SET valor_ja_pago = valor_ja_pago + ? WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            sql = "SELECT * FROM conta WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            logID_CLIENTE = rs.getInt("ID_CLIENTE");
            logdescricao= rs.getString("descricao");
            if (rs.getString("data_pagamento_final") == null) {
                if (rs.getDouble("valor_ja_pago") >= rs.getDouble("valor")) {
                    sql = "UPDATE conta SET data_pagamento_final = ? WHERE id = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
                    stmt.setInt(2, id);
                    stmt.executeUpdate();
                }
            }
            sql = "SELECT nome FROM clientes WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, logID_CLIENTE);
            rs.first();
            lognome_cliente =rs.getString("nome");
            sql = "INSERT INTO logpago (ID_CLIENTE,nome_cliente,descricao,valor_pago,data_pago) VALUES (?,?,?,?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, logID_CLIENTE);
            stmt.setString(2, lognome_cliente);
            stmt.setString(3, logdescricao);
            stmt.setDouble(4, logvalor);
            stmt.setString(5, loghoje);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public boolean controleCartao() {
        sql = "SELECT * FROM controle_cartao WHERE data = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return false;
    }

    public boolean CartaoHojeConcluido() {
        sql = "INSERT INTO controle_cartao (data) VALUES (?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean baixarCartoesHoje() {
        //variaveis para logpago
        String loghoje = CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual());
        double logvalor = 0;
        int logID_CLIENTE;
        String lognome_cliente;
        String logdescricao;
        //fim variaveis
        sql = "SELECT * FROM conta WHERE cartao = ? and data_pagamento_final is null";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, true);
            rs = stmt.executeQuery();
            int[] id = new int[9999];
            int count = 0;
            while (rs.next()) { //todas as contas que possuem cartao = true e sem pagamento final
                id[count] = rs.getInt("id"); //obtem todos os ids das contas que possuem cartao
                //System.out.println("ID da conta que tem Cartao: " + id[count]);
                count++;
            }
            for (int x = 0; x < count; x++) {
                //pega as parcelas que ainda nao foram pagas do dia atual para menos
                sql = "SELECT * FROM conta_sub WHERE ID_CONTA = ? and data_pago is null and vencimento <= now()";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id[x]);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    //seta elas como pagas no dia do seu próprio vencimento.
                    sql = "UPDATE conta_sub SET data_pago = vencimento WHERE id = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("id"));
                    stmt.executeUpdate();
                    //captura valor dessa parcela paga
                    int id_ = rs.getInt("id");
                    sql = "SELECT * FROM conta_sub WHERE id = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, id_);
                    double valor;
                    try (ResultSet rs2 = stmt.executeQuery()) {
                        rs2.first();
                        valor = rs2.getDouble("valor");
                        sql = "UPDATE conta SET valor_ja_pago = valor_ja_pago + ? WHERE id = ?";
                        stmt = con.prepareStatement(sql);
                        stmt.setDouble(1, valor);
                        stmt.setInt(2, id[x]);
                        stmt.executeUpdate();
                    }
                }
            }
            //repetidor para verificar se pagou a ultima parcela
            for (int x = 0; x < count; x++) {
                sql = "SELECT * FROM conta_sub WHERE ID_CONTA = ? and data_pago is null";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id[x]);
                rs = stmt.executeQuery();
                if (!rs.first()) {
                    //System.out.println("sem contas sub, entao setar pagamento final");
                    sql = "UPDATE conta SET data_pagamento_final = ? WHERE id =?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
                    stmt.setInt(2, id[x]);
                    stmt.executeUpdate();
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }
}
