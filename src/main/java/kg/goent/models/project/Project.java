package kg.goent.models.project;

import kg.goent.models.bmc.Bmc;
import kg.goent.models.hypothesis.HypothesisContainer;

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

    @OneToMany(mappedBy = "project",fetch = FetchType.EAGER)
    private List<ProjectMember> memberList = new ArrayList<ProjectMember>();

    @OneToOne(mappedBy = "project")
    private Bmc bmc;

    @OneToOne(mappedBy = "project")
    private HypothesisContainer hypothesisContainer;

    public HypothesisContainer getHypothesisContainer() {
        return hypothesisContainer;
    }

    public void setHypothesisContainer(HypothesisContainer hypothesisContainer) {
        this.hypothesisContainer = hypothesisContainer;
    }

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

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<ProjectMember> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ProjectMember> memberList) {
        this.memberList = memberList;
    }

    public Bmc getBmc() {
        return bmc;
    }

    public void setBmc(Bmc bmc) {
        this.bmc = bmc;
    }

    public ProjectMember getTeamLeader(){
        for(ProjectMember pm : getMemberList()){
            if(pm.getMemberRole().getMemberRoleId()==1)
                return pm;
        }
        return new ProjectMember();
    }

    public List<ProjectMember> getTeamMembers(){
        List<ProjectMember> pmList = new ArrayList<ProjectMember>();
        for(ProjectMember pm : getMemberList()){
            if(pm.getMemberRole().getMemberRoleId() == 2)
                pmList.add(pm);
        }
        return pmList;
    }
    public List<ProjectMember> getObservers(){
        List<ProjectMember> pmList = new ArrayList<ProjectMember>();
        for(ProjectMember pm : getMemberList()){
            if(pm.getMemberRole().getMemberRoleId() == 3)
                pmList.add(pm);
        }
        return pmList;
    }
}
