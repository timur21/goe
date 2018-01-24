package kg.goent.controllers;

import kg.goent.models.project.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by azire on 4/20/2017.
 */
@ManagedBean
@SessionScoped
public class ProjectSession extends GetReqBean {
    private Project project;
    private boolean bmcReady;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String closeProject(){
        return "index";
    }

    public boolean isBmcFinished(){
        return project.getBmc().getBmcStatus().getBmcStatusId() == 1;
    }
}
