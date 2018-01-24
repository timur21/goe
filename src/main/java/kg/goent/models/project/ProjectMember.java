package kg.goent.models.project;

import kg.goent.models.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by timur on 13-Apr-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "ProjectMember.findAll",
                query = "SELECT pm FROM ProjectMember pm"),
        @NamedQuery(name = "ProjectMember.findByProject",
                query = "SELECT pm FROM ProjectMember pm WHERE pm.project = :project"),
        @NamedQuery(name = "ProjectMember.findByUser",
                query = "SELECT pm FROM ProjectMember pm WHERE pm.user = :user"),
        @NamedQuery(name = "ProjectMemeber.findByUserAndProject",
                query = "SELECT pm FROM ProjectMember pm WHERE pm.user = :user AND pm.project = :project")
})

public class ProjectMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectMemberId;


    @Column
    private Date activationDate;
    @Column
    private Date lastActiveDate;

    @ManyToOne
    @JoinColumn(name = "memberStatusId")
    private MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "memberRoleId")
    private MemberRole memberRole;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public int getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(int memberDetailId) {
        this.projectMemberId = memberDetailId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
