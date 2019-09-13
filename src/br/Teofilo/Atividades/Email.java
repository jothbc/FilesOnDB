/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

/**
 *
 * @author User
 */
public class Email {

    private int id;
    private int ID_CARTAO;
    private String remetente;
    private String senha;
    private String destinatario;

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
     * @return the ID_CARTAO
     */
    public int getID_CARTAO() {
        return ID_CARTAO;
    }

    /**
     * @param ID_CARTAO the ID_CARTAO to set
     */
    public void setID_CARTAO(int ID_CARTAO) {
        this.ID_CARTAO = ID_CARTAO;
    }

    /**
     * @return the remetente
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * @param remetente the remetente to set
     */
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String toString() {
        return this.destinatario;
    }
}
