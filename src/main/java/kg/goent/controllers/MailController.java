package kg.goent.controllers;

import kg.goent.tools.Mail;
import kg.goent.tools.Tools;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by azire on 5/11/2017.
 */
@ManagedBean
@ViewScoped
public class MailController {
    private Mail mail = new Mail();

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public void sendMail(){
        mail.sendTestMail(sessionController.getUser().getEmail());
        Tools.faceMessageWarn("Message has been sent.","See your email.");
    }
}
