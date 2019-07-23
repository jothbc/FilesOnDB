/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Utilidades;

import br.Teofilo.Bean.Comentario;
import br.Teofilo.Bean.User;
import br.Teofilo.DAO.ComentarioDAO;
import br.Teofilo.DAO.UserDAO;
import funcoes.CDate;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author User
 */
public class ComentarioJF extends javax.swing.JFrame {

    private final Point point = new Point();
    private final int w;
    private final int h;
    private final int hp;
    User usuario;
    Thread thread;
    int count = 0;
    boolean isRunning = true;
    StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    final Style blueStyle = sc.addStyle("BLUE", null);
    final Style blackStyle = sc.addStyle("BLACK", null);

    /**
     * Creates new form ComentarioJF
     */
    public ComentarioJF() {
        initComponents();
        w = this.getWidth();
        h = this.getHeight();
        hp = jPanel3.getHeight();
        usuario = new UserDAO().getUser();
        blueStyle.addAttribute(StyleConstants.Background, Color.WHITE);
        blueStyle.addAttribute(StyleConstants.Foreground, Color.BLUE);
        blackStyle.addAttribute(StyleConstants.Background, Color.WHITE);
        blackStyle.addAttribute(StyleConstants.Foreground, Color.BLACK);
        jTextPane1.setDocument(doc);
        iniciar();
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
        coment = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        coment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comentKeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coment)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(coment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_cancel_18px.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/Teofilo/IMG/1x/ic_more_horiz_24px.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(301, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        stop();
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        minimizarMaximizar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comentKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            inserir();
        }
    }//GEN-LAST:event_comentKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(ComentarioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComentarioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComentarioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComentarioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComentarioJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField coment;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private void minimizarMaximizar() {
        jPanel1.setVisible(!jPanel1.isVisible());
        if (jPanel1.isVisible()) {
            this.setSize(w, h);
        } else {
            this.setSize(w, hp);
        }
    }

    private void inserir() {
        String comentario = coment.getText();
        if (new ComentarioDAO().addComentario(comentario, usuario.getId())) {
//            inserirLinha(coment.getText());
            coment.setText("");
        }
    }

    private void iniciar() {
//        area.setText("");
//        comentarios = new ComentarioDAO().getAllComentarios();
//        Calendar data_hora1 = Calendar.getInstance();
//        Calendar data_hora2 = Calendar.getInstance();
//        String _usuario = null;
//        for (Comentario c : comentarios) {
//            String[] hora = c.getHora().split(":");
//            String[] dia = c.getData().split("/");
//            //primeira vez no laço de repetição
//            //então já escreve as informações de data,hora e quem publicou além claro do comentário
//            if (_usuario == null) {
//                _usuario = c.getNome();
//                data_hora1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
//                data_hora1.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
//                data_hora1.set(Calendar.SECOND, Integer.parseInt(hora[2]));
//                data_hora1.set(Calendar.YEAR, Integer.parseInt(dia[2]));
//                data_hora1.set(Calendar.MONTH, Integer.parseInt(dia[1]) - 1);
//                data_hora1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia[0]));
//                area.append(
//                        "\n"
//                        + "[" + c.getData()
//                        + " " + c.getHora()
//                        + " " + c.getNome() + "]"
//                        + "\n\t"
//                        + c.getComentario()
//                );
//            } //verifica se ainda se trata do mesmo usuario
//            //se for o mesmo usuario entra nesse else if
//            else if (_usuario.equals(c.getNome())) {
//                data_hora2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
//                data_hora2.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
//                data_hora2.set(Calendar.SECOND, Integer.parseInt(hora[2]));
//                data_hora2.set(Calendar.YEAR, Integer.parseInt(dia[2]));
//                data_hora2.set(Calendar.MONTH, Integer.parseInt(dia[1]) - 1);
//                data_hora2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia[0]));
//                if (data_hora2.getTimeInMillis() - data_hora1.getTimeInMillis() >= 60000) {//1 minuto 
//                    data_hora1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0])); //ver dia
//                    data_hora1.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
//                    data_hora1.set(Calendar.SECOND, Integer.parseInt(hora[2]));
//                    data_hora1.set(Calendar.YEAR, Integer.parseInt(dia[2]));
//                    data_hora1.set(Calendar.MONTH, Integer.parseInt(dia[1]) - 1);
//                    data_hora1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia[0]));
//                    area.append("\n"
//                            + "[" + c.getData()
//                            + " " + c.getHora()
//                            + " " + c.getNome() + "]"
//                            + "\n\t"
//                            + c.getComentario()
//                    );
//                } else {
//                    area.append(
//                            "\n\t"
//                            + c.getComentario()
//                    );
//                }
//
//            } //mudou de usuario
//            else if (!_usuario.equals(c.getNome())) {
//                _usuario = c.getNome();
//                data_hora1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
//                data_hora1.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
//                data_hora1.set(Calendar.SECOND, Integer.parseInt(hora[2]));
//                data_hora1.set(Calendar.YEAR, Integer.parseInt(dia[2]));
//                data_hora1.set(Calendar.MONTH, Integer.parseInt(dia[1]) - 1);
//                data_hora1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia[0]));
//                area.append("\n"
//                        + "[" + c.getData()
//                        + " " + c.getHora()
//                        + " " + c.getNome() + "]"
//                        + "\n\t"
//                        + c.getComentario()
//                );
//            }
//        }
        List<Comentario> comentarios = new ComentarioDAO().getAllComentarios();
        for (Comentario c : comentarios) {
            inserirLinha(c);
        }

        //esse star precisa ter no fim desse inicio para chavar a thread que verifica novos comentarios.
        start();
    }

    private void inserirLinha(Comentario c) {
        //        area.append(
//                "\n"
//                + "[" + CDate.DataPTBRAtual()
//                + " " + CDate.getHoraAtualPTBR()
//                + " " + usuario.getNome() + "]"
//                + "\n\t"
//                + comentario
//        );
        try {
            if (usuario.getId() != c.getID_USER()) { //outro pc
                doc.insertString(doc.getLength(), "[" + c.getData() + " " + c.getHora() + " " + c.getNome() + "]\n", blueStyle);
                doc.insertString(doc.getLength(), "-> "+c.getComentario()+"\n", blueStyle);
            } else { //esse pc
                doc.insertString(doc.getLength(), "[" + c.getData() + " " + c.getHora() + " " + c.getNome() + "]\n", blackStyle);
                doc.insertString(doc.getLength(), "-> "+c.getComentario()+"\n", blackStyle);
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(ComentarioJF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void inserirLinhaOther(Comentario c) {
//        area.append(
//                "\n"
//                + "[" + c.getData()
//                + " " + c.getHora()
//                + " " + c.getNome() + "]"
//                + "\n\t"
//                + c.getComentario()
//        );

    }

    private synchronized void start() {
        thread = new Thread(() -> { //essa thread pesquisa se há um novo comentario feito por outra pessoa no banco de dados
            while (isRunning) {
                int countAtual = new ComentarioDAO().getNLinhasBYOTHER(usuario.getId());
                if (countAtual != count) {
                    List<Comentario> novos = new ComentarioDAO().getComentariosByOTHER(usuario.getNome(), count);
                    for (Comentario c : novos) {
                        inserirLinhaOther(c); //aqui ele chama o metodo para inserir esse comentario vindo do db
                    }
                    count = countAtual;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ComentarioJF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    private synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ComentarioJF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
