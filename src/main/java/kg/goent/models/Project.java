package kg.goent.models;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Project.findAll",
         query = "SELECT p FROM Project p"),
        @NamedQuery(name = "Project.findByTitle",
                query = "SELECT p FROM Project p WHERE p.title = :title")
})
public class Project implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Date projectDate;

    @ManyToOne
    @JoinColumn(name = "projectStatusId")
    private ProjectStatus projectStatus;

    @OneToMany(mappedBy = "projectMemberId")
    private List<ProjectMember> memberList = new ArrayList<ProjectMember>();

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public List<ProjectMember> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ProjectMember> memberList) {
        this.memberList = memberList;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
