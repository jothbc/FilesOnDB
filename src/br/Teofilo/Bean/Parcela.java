/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

/**
 *
 * @author User
 */
public class Parcela {
    private int id;
    private int n_parcela;
    private String data_vencimento;
    private double valor;
    private String data_pago;
    private int ID_VALORCLIENTE;

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
     * @return the n_parcela
     */
    public int getN_parcela() {
        return n_parcela;
    }

    /**
     * @param n_parcela the n_parcela to set
     */
    public void setN_parcela(int n_parcela) {
        this.n_parcela = n_parcela;
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
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
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
     * @return the ID_VALORCLIENTE
     */
    public int getID_VALORCLIENTE() {
        return ID_VALORCLIENTE;
    }

    /**
     * @param ID_VALORCLIENTE the ID_VALORCLIENTE to set
     */
    public void setID_VALORCLIENTE(int ID_VALORCLIENTE) {
        this.ID_VALORCLIENTE = ID_VALORCLIENTE;
    }
}
