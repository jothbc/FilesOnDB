/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

import funcoes.CDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ValorCliente {
    private int id;
    private int ID_CLIENTE;
    private double total;
    private int n_parcelas;
    private String data_vencimento;
    private String data_pago;
    private List<Parcela> parcelas;
    private String referente;
    private String emissao;
    private double japago;

    public ValorCliente() {
        parcelas = new ArrayList<>();
        emissao = CDate.DataPTBRAtual();
        data_vencimento = CDate.DataPTBRAtual();
        n_parcelas=1;
    }

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ID_CLIENTE
     */
    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    /**
     * @param ID_CLIENTE the ID_CLIENTE to set
     */
    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the n_parcelas
     */
    public int getN_parcelas() {
        return n_parcelas;
    }

    /**
     * @param n_parcelas the n_parcelas to set
     */
    public void setN_parcelas(int n_parcelas) {
        this.n_parcelas = n_parcelas;
    }

    /**
     * @return the data_vencimento
     */
    public String getData_vencimento() {
        return data_vencimento;
    }

    /**
     * @param data_vencimento the data_vencimento to set
     */
    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    /**
     * @return the data_pago
     */
    public String getData_pago() {
        return data_pago;
    }

    /**
     * @param data_pago the data_pago to set
     */
    public void setData_pago(String data_pago) {
        this.data_pago = data_pago;
    }

    /**
     * @return the parcelas
     */
    public List<Parcela> getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
    
    public void addParcela(int n_parcela,String vencimento,double valor){
        Parcela p = new Parcela();
        p.setN_parcela(n_parcela);
        p.setData_vencimento(vencimento);
        p.setValor(valor);
        this.parcelas.add(p);
    }

    /**
     * @return the referente
     */
    public String getReferente() {
        return referente;
    }

    /**
     * @param referente the referente to set
     */
    public void setReferente(String referente) {
        this.referente = referente;
    }

    /**
     * @return the emissao
     */
    public String getEmissao() {
        return emissao;
    }

    /**
     * @param emissao the emissao to set
     */
    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    /**
     * @return the japago
     */
    public double getJapago() {
        return japago;
    }

    /**
     * @param japago the japago to set
     */
    public void setJapago(double japago) {
        this.japago = japago;
    }
}
