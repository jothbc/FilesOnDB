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
public class Check {
    private int id;
    private int ID_CARTAO;
    private boolean concluido;
    private String nome;

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
     * @return the concluido
     */
    public boolean isConcluido() {
        return concluido;
    }

    /**
     * @param concluido the concluido to set
     */
    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
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
    
    public String toString(){
        return this.nome;
    }
}
