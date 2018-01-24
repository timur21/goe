package kg.goent.controllers;

import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.facade.bmc.SegmentTypeFacade;
import kg.goent.facade.hypothesis.HypothesisContainerFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by azire on 5/12/2017.
 */
@ManagedBean
@SessionScoped
public class HypothesisController extends GetReqBean{
    private List<Segment> csList,vpList,dcList,crList;

    private Hypothesis hypothesis;
    private HypothesisContainer hypothesisContainer;

    @PostConstruct
    public void init(){
        csList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(1));
        vpList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(2));
        dcList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(3));
        crList = new SegmentFacade().findBySegmentType(new SegmentTypeFacade().findById(4));
    }

    @Override
    public void setProjectId(int projectId) {
        hypothesisContainer = new HypothesisContainerFacade().findByProject(new ProjectFacade().findById(projectId));
        super.setProjectId(projectId);
    }

    public Hypothesis getHypothesis() {
        return hypothesis;
    }

    public void setHypothesis(Hypothesis hypothesis) {
        this.hypothesis = hypothesis;
    }

    public HypothesisContainer getHypothesisContainer() {
        return hypothesisContainer;
    }

    public void setHypothesisContainer(HypothesisContainer hypothesisContainer) {
        this.hypothesisContainer = hypothesisContainer;
    }

    public List<Segment> getCsList() {
        return csList;
    }

    public void setCsList(List<Segment> csList) {
        this.csList = csList;
    }

    public List<Segment> getVpList() {
        return vpList;
    }

    public void setVpList(List<Segment> vpList) {
        this.vpList = vpList;
    }

    public List<Segment> getDcList() {
        return dcList;
    }

    public void setDcList(List<Segment> dcList) {
        this.dcList = dcList;
    }

    public List<Segment> getCrList() {
        return crList;
    }

    public void setCrList(List<Segment> crList) {
        this.crList = crList;
    }
}
