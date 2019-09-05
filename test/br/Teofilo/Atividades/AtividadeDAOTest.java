/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class AtividadeDAOTest {

    public AtividadeDAOTest() {
    }

    @Test
    public void testSomeMethod() {
        new AtividadeDAO().addAtividade("Em Andamento");
        new AtividadeDAO().addAtividade("A Seguir");
        new AtividadeDAO().addAtividade("Em Espera");
        new AtividadeDAO().addAtividade("Concluídos");
    }

}
