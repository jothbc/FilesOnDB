/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.DAO;

import br.Teofilo.Bean.Parcela;
import br.Teofilo.Bean.ValorCliente;
import funcoes.CDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class ValorClienteDAOTest {
    
    public ValorClienteDAOTest() {
    }

    @Test
    public void testSomeMethod() {
        ValorCliente vc= new ValorCliente();
        vc.setData_vencimento("10/07/2019");
        vc.setID_CLIENTE(1);
        vc.setN_parcelas(5);
        vc.setTotal(100);
        for (int x=0;x<vc.getN_parcelas();x++){
            vc.addParcela(x+1, CDate.SomarData_MES(x, vc.getData_vencimento()),vc.getTotal()/vc.getN_parcelas());
        }
        new ValorClienteDAO().addConta(vc);
    }
    
}
