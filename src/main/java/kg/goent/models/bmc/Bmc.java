package kg.goent.models.bmc;

import kg.goent.models.project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Bmc.findByProject",
                query = "SELECT b FROM Bmc b WHERE b.project = :project")
})
public class Bmc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bmcId;

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "bmc")
    private List<SegmentContainer> segmentContainerList = new ArrayList<SegmentContainer>();

    @ManyToOne
    @JoinColumn(name="bmcStatusId")
    private BmcStatus bmcStatus;


    public int getBmcId() {
        return bmcId;
    }

    public void setBmcId(int bmcId) {
        this.bmcId = bmcId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<SegmentContainer> getSegmentContainerList() {
        return segmentContainerList;
    }

    public void setSegmentContainerList(List<SegmentContainer> segmentContainerList) {
        this.segmentContainerList = segmentContainerList;
    }

    public BmcStatus getBmcStatus() {
        return bmcStatus;
    }

    public void setBmcStatus(BmcStatus bmcStatus) {
        this.bmcStatus = bmcStatus;
    }
}