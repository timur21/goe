package kg.goent.models.project;

import javax.persistence.*;
import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "ProjectStatus.findAll",
            query = "SELECT ps FROM ProjectStatus ps"),
        @NamedQuery(name = "ProjectStatus.findByStatus",
                query = "SELECT ps FROM ProjectStatus ps WHERE ps.status = :status")
})
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectStatusId;

    @Column
    private String status;

    @OneToMany(mappedBy = "projectStatus")
    private List<Project> projectList;

    public ProjectStatus() {
    }

    public ProjectStatus(String status) {
        this.status = status;
    }

    public int getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(int projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
