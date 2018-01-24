package kg.goent.tools;

import kg.goent.models.User;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private Properties props = new Properties();
    private Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                final String username = "goentrservice@gmail.com";
                final String password = "serviceinfo";
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    public Mail(){
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public int sendActivationMail(User user,String url){
        String mailTo = user.getEmail();
        String mailSubject = "Accout activation";
        String mailText = "Please activate your account by entering following activation key " + user.getActivationKey();
        String link = "http://localhost:8080/hibernate/activate.xhtml?activate="+url;
        mailText += ".\n\nOr follow given link bellow "+link+"\nto activate your account.";

        return sendMsg(mailTo, mailSubject, mailText) ? 1:0;
    }

    public void sendTestMail(String receiver){
        String mailSubject = "Test mail.";

        String text = "This is test mail. If u've received this mail, it means that our mailing service is working properly.";

        sendMsg(receiver,mailSubject,text);
    }

    private boolean sendMsg(String mailTo,String mailSubject, String mailText){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("goentrservice@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject(mailSubject);
            message.setText(mailText);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}