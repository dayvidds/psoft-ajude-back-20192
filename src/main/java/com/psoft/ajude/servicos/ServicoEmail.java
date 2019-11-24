package com.psoft.ajude.servicos;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class ServicoEmail {

    private final Session session;

    public ServicoEmail() {
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        this.session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("ajude.psoft.2019@gmail.com", "loacehpesod+");
                    }
                });
    }


    public String getCadastroEmailCorpo() {
        return "Seu cadastro em nossa plataforma foi realizado com sucesso! acesse: wwww.google.com.br";
    }

    public String getCadastroEmailSubject() {
        return "Cadastro no AJuDE";
    }

    public void mandarEmail(String email, String corpo, String assunto) {
        try {

            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress("ajude.psoft.2019@gmail.com"));

            Address[] toUser = InternetAddress.parse(email);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setText(corpo);

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }
}
