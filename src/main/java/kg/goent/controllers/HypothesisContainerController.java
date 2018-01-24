package kg.goent.controllers;

import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.facade.hypothesis.HypothesisContainerFacade;
import kg.goent.facade.hypothesis.HypothesisFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;
import kg.goent.models.project.Project;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by azire on 5/13/2017.
 */
@ManagedBean
@ViewScoped
public class HypothesisContainerController extends GetReqBean {

    private HypothesisContainer hypothesisContainer;

    private List<Hypothesis> csHypList,vpHypList,dcHypList,crHypList;

    @Override
    public void setProjectId(int projectId) {
        hypothesisContainer = new HypothesisContainerFacade().findByProject(new ProjectFacade().findById(projectId));
        if(hypothesisContainer != null)
            hypothesisContainer.setHypothesisList(new HypothesisFacade().findByContainer(hypothesisContainer));
        super.setProjectId(projectId);
    }

    public HypothesisContainer getHypothesisContainer() {
        return hypothesisContainer;
    }

    public void setHypothesisContainer(HypothesisContainer hypothesisContainer) {
        this.hypothesisContainer = hypothesisContainer;
    }

    public List<Hypothesis> getCsHypList() {
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 1){
                list.add(h);
            }
        }
        return list;
    }

    public void setCsHypList(List<Hypothesis> csHypList) {
        this.csHypList = csHypList;
    }

    public List<Hypothesis> getVpHypList() {
        List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 2){
                list.add(h);
            }
        }
        return list;    }

    public void setVpHypList(List<Hypothesis> vpHypList) {
        this.vpHypList = vpHypList;
    }

    public List<Hypothesis> getDcHypList() {List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 3){
                list.add(h);
            }
        }
        return list;
    }

    public void setDcHypList(List<Hypothesis> dcHypList) {
        this.dcHypList = dcHypList;
    }

    public List<Hypothesis> getCrHypList() {List<Hypothesis> list = new ArrayList<Hypothesis>();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            if(h.getSegment().getSegmentType().getSegmentTypeId() == 4){
                list.add(h);
            }
        }
        return list;
    }

    public void setCrHypList(List<Hypothesis> ctHypList) {
        this.crHypList = ctHypList;
    }

    public String initializeHypothesisContainer() {
        /**
         * Initializes hypothesisContainer object before using
         */
        Project project = new ProjectFacade().findById(projectId);
        if (project.getBmc().getBmcStatus().getBmcStatusId() == 2){
            Tools.faceMessageWarn(Tools.getFieldMsg("bmcIsNotFinished"),"");
            return PROJECT_OVERVIEW + REDIRECT + "projectId="+projectId;
        }
        hypothesisContainer = new HypothesisContainerFacade().findByProject(project);
        if(hypothesisContainer == null){
            createHypothesisList();
            hypothesisContainer = new HypothesisContainerFacade().findByProject(project);
        }
        hypothesisContainer.setHypothesisList(new HypothesisFacade().findByContainer(hypothesisContainer));
        hypothesisContainer.initLists();
        return HYPOTHESIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
    }

    public String viewHypothesisOverView(){
        /**
         * initializes hypothesis lists
         */
        String redirect = HYPOTHESIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
        hypothesisContainer = new HypothesisContainerFacade().findByProject(new ProjectFacade().findById(projectId));
        if(hypothesisContainer != null)
            hypothesisContainer.setHypothesisList(new HypothesisFacade().findByContainer(hypothesisContainer));

        //= new HypothesisContainerFacade().findByProject(new ProjectFacade().findById(projectId));

        if(hypothesisContainer == null ||
                hypothesisContainer.getHypothesisList().size() == 0){
            redirect = initializeHypothesisContainer();
        }
        return redirect;
    }

    private void createHypothesisList(){
        hypothesisContainer = new HypothesisContainer();
        hypothesisContainer.setProject(new ProjectFacade().findById(projectId));
        new HypothesisContainerFacade().create(hypothesisContainer);
        initializeHypothesisList();
    }

    public String refreshHypothesisList(){
        clearHypothesisList();
        initializeHypothesisList();
        return HYPOTHESIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
    }

    private void clearHypothesisList(){
        HypothesisFacade hf = new HypothesisFacade();
        for(Hypothesis h : hypothesisContainer.getHypothesisList()){
            hf.delete(h);
        }
        hypothesisContainer.setHypothesisList(null);
    }

    private void initializeHypothesisList(){
        List<SegmentContainer>  segmentContainerList = new SegmentContainerFacade().findByBmc(new ProjectFacade().findById(projectId).getBmc());
        for(SegmentContainer sc : segmentContainerList){
            for(Segment s : sc.getSegmentList()){
                if(s.getSegmentType().getSegmentTypeId() <= 4){
                    Hypothesis hypothesis = new Hypothesis();
                    hypothesis.setSegment(s);
                    hypothesis.setStatus(0);
                    hypothesis.setHypothesisContainer(hypothesisContainer);
                    new HypothesisFacade().create(hypothesis);
                }
            }
        }
    }

}
