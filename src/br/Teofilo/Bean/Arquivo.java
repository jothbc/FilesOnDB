/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

/**
 *
 * @author Jonathan
 */
public class Arquivo {

    private int id;
    private String nome;
    private int ID_CLIENTE;
    private String alteracao;
    private boolean crip;
    private byte[] crip2;
    private double tamanho;

    public Arquivo() {
    }

    public Arquivo(int id, String nome, int ID_CLIENTE, String alteracao, boolean crip, byte[] crip2, double tamanho) {
        this.id = id;
        this.nome = nome;
        this.ID_CLIENTE = ID_CLIENTE;
        this.alteracao = alteracao;
        this.crip = crip;
        this.crip2 = crip2;
        this.tamanho = tamanho;
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

    /**
     * @return the crip
     */
    public boolean isCrip() {
        return crip;
    }

    /**
     * @param crip the crip to set
     */
    public void setCrip(boolean crip) {
        this.crip = crip;
    }

    /**
     * @return the crip2
     */
    public byte[] getCrip2() {
        return crip2;
    }

    /**
     * @param crip2 the crip2 to set
     */
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

    public String toString() {
        return this.nome;
    }
}
