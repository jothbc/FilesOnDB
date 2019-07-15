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
public class Conta {
    private int id;
    private int ID_CLIENTE;
    private String descricao;
    private double valor;
    private String emissao;
    private double valor_ja_pago;
    private String data_pagamento_final;
    private boolean ativo;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the valor_ja_pago
     */
    public double getValor_ja_pago() {
        return valor_ja_pago;
    }

    /**
     * @param valor_ja_pago the valor_ja_pago to set
     */
    public void setValor_ja_pago(double valor_ja_pago) {
        this.valor_ja_pago = valor_ja_pago;
    }

    /**
     * @return the data_pagamento_final
     */
    public String getData_pagamento_final() {
        return data_pagamento_final;
    }

    /**
     * @param data_pagamento_final the data_pagamento_final to set
     */
    public void setData_pagamento_final(String data_pagamento_final) {
        this.data_pagamento_final = data_pagamento_final;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
}
