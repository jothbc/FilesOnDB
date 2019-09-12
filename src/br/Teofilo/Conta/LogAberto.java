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
public class LogAberto {
    private String nome;
    private String desc;
    private double valor;
    private double valor_ja_pago;
    private String emissao;
    private String vencimento;
    private String quitacao;

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
     * @return the quitacao
     */
    public String getQuitacao() {
        return quitacao;
    }

    /**
     * @param quitacao the quitacao to set
     */
    public void setQuitacao(String quitacao) {
        this.quitacao = quitacao;
    }
    
    
}
