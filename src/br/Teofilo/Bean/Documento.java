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
public class Documento extends Arquivo {

    private String status;
    private int ID_TIPO;
    private int ID_PROCESSO;

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

}
