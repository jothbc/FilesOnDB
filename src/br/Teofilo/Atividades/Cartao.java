/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Cartao {

    private int id;
    private int ID_LISTA_ATIVIDADES;
    private String titulo;
    private String cor;
    private String entrega;
    private String descricao;

    private List<Comentario> comentarios;
    private List<Check> checks;

    public Cartao() {
        comentarios = new ArrayList<>();
        checks = new ArrayList<>();
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
     * @return the ID_LISTA_ATIVIDADES
     */
    public int getID_LISTA_ATIVIDADES() {
        return ID_LISTA_ATIVIDADES;
    }

    /**
     * @param ID_LISTA_ATIVIDADES the ID_LISTA_ATIVIDADES to set
     */
    public void setID_LISTA_ATIVIDADES(int ID_LISTA_ATIVIDADES) {
        this.ID_LISTA_ATIVIDADES = ID_LISTA_ATIVIDADES;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the entrega
     */
    public String getEntrega() {
        return entrega;
    }

    /**
     * @param entrega the entrega to set
     */
    public void setEntrega(String entrega) {
        this.entrega = entrega;
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
    
    public void addComentario(Comentario c){
        this.comentarios.add(c);
    }
    public void addCheck(Check c){
        this.checks.add(c);
    }

    public List<Check> getChecks() {
        return checks;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
    

}
