package kg.goent.controllers;

import kg.goent.facade.MemberRoleFacade;
import kg.goent.facade.MemberStatusFacade;
import kg.goent.facade.ProjectFacade;
import kg.goent.facade.ProjectMemberFacade;
import kg.goent.models.MemberRole;
import kg.goent.models.Project;
import kg.goent.models.ProjectMember;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by azire on 4/20/2017.
 */
@ManagedBean
@ViewScoped
public class ProjectController {
    private Project project;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    private ProjectSession projectSession;

    @PostConstruct
    void initialize(){
        project = new Project();
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public void setProjectSession(ProjectSession projectSession) {
        this.projectSession = projectSession;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String createProject(){
        if(!userSession.isLogged()){
            Tools.faceMessageWarn("Операция невозможна.","");
        }
        new ProjectFacade().create(project);

        ProjectMember projectMember = new ProjectMember();
        projectMember.setMemberStatus(new MemberStatusFacade().findByStatus("accepted"));
        projectMember.setMemberRole(new MemberRoleFacade().findByStatus("administrator"));
        projectMember.setProject(project);
        projectMember.setUser(userSession.getUser());
        new ProjectMemberFacade().create(projectMember);

        return "index";
    }

    public String projectOverView(Project project){
        projectSession.setProject(project);
        return "/pages/project-overview";
    }
}
