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
public class DocumentoPessoal {

    private int id;
    private String nome;
    private int ID_CLIENTE;
    private String alteracao;
    private boolean crip;
    private byte[] crip2;
    private double tamanho;

    public DocumentoPessoal() {
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

    public String toString() {
        return this.nome;
    }

    /**
     * @return the alteracao
     */
    public String getAlteracao() {
        return alteracao;
    }

    /**
     * @param alteracao the alteracao to set
     */
    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public void setCrip(boolean crip) {
        this.crip = crip;
    }

    public boolean isCrip() {
        return crip;
    }

    public byte[] getCrip2() {
        return crip2;
    }

    public void setCrip2(byte[] crip2) {
        this.crip2 = crip2;
    }

    /**
     * @return the tamanho
     */
    public double getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

}
