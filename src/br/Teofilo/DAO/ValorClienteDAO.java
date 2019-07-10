/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.Cliente;
import br.Teofilo.Bean.Parcela;
import br.Teofilo.Bean.ValorCliente;
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
public class ValorClienteDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ValorClienteDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addConta(ValorCliente vc) {
        sql = "INSERT INTO valorcliente (ID_CLIENTE,total,vencimento,parcelas,emissao) VALUES (?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vc.getID_CLIENTE());
            stmt.setDouble(2, vc.getTotal());
            stmt.setString(3, CDate.DataPTBRtoDataMySQL(vc.getData_vencimento()));
            stmt.setInt(4, vc.getN_parcelas());
            stmt.setString(5, CDate.DataPTBRtoDataMySQL(vc.getEmissao()));
            stmt.execute();
            if (vc.getN_parcelas() > 1) {
                sql = "SELECT LAST_INSERT_ID() INTO @id";
                stmt = con.prepareStatement(sql);
                stmt.execute();
                sql = "INSERT INTO parcela (ID_VALORCLIENTE,valor,vencimento,n) VALUES (@id,?,?,?)";
                stmt = con.prepareStatement(sql);
                int cont = 1;
                for (Parcela p : vc.getParcelas()) {
                    stmt.setDouble(1, p.getValor());
                    stmt.setString(2, CDate.DataPTBRtoDataMySQL(p.getData_vencimento()));
                    stmt.setInt(3, cont);
                    stmt.execute();
                    cont++;
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ValorClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public List<ValorCliente> getValoresDoCLiente_EmAberto(Cliente c){
        List<ValorCliente> valores= new ArrayList<>();
        sql = "SELECT * FROM valorcliente WHERE ID_CLIENTE = ? AND data_pago is null";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            rs = stmt.executeQuery();
            while(rs.next()){
                ValorCliente v = new ValorCliente();
                v.setId(rs.getInt("id"));
                v.setID_CLIENTE(rs.getInt("ID_CLIENTE"));
                v.setEmissao(CDate.DataMySQLtoDataStringPT(rs.getString("emissao")));
                v.setData_vencimento(CDate.DataMySQLtoDataStringPT(rs.getString("vencimento")));
                v.setTotal(rs.getDouble("total"));
                v.setN_parcelas(rs.getInt("parcelas"));
                v.setJapago(rs.getDouble("jaPago"));
                valores.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValorClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return valores;
    }
    
    public List<Parcela> getParcelasDoValorCliente(ValorCliente v){
        List<Parcela> parcelas = new ArrayList<>();
        sql = "SELECT * FROM parcela WHERE ID_VALORCLIENTE = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, v.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                Parcela p = new Parcela();
                p.setId(rs.getInt("id"));
                p.setID_VALORCLIENTE(v.getId());
                p.setN_parcela(rs.getInt("n"));
                p.setValor(rs.getDouble("valor"));
                p.setData_vencimento(CDate.DataMySQLtoDataStringPT(rs.getString("vencimento")));
                if (rs.getString("pago")!=null){
                    p.setData_pago(CDate.DataMySQLtoDataStringPT(rs.getString("pago")));
                }
                parcelas.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValorClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return parcelas;
    }
    
    public boolean baixarValorCliente(int id,double valor){
        sql = "UPDATE valorcliente SET jaPago = jaPago + ? WHERE id = ?";
        try {
            stmt= con.prepareStatement(sql);
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ValorClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
    public boolean baixarParcelaValorCliente(int id){
        sql = "UPDATE parcela SET pago = ? WHERE id = ?";
        try {
            stmt =con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ValorClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }
    
}
