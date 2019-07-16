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
public class ContaSub {
    private int CONTA_ID;
    private double valor;
    private String vencimento;
    private String data_pago;

    /**
     * @return the CONTA_ID
     */
    public int getCONTA_ID() {
        return CONTA_ID;
    }

    /**
     * @param CONTA_ID the CONTA_ID to set
     */
    public void setCONTA_ID(int CONTA_ID) {
        this.CONTA_ID = CONTA_ID;
    }

    /**
     * @return the vencimento
     */
    public String getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
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
    
}
