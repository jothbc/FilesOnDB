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
public class Documento {

    private int id;
    private String nome;
    private int ID_CLIENTE;
    private String modificacao;
    private String status;
    private int ID_TIPO;
    private int ID_PROCESSO;
    private boolean crip;
    private byte[] crip2;

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
     * @return the modificacao
     */
    public String getModificacao() {
        return modificacao;
    }

    /**
     * @param modificacao the modificacao to set
     */
    public void setModificacao(String modificacao) {
        this.modificacao = modificacao;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the ID_TIPO
     */
    public int getID_TIPO() {
        return ID_TIPO;
    }

    /**
     * @param ID_TIPO the ID_TIPO to set
     */
    public void setID_TIPO(int ID_TIPO) {
        this.ID_TIPO = ID_TIPO;
    }

    public String toString() {
        return this.getNome();
    }

    /**
     * @return the ID_PROCESSO
     */
    public int getID_PROCESSO() {
        return ID_PROCESSO;
    }

    /**
     * @param ID_PROCESSO the ID_PROCESSO to set
     */
    public void setID_PROCESSO(int ID_PROCESSO) {
        this.ID_PROCESSO = ID_PROCESSO;
    }

    public boolean isCrip() {
        return crip;
    }

    public void setCrip(boolean crip) {
        this.crip = crip;
    }

    public void setCrip2(byte[] crip2) {
        this.crip2 = crip2;
    }

    public byte[] getCrip2() {
        return crip2;
    }

    
}
