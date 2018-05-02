package kg.goent.controllers;

import kg.goent.facade.UserFacade;
import kg.goent.facade.UserRoleFacade;
import kg.goent.facade.UserStatusFacade;
import kg.goent.models.User;
//import kg.goent.tools.Mail;
import kg.goent.tools.Mail;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static kg.goent.tools.ViewPath.*;
/**
 * Created by timur on 13-Apr-17.
 */
@ManagedBean
@ViewScoped
public class UserController extends GetReqBean {
    private User user;
    private String activationKey;
    private String activate;

    @PostConstruct
    void init(){
        user = new User();
    }

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<User> getAllUsers(){
        return new UserFacade().findAll();
    }

    public String getActivate() {
        return activate;
    }

    public String setActivate(String activate) {
        if(activate != null && activate.length() != 0){
            User user = new UserFacade().findByEmail(Tools.decode(activate));
            if(user == null){
                Tools.faceMessageWarn(Tools.getFieldMsg("wrongActivationLink"),"");
                return "";
            }
            user.setActivationKey("");
            user.setUserStatus(new UserStatusFacade().findById(1));
            new UserFacade().updateUser(user);
            return INDEX+REDIRECT;
        }
        this.activate = activate;
        return "";
    }

    public String signIn(){
        String email=user.getEmail();
        String password=user.getPassword();

        UserFacade uf = new UserFacade();
        User tempUser = uf.findByEmailPass(email,password);

        if(tempUser == null){
            Tools.faceMessageWarn("Wrong email or password.","Please, check if data are correct.");
            return "signIn";
        }

        sessionController.setUser(tempUser);
        sessionController.setLogged(true);


//        sessionController.signIn();
//        System.out.println(sessionController.getUser());
//        System.out.println("IsLogged: "+sessionController.isLogged());
        return "index?faces-redirect=true";
    }

    public String signOut(){
        this.destroySession();
        return "/index"+REDIRECT;
    }

    public String signUp(){
        user.setRegDate(new Date());
        if(user.getUserRole() == null){
            user.setUserRole(new UserRoleFacade().findById(3));
        }
        if(user.getUserStatus() == null){
            user.setUserStatus(new UserStatusFacade().findById(4));
        }
        user.setActivationKey(Tools.generateRandomKey());
        UserFacade uf=new UserFacade();
        uf.createUser(user);
        String activationLink = Tools.encode(user.getEmail());
        Mail m = new Mail();
        m.sendActivationMail(user,activationLink);
        System.out.println("successfully registered");

        return "signin?faces-redirect=true";
    }

    public String activateByKey(){
        /*
        * method for account activation by providing activation key from web page
        * */
        if(sessionController.getUser().getActivationKey().equals(activationKey)){
            sessionController.getUser().setActivationKey("");
            sessionController.getUser().setUserStatus(new UserStatusFacade().findByStatus("activated"));
            new UserFacade().updateUser(sessionController.getUser());
        }
        return "index?faces-redirect=true";
    }

    public String activateAutomaticaly(){
        /*
        * Method for account activation by following link from email
                * */
        return "infopage?faces-redirect=true";
    }

    public List<User> searchByEmailTop5(String email){
        if(email.length() > 3){
            return new ArrayList<User>();
        }
        List<User> userList;
        userList = new UserFacade().searchByEmailBy5(email);
        //System.out.println("Searching for user: "+email);
        return userList;
    }

    public void destroySession(){
        //System.out.println("DESTROYING USER SESSION");
        sessionController.signOut();
    }

}
