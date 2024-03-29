/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Conta;

import br.Teofilo.Cliente.SeletorDeClienteJD;
import br.Teofilo.Bean.Cliente;
import br.Teofilo.Bean.Conta;
import br.Teofilo.Bean.ContaSub;
import br.Teofilo.DAO.ClienteDAO;
import br.Teofilo.DAO.ContaDAO;
import funcoes.Conv;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author User
 */
public class ContasClienteJF extends javax.swing.JFrame {

    private static Point point = new Point();
    Cliente cliente = null;
    DefaultTableModel tb;

    /**
     * Creates new form ContasClienteJF
     */
    public ContasClienteJF() {
        initComponents();
        jTable1.setDefaultRenderer(Object.class, new CellRenderConta());
        tb = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<>(tb));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        ID_CLIENTEtxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Clientelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        totalEmAbertotxt = new javax.swing.JTextField();
        pagobox = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/logo-2-teofilo-rocha-advocacia-balneario-camboriu-sc-advogado.png"))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_cancel_18px.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ID_CLIENTEtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ID_CLIENTEtxtKeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/zoom-2.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Clientelbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Valor", "Valor Já Pago", "Emissão", "Vencimento", "Quitação", "Parcelado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(20);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(70);
        }
        if (jTable1.getColumnModel().getColumnCount()>0){
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_control_point_24px.png"))); // NOI18N
        jButton2.setText("Nova Conta");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Total em Aberto");

        totalEmAbertotxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalEmAbertotxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pagobox.setBackground(new java.awt.Color(255, 255, 255));
        pagobox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pagobox.setText("Exibir Pagos");
        pagobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoboxActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_equalizer_24px.png"))); // NOI18N
        jButton4.setText("Relatório");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(pagobox)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ID_CLIENTEtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(Clientelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(totalEmAbertotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(ID_CLIENTEtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Clientelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(totalEmAbertotxt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(pagobox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        Point p = this.getLocation();
        this.setLocation(p.x + evt.getX() - point.x, p.y + evt.getY() - point.y);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        point.x = evt.getX();
        point.y = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        novaConta();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            if (jTable1.getSelectedRow() >= 0) {
                if ("Sim".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 7))) {
                    verParcelas();
                } else {
                    baixarSelecionado();
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void ID_CLIENTEtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ID_CLIENTEtxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            carregarCliente();
        }
    }//GEN-LAST:event_ID_CLIENTEtxtKeyPressed

    private void pagoboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoboxActionPerformed
        if (cliente != null) {
            exibirPagos();
        }
    }//GEN-LAST:event_pagoboxActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        relatorio();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContasClienteJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContasClienteJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContasClienteJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContasClienteJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContasClienteJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Clientelbl;
    private javax.swing.JTextField ID_CLIENTEtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox pagobox;
    private javax.swing.JTextField totalEmAbertotxt;
    // End of variables declaration//GEN-END:variables

    private void buscarCliente() {
        SeletorDeClienteJD jd = new SeletorDeClienteJD(null, true);
        jd.setVisible(true);
        if (jd.getCliente() != null) {
            cliente = jd.getCliente();
            ID_CLIENTEtxt.setText(Integer.toString(cliente.getId()));
            Clientelbl.setText(cliente.getNome());
            carregarContasDoCliente();
        }
    }

    private void novaConta() {
        if (cliente != null) {
            NovaContaJD jd = new NovaContaJD(null, true, cliente);
            jd.setVisible(true);
            carregarContasDoCliente();
        }
    }

    private void carregarContasDoCliente() {
        tb.setRowCount(0);
        //id, desc, valor,valorPago,emissao,vencimento,quitacao, parcelado
        List<Conta> contas = new ContaDAO().getContas_ID_CLIENTE(cliente.getId());
        if (!contas.isEmpty()) {
            for (Conta c : contas) {
                if (!c.isParcelado()) {
                    Object[] dado = {c.getId(), c.getDescricao(), c.getValor(), c.getValor_ja_pago(), c.getEmissao(), c.getVencimento(), c.getData_pagamento_final(), "Não"};
                    if (pagobox.isSelected()) {
                        tb.addRow(dado);
                    } else if (c.getData_pagamento_final() == null) {
                        tb.addRow(dado);
                    }
                } else {
                    double pago = 0;
                    for (ContaSub s : c.getConta_sub()) {
                        if (s.getData_pago() != null) {
                            pago += s.getValor();
                        }
                    }
                    Object[] dado = {c.getId(), c.getDescricao(), c.getValor(), pago, c.getEmissao(), c.getVencimento(), c.getData_pagamento_final(), "Sim"};
                    if (pagobox.isSelected()) {
                        tb.addRow(dado);
                    } else if (c.getData_pagamento_final() == null) {
                        tb.addRow(dado);
                    }
                }
            }
        }
        emAberto();
    }

    private void baixarSelecionado() {
        double total = (double) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        double pago = (double) jTable1.getValueAt(jTable1.getSelectedRow(), 3);
        double restante = total - pago;
        String valorEntrada = JOptionPane.showInputDialog("Informe o valor a ser debitado da conta: ", Conv.validarValue(restante));
        try {
            if (valorEntrada != null) {
                double entrada = Double.parseDouble(valorEntrada.replaceAll(",", "\\."));
                if (entrada > restante) {
                    JOptionPane.showConfirmDialog(null, "Foi informado um valor maior do que o devido.", "Valor superior", JOptionPane.INFORMATION_MESSAGE);
                }
                int id = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                if (!new ContaDAO().debitarValor(entrada, id)) {
                    JOptionPane.showMessageDialog(null, "Erro ao tentar dar baixa do valor no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    carregarContasDoCliente();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exibirPagos() {
        carregarContasDoCliente();
    }

    private void verParcelas() {
        int idConta = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        int idCliente = cliente.getId();
        List<Conta> contas = new ContaDAO().getContas_ID_CLIENTE(idCliente);
        for (Conta c : contas) {
            if (c.getId() == idConta) {
                if (!c.getConta_sub().isEmpty()) {
                    ParcelasJD jd = new ParcelasJD(null, true, c);
                    jd.setVisible(true);
                    carregarContasDoCliente();
                }
            }
        }
    }

    private void carregarCliente() {
        try {
            cliente = new ClienteDAO().getClinte(Integer.parseInt(ID_CLIENTEtxt.getText()));
            if (cliente == null) {
                tb.setRowCount(0);
                Clientelbl.setText("");
                JOptionPane.showMessageDialog(null, "Cliente inexistente.");
                return;
            }
            ID_CLIENTEtxt.setText(Integer.toString(cliente.getId()));
            Clientelbl.setText(cliente.getNome());
            carregarContasDoCliente();
        } catch (NumberFormatException e) {
            cliente = null;
            tb.setRowCount(0);
            Clientelbl.setText("");
            JOptionPane.showMessageDialog(null, "Cliente inexistente.");
        }
    }

    private void emAberto() {
        double aberto = 0;
        for (int x = 0; x < jTable1.getRowCount(); x++) {
            double valor, pago;
            valor = (double) jTable1.getValueAt(x, 2);
            pago = (double) jTable1.getValueAt(x, 3);
            if (pago < valor) {
                aberto += valor - pago;
            }
        }
        totalEmAbertotxt.setText(Conv.validarValue(aberto));
    }

    private void relatorio() {
        RelatorioContaJD jd = new RelatorioContaJD(null, true);
        jd.setVisible(true);
    }

}
