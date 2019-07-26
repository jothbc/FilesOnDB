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
public class Processo {

    private int id;
    private int ID_CLIENTE;
    private String status;
    private String n_processo;
    private List<TipoDoc> tipos_doc;
    private String data;

    public Processo() {
        tipos_doc = new ArrayList<>();
    }

    
    /**
     * @return the tipos_doc
     */
    public List<TipoDoc> getTipos_doc() {
        return tipos_doc;
    }

    /**
     * @param tipos_doc the tipos_doc to set
     */
    public void setTipos_doc(List<TipoDoc> tipos_doc) {
        this.tipos_doc = tipos_doc;
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
     * @return the n_processo
     */
    public String getN_processo() {
        return n_processo;
    }

    /**
     * @param n_processo the n_processo to set
     */
    public void setN_processo(String n_processo) {
        this.n_processo = n_processo;
    }

    public String toString() {
        return this.n_processo;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
}
