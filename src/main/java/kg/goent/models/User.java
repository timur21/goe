package kg.goent.models;

import kg.goent.models.project.ProjectMember;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by timur on 13-Apr-17.
 */

@FacesValidator(value = "passwordValidator")
@Entity
@NamedQueries({
        @NamedQuery(name="User.findAll",
                query="SELECT u FROM User u"),
        @NamedQuery(name="User.findByPrimaryKey",
                query="SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name="User.findByEmail",
                query="SELECT u FROM kg.goent.models.User u WHERE u.email = :email"),
        @NamedQuery(name="User.findByEmailPass",
                query="SELECT u FROM kg.goent.models.User u WHERE u.email = :email AND u.password = :password"),
        @NamedQuery(name="User.searchByEmail",
                query = "SELECT u FROM kg.goent.models.User u WHERE u.email LIKE :email")
})
public class User implements Serializable,Validator{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private String password;
    @Transient
    private String confirm;

    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private Date regDate;
    @Column
    private String activationKey;

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ProjectMember> projectMemberList = new ArrayList<ProjectMember>();

    @ManyToOne
    @JoinColumn(name = "userRoleId")
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "userStatusId")
    private UserStatus userStatus;

    public int getUserId() {
        return userId;
    }
    public User(){}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String phoneF() {
        String temp = "";
        if(phone != null)
            if(phone.length() == 13) {
                temp = phone.substring(0,4);
                temp += " "+phone.substring(4,7);
                temp += " "+phone.substring(7,10);
                temp += " "+phone.substring(10,13);
            }else
            if(phone.length() == 10){
                temp = phone.substring(0,4);
                temp += " "+phone.substring(4,7);
                temp += " "+phone.substring(7,10);
            }else{
                temp = phone;
            }
        return temp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<ProjectMember> getProjectMemberList() {
        return projectMemberList;
    }

    public void setProjectMemberList(List<ProjectMember> projectMemberList) {
        this.projectMemberList = projectMemberList;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void validate(FacesContext facesContext, UIComponent component, Object value)
            throws ValidatorException{
        String password1 = (String) value;

        UIInput uiPasswordConfirm = (UIInput) component.getAttributes().get("confirmPassword");
        String confirmPassword = uiPasswordConfirm.getSubmittedValue().toString();

        if(password1 == null || password1.isEmpty() || confirmPassword == null) {
            return;
        }
        if(!password1.equals(confirmPassword)){
            uiPasswordConfirm.setValid(false);
            //Tools.faceMessageWarn(confirmPassword + " Passwords don't match " + password1);
            FacesMessage msg = new FacesMessage(confirmPassword + " Passwords don't match " + password1);
            throw new ValidatorException(msg);
        }
    }

//    @Override
//    public String toString() {
//        return "\nUser{" +
//                "userId=" + userId +
//                ", fname='" + fname + '\'' +
//                ", lname='" + lname + '\'' +
//                ", password='" + password + '\'' +
//                ", confirm='" + confirm + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", activationKey='" + activationKey + '\'' +
//                ", projectMemberList=" + projectMemberList +
//                ", userRole=" + userRole +
//                ", userStatus=" + userStatus +
//                "}\n";
//    }
}
