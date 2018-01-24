package kg.goent.models.project;

import kg.goent.models.project.ProjectMember;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "MemberRole.findAll",
                query = "SELECT ms FROM MemberRole ms"),
        @NamedQuery(name = "MemberRole.findByMemberRole",
                query = "SELECT ms FROM MemberRole ms WHERE ms.memberRole = :status"),
        @NamedQuery(name = "MemberRole.findAllSimpleUsers",
                query = "SELECT ms FROM MemberRole ms WHERE ms.memberRole NOT LIKE '%leader%'")
})
public class MemberRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberRoleId;

    @OneToMany(mappedBy = "memberRole")
    private List<ProjectMember> projectMemberList=new ArrayList<ProjectMember>();

    @Column
    private String memberRole;

    public MemberRole() {
    }

    public MemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public int getMemberRoleId() {
        return memberRoleId;
    }

    public void setMemberRoleId(int memberRoleId) {
        this.memberRoleId = memberRoleId;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public List<ProjectMember> getProjectMemberList() {
        return projectMemberList;
    }

    public void setProjectMemberList(List<ProjectMember> projectMemberList) {
        this.projectMemberList = projectMemberList;
    }

    @Override
    public String toString() {
        return memberRole ;
    }
}
