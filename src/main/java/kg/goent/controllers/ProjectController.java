package kg.goent.controllers;

import kg.goent.facade.*;
import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.BmcStatusFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.project.ProjectMemberFacade;
import kg.goent.facade.project.ProjectStatusFacade;
import kg.goent.models.*;
import kg.goent.models.project.Project;
import kg.goent.models.project.ProjectMember;
import kg.goent.tools.Tools;
import kg.goent.tools.ViewPath;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

import static kg.goent.tools.ViewPath.PROJECT_OVERVIEW;
import static kg.goent.tools.ViewPath.REDIRECT;

/**
 * Created by timur on 4/20/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectController extends GetReqBean {
    private Project project;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    private ProjectMember projectMember;

    @PostConstruct
    void initialize(){
        project = new Project();
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        if(projectId != 0){
            project = new ProjectFacade().findById(projectId);
        }
        this.projectId = projectId;
    }

    public Project getFromDbProject(int id){
        return new ProjectFacade().findById(id);
    }

    public String addProject(){
        return ViewPath.ADD_PROJECT + ViewPath.REDIRECT;
    }

    public String createProject(){
        if(!sessionController.isLogged()){
            Tools.faceMessageWarn("Операция невозможна.","");
            return "";
        }
        if(new ProjectFacade().findByTitle(project.getTitle()) != null){
            Tools.faceMessageWarn("Проект с таким названием уже существует.","");
            return "";
        }
        project.setProjectDate(new Date());
        project.setProjectStatus(new ProjectStatusFacade().findByStatus("active"));

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("accepted"));
        projectMember.setMemberRole(new MemberRoleFacade().findByRole("team leader"));
        projectMember.setActivationDate(new Date());
        projectMember.setUser(sessionController.getUser());

        new ProjectFacade().create(project);
        System.out.print(project);
        projectMember.setProject(project);

        new ProjectMemberFacade().create(projectMember);

        sessionController.setUser(new UserFacade().findById(sessionController.getUser().getUserId()));
        return "/index?faces-redirect=true";

    }

    public String removeProject(Project project){
        ProjectMemberFacade pmFacade = new ProjectMemberFacade();
        List<ProjectMember> pmList = pmFacade.findByProject(project);

        for(ProjectMember pm : pmList){
            pmFacade.delete(pmFacade.findById(pm.getProjectMemberId()));
        }

        new ProjectFacade().delete(project);

        sessionController.getUser().setProjectMemberList(pmFacade.findByUser(sessionController.getUser()));

        return "index?faces-redirect=true";
    }

    public boolean existsProject(int projectId){
        Project p = new ProjectFacade().findById(projectId);
        return p != null && p.getTitle() != null;
    }

    public String addProjectMember(String email){
        /*
        * check for existing of user
        * send invitation to email
        * add PROJECT member to PROJECT with pending status and teamMember role
        *
        * */
        User user = new UserFacade().findByEmail(email);
        if(user == null){
            Tools.faceMessageWarn("User does not exist.","check if email is correct.");
            return "";
        }

        return "";
    }

    public String projectOverView(Project project){
        return ViewPath.PROJECT_OVERVIEW + ViewPath.REDIRECT+"projectId="+project.getProjectId();
    }

    public boolean hasBmc() {
        return project != null && project.getBmc() != null;
    }
    public boolean hasAccess(){
        boolean hasAcccess = false;
        for (ProjectMember pm : project.getMemberList()){
            if(pm.getUser().getUserId() == sessionController.getUser().getUserId()){
                hasAcccess = true;
                break;
            }
        }
        return hasAcccess;
    }
}