/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import br.Teofilo.Utilidades.ColorJD;
import funcoes.CDate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author User
 */
public class CartaoJD extends javax.swing.JDialog {

    int ID_CARD;
    List<JCheckBox> checks;
    public String data_entrega_temp;
    JButton botao;

    /**
     * Creates new form CartaoJD
     */
    public CartaoJD(java.awt.Frame parent, boolean modal, JButton botao) {
        super(parent, modal);
        initComponents();
        checks = new ArrayList<>();
        this.botao = botao;
        init();
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
        jButton1 = new javax.swing.JButton();
        titulotxt = new javax.swing.JTextField();
        marcadorBtn = new javax.swing.JButton();
        jspcheck = new javax.swing.JScrollPane();
        jpcheck = new javax.swing.JPanel();
        checkListBtn = new javax.swing.JButton();
        progressCheck = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        dataEntregatxt = new javax.swing.JFormattedTextField();
        grupoCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_cancel_18px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        titulotxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titulotxtActionPerformed(evt);
            }
        });
        titulotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titulotxtKeyPressed(evt);
            }
        });

        marcadorBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        marcadorBtn.setText("   Marcador");
        marcadorBtn.setBorder(null);
        marcadorBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        marcadorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcadorBtnActionPerformed(evt);
            }
        });

        jspcheck.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jpcheck.setBackground(new java.awt.Color(255, 255, 255));

        checkListBtn.setBackground(new java.awt.Color(255, 255, 255));
        checkListBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        checkListBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_check_box_24px.png"))); // NOI18N
        checkListBtn.setText("+CheckList");
        checkListBtn.setBorder(null);
        checkListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkListBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpcheckLayout = new javax.swing.GroupLayout(jpcheck);
        jpcheck.setLayout(jpcheckLayout);
        jpcheckLayout.setHorizontalGroup(
            jpcheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpcheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
        );
        jpcheckLayout.setVerticalGroup(
            jpcheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpcheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkListBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jspcheck.setViewportView(jpcheck);

        progressCheck.setStringPainted(true);

        jLabel1.setText("Data de entrega");

        try {
            dataEntregatxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataEntregatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataEntregatxtActionPerformed(evt);
            }
        });

        grupoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(titulotxt)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataEntregatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grupoCB, 0, 273, Short.MAX_VALUE))
                            .addComponent(jspcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(marcadorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(progressCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titulotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(marcadorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dataEntregatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 297, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void marcadorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcadorBtnActionPerformed
        changeColorMarcador();
    }//GEN-LAST:event_marcadorBtnActionPerformed

    private void checkListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkListBtnActionPerformed
        addCheck();
    }//GEN-LAST:event_checkListBtnActionPerformed

    private void titulotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titulotxtActionPerformed
        botao.setName(titulotxt.getText());
        if (data_entrega_temp != null)
            botao.setText("<html><center>" + botao.getName() + "<br>" + data_entrega_temp + "</center></html>");
        else
            botao.setText("<html><center>" + botao.getName() + "</center></html>");
    }//GEN-LAST:event_titulotxtActionPerformed

    private void titulotxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titulotxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //titulotxt perde o foco pra qualquer outra coisa, pra poder setar o titulo também
            // no botao la fora
        }
    }//GEN-LAST:event_titulotxtKeyPressed

    private void grupoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoCBActionPerformed
        //acao quando mudar de grupo
        System.out.println("Mudou para o grupo: " + grupoCB.getSelectedItem());
    }//GEN-LAST:event_grupoCBActionPerformed

    private void dataEntregatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataEntregatxtActionPerformed
        System.out.println("Nova data de entrega: " + dataEntregatxt.getText());
        data_entrega_temp = dataEntregatxt.getText();
        if (data_entrega_temp != null)
            botao.setText("<html><center>" + botao.getName() + "<br>" + data_entrega_temp + "</center></html>");
        else
            botao.setText("<html><center>" + botao.getName() + "</center></html>");
    }//GEN-LAST:event_dataEntregatxtActionPerformed

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
            java.util.logging.Logger.getLogger(CartaoJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CartaoJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CartaoJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CartaoJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CartaoJD dialog = new CartaoJD(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkListBtn;
    private javax.swing.JFormattedTextField dataEntregatxt;
    private javax.swing.JComboBox<String> grupoCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpcheck;
    private javax.swing.JScrollPane jspcheck;
    private javax.swing.JButton marcadorBtn;
    private javax.swing.JProgressBar progressCheck;
    private javax.swing.JTextField titulotxt;
    // End of variables declaration//GEN-END:variables

    private void changeColorMarcador() {
        ColorJD changeColor = new ColorJD(null, true);
        changeColor.setVisible(true);
        if (changeColor.getColor() != null) {
            marcadorBtn.setBackground(changeColor.getColor());
            botao.setBackground(changeColor.getColor());
        }
    }

    private void addCheck() {
        String tituloCheck = JOptionPane.showInputDialog(null, "Descrição");
        if (tituloCheck != null) {
            if (!tituloCheck.equals("")) {
                JCheckBox ck = new JCheckBox();
                ck.setText(tituloCheck);
                ck.setSelected(false);
                ck.addActionListener((java.awt.event.ActionEvent evt) -> {
                    if (ck.isSelected()) {
                        //atualizar no db que esse check esta selecionado
                    } else {
                        //atualizar no db que esse check esta desselecionado
                    }
                    atualizarChecks();
                });
                checks.add(ck);
                atualizarChecks();
            }
        }
    }

    private void atualizarChecks() {
        jpcheck.removeAll();
        checkListBtn.setLocation(0, 0);
        jpcheck.add(checkListBtn);
//        checks = new CHECKSDAO().GETCHECKSOFCARD(ID DESSE CARTAO);
        progressCheck.setMinimum(0);
        progressCheck.setMaximum(checks.size());
        progressCheck.setValue(0);
        progressCheck.setVisible(!checks.isEmpty());
        for (JCheckBox c : checks) {
            c.setLocation(0, jpcheck.getComponent(jpcheck.getComponentCount() - 1).getLocation().y + 25);
            c.setSize(jpcheck.getSize().width, 20);
            c.setFont(new java.awt.Font("Tahoma", 1, 12));
            jpcheck.add(c);
            Dimension d = jpcheck.getSize();
            d.height = jpcheck.getComponent(jpcheck.getComponentCount() - 1).getLocation().y + 25;
            jpcheck.setPreferredSize(d);
            if (c.isSelected()) {
                progressCheck.setValue(progressCheck.getValue() + 1);
            }
        }
        jspcheck.setViewportView(jpcheck);
    }

    private void init() {
        //carregar do db
        progressCheck.setVisible(!checks.isEmpty()); //temporario
        ID_CARD = 1; //temporario
        dataEntregatxt.setText(CDate.DataPTBRAtual());//temporario
        List<String> grupos = new ArrayList<>(); //fazer busca dos nomes dos grupos no db
        grupos.add("Em Andamento"); // temporario
        grupos.add("A Seguir");     //
        grupos.add("Em Espera");    //
        grupos.add("Concluídos");   //
        for (String s : grupos) {
            grupoCB.addItem(s);
        }

        titulotxt.setText(botao.getName());
        marcadorBtn.setBackground(botao.getBackground());
    }
}
