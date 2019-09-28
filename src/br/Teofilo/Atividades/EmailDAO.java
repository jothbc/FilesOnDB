/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Teofilo.Atividades;

import JDBC.ConnectionFactoryMySQL;
import br.Teofilo.Bean.GerarLogErro;
import funcoes.CDate;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.Normalizer;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JTextArea;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author User
 */
public class EmailDAO {

    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public EmailDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addRemetente(String email, String senha) {
        sql = "INSERT INTO email_from(email,senha) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    /*
        por enquanto só vai ter um remetente
     */
    public Email getRemetente() {
        sql = "SELECT * FROM email_from";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.first();
            Email e = new Email();
            e.setRemetente(rs.getString("email"));
            e.setSenha(rs.getString("senha"));
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return null;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public List<Email> getDestinatarios(int ID_CARTAO) {
        List<Email> emails = new ArrayList<>();
        sql = "SELECT * FROM email_for WHERE ID_CARTAO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Email e = new Email();
                e.setId(rs.getInt("id"));
                e.setID_CARTAO(ID_CARTAO);
                e.setDestinatario(rs.getString("destinatario"));
                emails.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
        return emails;
    }

    public boolean addDestinatario(int ID_CARTAO, String destinatario) {
        sql = "INSERT INTO email_for(ID_CARTAO,destinatario) VALUES (?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            stmt.setString(2, destinatario);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removerDestinatario(int id) {
        sql = "DELETE FROM email_for WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public boolean removerDestinatariosDoCartao(int ID_CARTAO) {
        sql = "DELETE FROM email_for WHERE ID_CARTAO = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID_CARTAO);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public void enviarLembretes() {
        if (lembretesNaoEnviados()) {
            enviarEmails();
            marcarComoEnviado();
        }
    }

    private boolean lembretesNaoEnviados() {
        con = ConnectionFactoryMySQL.getConnection();
        sql = "SELECT * FROM controle_email WHERE data = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            rs = stmt.executeQuery();
            return !rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    private void marcarComoEnviado() {
        con = ConnectionFactoryMySQL.getConnection();
        sql = "INSERT INTO controle_email (data) VALUES (?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, CDate.DataPTBRtoDataMySQL(CDate.DataPTBRAtual()));
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    private void enviarEmails() {
        List<Cartao> cartoes = new CartaoDAO().findAll();
        for (Cartao c : cartoes) {
            /*
                verifica se o cartao tem prazo de entrega, e também verifica se o cartao
                nao pertence a lista de concluídos.
             */
            if (c.getEntrega() != null && c.getID_LISTA_ATIVIDADES() != 4) {
                /*
                    essa string representa o inicio dos avisos por email,
                    se a entrega for pra dia 05/01/19 entao a partir do 02/01/2019 sera enviado emails com aviso
                 */
                String dataInicial = CDate.SomarData(-3, c.getEntrega());
                /*
                System.out.println("Cartao: " + c.getTitulo() + " data de entrega: " + c.getEntrega() + " data inicial do envio dos emails: " + dataInicial);
                 */
                try {
                    /*
                        se hoje for maior ou igual a data inicial de envios de emails então deve enviar o aviso
                     */
                    if (new Date().compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial)) >= 0) {
                        /*
                            obtem uma lista contendo todos os destinatários cadastrados para receber aviso
                            sobre esse cartao atual.
                         */
                        List<Email> emailsDesseCartao = new EmailDAO().getDestinatarios(c.getId());
                        // se o cartao tiver emails cadastrados para enviar a info
                        if (!emailsDesseCartao.isEmpty()) {
                            String msg = "Lembrete:\n\nA Atividade com titulo de ''" + c.getTitulo() + "'' está com data de entrega para " + c.getEntrega();
                            enviarEmail(emailsDesseCartao, msg, "Lembrete de entrega");
                        }
                    }
                } catch (ParseException ex) {
                    GerarLogErro.gerar(ex.getMessage());
                }
            }
        }
    }

    public Email obterRemetente() {
        con = ConnectionFactoryMySQL.getConnection();
        sql = "SELECT * FROM email_from";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.first();
            Email e = new Email();
            e.setId(rs.getInt("id"));
            e.setRemetente(rs.getString("email"));
            e.setSenha(rs.getString("senha"));
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            GerarLogErro.gerar(ex.getMessage());
            return null;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt, rs);
        }
    }

    public void enviarEmail(List<Email> destinatarios, String msg, String titulo) {
        Email remetente = obterRemetente();

        final String username = remetente.getRemetente();
        final String password = remetente.getSenha();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String destinatariosString = "";
            for (int x = 0; x < destinatarios.size(); x++) {
                if (x + 1 == destinatarios.size()) {
                    destinatariosString += destinatarios.get(x).getDestinatario();
                } else {
                    destinatariosString += destinatarios.get(x).getDestinatario() + ",";
                }
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente.getRemetente()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatariosString)
            );
            message.setSubject(titulo);
            message.setText(msg
                    + "\n\n Por favor, não marcar esse email como spam!"
                    + "\n\n Email enviado automaticamente, não responder.");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            GerarLogErro.gerar(e.getMessage());
        }
    }

    public static boolean enviarEmailAnexo(Email remetente,List<File> files, String destinatario, String titulo, String mensagem) {
//        Email remetente = new Email();
//        remetente.setRemetente("jothbc@gmail.com");
//        remetente.setSenha("sbjzgrsfcsgldsnm");
        //salva as propriedados do servidor de emails
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new DefaultAuthenticator(remetente.getRemetente(), remetente.getSenha()));

        MimeMessage msg = new MimeMessage(session);

        try {
            //Quem esta enviando
            msg.setFrom(new InternetAddress(remetente.getRemetente()));
            //Destinatario
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            //Data de envio
            msg.setSentDate(new Date());
            //titulo email
            msg.setSubject(titulo);

            Multipart mp = new MimeMultipart();
            //mensagem

            MimeBodyPart paragrafo = new MimeBodyPart();
            paragrafo.setContent(mensagem, "text/html");
            mp.addBodyPart(paragrafo);
            //anexos
            for (File file : files) {
                MimeBodyPart mbp = new MimeBodyPart();
                DataSource fds = new FileDataSource(file);
                mbp.setDisposition(Part.ATTACHMENT);
                mbp.setDataHandler(new DataHandler(fds));
                mbp.setFileName(deAccent(fds.getName()));
                mp.addBodyPart(mbp);
            }

            msg.setContent(mp);

            //envia a mensagem
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            System.err.println(e);
            GerarLogErro.gerar(e.getMessage());
            return false;
        }
    }

    public static final String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
