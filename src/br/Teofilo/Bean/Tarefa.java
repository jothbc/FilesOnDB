/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Bean;

import java.awt.Color;

/**
 *
 * @author User
 */
public class Tarefa {
    private int id;
    private String inicio;
    private String fim;
    private boolean concluido;
    private String anotacoes;
    private boolean vinculado;
    private String clienteNome;
    private String processo;
    private Color marcador;

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
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fim
     */
    public String getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(String fim) {
        this.fim = fim;
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
     * @return the anotacoes
     */
    public String getAnotacoes() {
        return anotacoes;
    }

    /**
     * @param anotacoes the anotacoes to set
     */
    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    /**
     * @return the vinculado
     */
    public boolean isVinculado() {
        return vinculado;
    }

    /**
     * @param vinculado the vinculado to set
     */
    public void setVinculado(boolean vinculado) {
        this.vinculado = vinculado;
    }

    /**
     * @return the clienteNome
     */
    public String getClienteNome() {
        return clienteNome;
    }

    /**
     * @param clienteNome the clienteNome to set
     */
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
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

    /**
     * @return the marcador
     */
    public Color getMarcador() {
        return marcador;
    }

    /**
     * @param marcador the marcador to set
     */
    public void setMarcador(Color marcador) {
        this.marcador = marcador;
    }
}
