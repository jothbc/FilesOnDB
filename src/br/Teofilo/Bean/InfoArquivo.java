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
public class InfoArquivo {
    private int id;
    private String nome;
    private String data_modificacao;
    private String status;
    private int id_tipo;
    private String processo;

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
    
    @Override
    public String toString(){
        return this.getNome();
    }

    /**
     * @return the data_modificacao
     */
    public String getData_modificacao() {
        return data_modificacao;
    }

    /**
     * @param data_modificacao the data_modificacao to set
     */
    public void setData_modificacao(String data_modificacao) {
        this.data_modificacao = data_modificacao;
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
     * @return the id_tipo
     */
    public int getId_tipo() {
        return id_tipo;
    }

    /**
     * @param id_tipo the id_tipo to set
     */
    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    /**
     * @return the processo
     */
    public String getProcesso() {
        return processo;
    }

    /**
     * @param processo the processo to set
     */
    public void setProcesso(String processo) {
        this.processo = processo;
    }
}
