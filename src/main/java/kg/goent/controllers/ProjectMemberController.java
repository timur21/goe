package kg.goent.controllers;

import kg.goent.facade.*;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.project.ProjectMemberFacade;
import kg.goent.models.*;
import kg.goent.models.project.MemberRole;
import kg.goent.models.project.Project;
import kg.goent.models.project.ProjectMember;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by b-207 on 4/21/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectMemberController extends GetReqBean {
    private ProjectMember projectMember;

    private String userEmail;
    private String memberRole;
    private int projectId;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    @PostConstruct
    public void init(){
        projectMember = new ProjectMember();
        userEmail = "";
        memberRole = "";
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public ProjectMember getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMember = projectMember;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String createMember(){
        projectMember = new ProjectMember();
        User u = new UserFacade().findByEmail(userEmail);
        if(u == null || u.getEmail() == null){
            Tools.faceMessageWarn("User doesn't exist.","Please, look if email is correct.");
            return "";
        }
        MemberRole mr = new MemberRoleFacade().findByRole(memberRole);
        if(mr == null || mr.getMemberRole() == null || mr.getMemberRoleId() <= 1){
            Tools.faceMessageWarn("Invalid memberole assigned: "+memberRole,"");
            return "";
        }

        Project project = new ProjectFacade().findById(projectId);
        if(project == null || project.getTitle() == null){
            Tools.faceMessageWarn("Invalid PROJECT id","");
            return "";
        }
        ProjectMember tempMember = new ProjectMemberFacade().findByUserAndProject(u,project);
        if(tempMember != null && tempMember.getMemberRole().getMemberRoleId() == 1){
            Tools.faceMessageWarn("Cannot add team leader","");
            return "";
        }

        if(tempMember != null){
            Tools.faceMessageWarn("User already in PROJECT team","");
            return "";
        }

        ProjectMember pm = new ProjectMemberFacade().findByUserAndProject(sessionController.getUser(),project);

        if(pm == null || pm.getMemberRole().getMemberRoleId() != 1){
            Tools.faceMessageWarn("You do not have privileges","");
            return "";
        }

        projectMember.setUser(u);
        projectMember.setMemberRole(mr);
        project.getMemberList().add(projectMember);
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("pending"));
        projectMember.setProject(project);
        new ProjectMemberFacade().create(projectMember);

        sessionController.getUser().setProjectMemberList(new ProjectMemberFacade().findByUser(sessionController.getUser()));

        Tools.faceMessageWarn("New team member has been added","Success");
        return PROJECT_ADD_MEMEBER + REDIRECT + "projectId="+projectId;
    }

    public String addMember(){
        return PROJECT_ADD_MEMEBER + REDIRECT + "projectId="+projectId;
    }

    public List<MemberRole> findAllSimpleUsers(){
        return new MemberRoleFacade().findAllSimpleUsers();
    }

    public List<ProjectMember> getAllProjectMembers(){
        return new ProjectMemberFacade().findByUser(sessionController.getUser());
    }

}
