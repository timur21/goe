package kg.goent.models.hypothesis;


import kg.goent.models.project.Project;
import kg.goent.models.bmc.Segment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/13/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "HypothesisContainer.findAll",
                query = "SELECT hc FROM HypothesisContainer hc"),
        @NamedQuery(name = "HypothesisContainer.findByProject",
                query = "SELECT hc FROM HypothesisContainer hc WHERE hc.project = :project")
})
public class HypothesisContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hypothesisContainerId;

    @OneToMany(mappedBy = "hypothesisContainer")
    private List<Hypothesis> hypothesisList = new ArrayList<Hypothesis>();

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @Transient
    private List<Hypothesis> csHypList,vpHypList,dcHypList,crHypList;

    @Transient
    private List<Hypothesis> uCsHypList,uVpHypList,uDcHypList,uCrHypList;

    public int getHypothesisContainerId() {
        return hypothesisContainerId;
    }

    public void setHypothesisContainerId(int hypothesisContainerId) {
        this.hypothesisContainerId = hypothesisContainerId;
    }

    public List<Hypothesis> getHypothesisList() {
        return hypothesisList;
    }

    public void setHypothesisList(List<Hypothesis> hypothesisList) {
        this.hypothesisList = hypothesisList;
        initLists();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Hypothesis> getCsHypList() {
        return csHypList;
    }

    public void setCsHypList(List<Hypothesis> csHypList) {
        this.csHypList = csHypList;
    }

    public List<Hypothesis> getVpHypList() {
        return vpHypList;
    }

    public void setVpHypList(List<Hypothesis> vpHypList) {
        this.vpHypList = vpHypList;
    }

    public List<Hypothesis> getDcHypList() {
        return dcHypList;
    }

    public void setDcHypList(List<Hypothesis> dcHypList) {
        this.dcHypList = dcHypList;
    }

    public List<Hypothesis> getCrHypList() {
        return crHypList;
    }

    public void setCrHypList(List<Hypothesis> crHypList) {
        this.crHypList = crHypList;
    }

    public List<Hypothesis> getuCsHypList() {
        return uCsHypList;
    }

    public void setuCsHypList(List<Hypothesis> uCsHypList) {
        this.uCsHypList = uCsHypList;
    }

    public List<Hypothesis> getuVpHypList() {
        return uVpHypList;
    }

    public void setuVpHypList(List<Hypothesis> uVpHypList) {
        this.uVpHypList = uVpHypList;
    }

    public List<Hypothesis> getuDcHypList() {
        return uDcHypList;
    }

    public void setuDcHypList(List<Hypothesis> uDcHypList) {
        this.uDcHypList = uDcHypList;
    }

    public List<Hypothesis> getuCrHypList() {
        return uCrHypList;
    }

    public void setuCrHypList(List<Hypothesis> uCrHypList) {
        this.uCrHypList = uCrHypList;
    }

    public void initLists(){
        if(hypothesisList == null || hypothesisList.size() == 0)
            return;
        csHypList = new ArrayList<Hypothesis>();
        vpHypList = new ArrayList<Hypothesis>();
        dcHypList = new ArrayList<Hypothesis>();
        crHypList = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisList){
            switch (h.getSegment().getSegmentType().getSegmentTypeId()){
                case 1:csHypList.add(h);break;
                case 2:vpHypList.add(h);break;
                case 3:dcHypList.add(h);break;
                case 4:crHypList.add(h);break;
            }
        }
    }

    public void initULists(){
        if(hypothesisList == null || hypothesisList.size() == 0)
            return;
        uCsHypList = new ArrayList<Hypothesis>();
        uVpHypList = new ArrayList<Hypothesis>();
        uDcHypList = new ArrayList<Hypothesis>();
        uCrHypList = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisList){
            switch (h.getSegment().getSegmentType().getSegmentTypeId()){
                case 1:
                    if(!containsHypothesis(uCsHypList,h))
                        uCsHypList.add(h);
                    break;
                case 2:
                    if(!containsHypothesis(uVpHypList,h))
                        uVpHypList.add(h);
                    break;
                case 3:
                    if(!containsHypothesis(uDcHypList,h))
                        uDcHypList.add(h);
                    break;
                case 4:
                    if(!containsHypothesis(uCrHypList,h))
                        uCrHypList.add(h);
                    break;
            }
        }
    }

    private boolean containsHypothesis(List<Hypothesis> hList,Hypothesis hypothesis){
        if(csHypList.size() > 0)
            for(Hypothesis h : hList){
                if(h.getSegment().getSegmentTitle().toLowerCase().equals(hypothesis.getSegment().getSegmentTitle())){
                    return true;
                }
            }
        return false;
    }
}
