/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Conta {
    private int id;
    private int ID_CLIENTE;
    private double valor;
    private double valor_ja_pago;
    private String descricao;
    private String emissao;
    private String data_pagamento_final;
    private String vencimento;
    private List<ContaSub> conta_sub;
    private boolean ativo;
    private boolean parcelado;
    private boolean cartao;

    public Conta() {
        conta_sub = new ArrayList<>();
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
     * @return the parcelado
     */
    public boolean isParcelado() {
        return parcelado;
    }

    /**
     * @param parcelado the parcelado to set
     */
    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    /**
     * @return the conta_sub
     */
    public List<ContaSub> getConta_sub() {
        return conta_sub;
    }

    /**
     * @param conta_sub the conta_sub to set
     */
    public void setConta_sub(List<ContaSub> conta_sub) {
        this.conta_sub = conta_sub;
    }
    
    public void addConta_sub(ContaSub c){
        this.getConta_sub().add(c);
    }

    /**
     * @return the cartao
     */
    public boolean isCartao() {
        return cartao;
    }

    /**
     * @param cartao the cartao to set
     */
    public void setCartao(boolean cartao) {
        this.cartao = cartao;
    }
    
    
}
