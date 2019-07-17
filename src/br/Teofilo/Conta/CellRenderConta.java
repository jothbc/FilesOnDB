/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Conta;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author User
 */
public class CellRenderConta extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(tabela, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        try {
            String pago = (String) tabela.getValueAt(row, 7);
            if ("Sim".equals(pago)) {
                setBackground(Color.WHITE);
                setForeground(Color.BLUE);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
        } catch (Exception e) {
            System.out.println(e);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        if (tabela.getValueAt(row, 6) != null) {
            setBackground(Color.YELLOW);
            setForeground(Color.RED);
        }
        return this;
    }
}
