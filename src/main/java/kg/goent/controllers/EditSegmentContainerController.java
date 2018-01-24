package kg.goent.controllers;

import kg.goent.facade.UserFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.facade.hypothesis.HypothesisFacade;
import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.project.ProjectMemberFacade;
import kg.goent.models.User;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.project.ProjectMember;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by azire on 5/4/2017.
 */
@ManagedBean
@SessionScoped
public class EditSegmentContainerController extends GetReqBean {
    private SegmentContainer segmentContainer = new SegmentContainer();

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

    @Override
    public void setSegmentContainerId(int segmentContainerId) {
        if(segmentContainerId != segmentContainer.getSegmentContainerId()){
            segmentContainer = new SegmentContainerFacade().findById(segmentContainerId);
            if(segmentContainer.getSegmentList() == null ||segmentContainer.getSegmentList().size() == 0){
                new SegmentContainerFacade().delete(segmentContainer);
            }
        }
        super.setSegmentContainerId(segmentContainerId);
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public String addSegment(int typeId){
        segmentContainer.addSegment(typeId);
        return EDIT_SEGMENT_CONTAINER + REDIRECT + "projectId=" + projectId + "&bmcId=" + bmcId + "&segmentContainerId=" + segmentContainerId;
    }

    public String editSegmentContainer(SegmentContainer sc){
        return EDIT_SEGMENT_CONTAINER + REDIRECT+"projectId="+projectId+"&bmcId="+
                bmcId+"&segmentContainerId="+sc.getSegmentContainerId();
    }

    public String saveSegmentContainer(){
        segmentContainer.refreshSegmentList();
        System.out.println("segment size: "+segmentContainer.getSegmentList().size());
        for(Segment segment : segmentContainer.getSegmentList()){
            new SegmentFacade().update(segment);
        }
        return BMC_OVERVIEW + REDIRECT + "projectId=" + projectId + "&bmcId=" + bmcId;
    }

    public String removeSegment(Segment segment){
        if(!isAllowedOperation()){

        }
        segmentContainer.getSegmentList().remove(segment);
        segmentContainer.initLists();
        new SegmentFacade().delete(segment);
        return EDIT_SEGMENT_CONTAINER + REDIRECT + "projectId=" + projectId + "&bmcId=" + bmcId + "&segmentContainerId=" + segmentContainerId;
    }

    public String deleteSegmentContainer(){
        if(!isAllowedOperation()){

        }
        SegmentContainer sc = new SegmentContainerFacade().findById(segmentContainerId);
        if(sc == null)
            return BMC_OVERVIEW + REDIRECT + "projectId=" + projectId + "&bmcId=" + bmcId;
//        System.out.println("removeContId: "+sc.getSegmentContainerId());
        if(sc.getSegmentList().size() > 0)
            for(Segment s : sc.getSegmentList()){
                if(s != null) {
                    Hypothesis hypothesis = new HypothesisFacade().findBySegment(s);
                    if (hypothesis != null)
                        new HypothesisFacade().delete(hypothesis);
                    new SegmentFacade().delete(s);
                }
            }
        new SegmentContainerFacade().delete(new SegmentContainerFacade().findById(segmentContainerId));
        return BMC_OVERVIEW + REDIRECT + "projectId=" + projectId + "&bmcId=" + bmcId;
    }

    private boolean isAllowedOperation(){
        List<ProjectMember> pmList = new ProjectMemberFacade().findByProject(new ProjectFacade().findById(projectId));

        boolean allowed = false;
        for(ProjectMember pm : pmList){
            if(pm.getMemberRole().getMemberRoleId() <= 2 && pm.getUser().getUserId() == sessionController.getUser().getUserId())
                allowed = true;
        }
        return allowed;
    }
}