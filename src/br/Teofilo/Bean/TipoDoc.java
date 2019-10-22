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
public class TipoDoc {

    private int id;
    private String nome;
    private List<Documento> documentos;

    public TipoDoc() {
        documentos = new ArrayList<>();
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

    public String toString() {
        return this.nome;
    }

    /**
     * @return the documentos
     */
    public List<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void addDocumento(Documento documento) {
        this.documentos.add(documento);
    }

    public void removeDocumeto(Documento documento) {
        this.documentos.remove(documento);
    }
}
