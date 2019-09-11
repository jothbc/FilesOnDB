/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Conta;

/**
 *
 * @author User
 */
public class LogPago {
    private int id;
    private int ID_CLIENTE;
    private String nome;
    private String desc;
    private double valor_pago;
    private String data_pago;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the valor_pago
     */
    public double getValor_pago() {
        return valor_pago;
    }

    /**
     * @param valor_pago the valor_pago to set
     */
    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
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
