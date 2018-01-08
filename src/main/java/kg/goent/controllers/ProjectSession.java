package kg.goent.controllers;

import kg.goent.models.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by azire on 4/20/2017.
 */
@ManagedBean
@SessionScoped
public class ProjectSession {
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String closeProject(){
        return "index";
    }
}
