package kg.goent.controllers;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.facade.bmc.SegmentFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;
import kg.goent.tools.ViewPath;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kg.goent.tools.ViewPath.*;

/**
 * Created by azire on 5/4/2017.
 */
@ManagedBean
@SessionScoped
public class SegmentContainerController extends GetReqBean {
    private SegmentContainer segmentContainer = new SegmentContainer();

    private List<SegmentContainer> segmentContainerList = new ArrayList<SegmentContainer>(Arrays.asList(new SegmentContainer()));

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

    public List<SegmentContainer> getSegmentContainerList() {
        segmentContainerList = new SegmentContainerFacade().findByBmc(new BmcFacade().findById(bmcId));
        return segmentContainerList;
    }

    public void setSegmentContainerList(List<SegmentContainer> segmentContainerList) {
        this.segmentContainerList = segmentContainerList;
    }

    private void initSegmentContainer(){
        if(bmcId != 0) {
            segmentContainer.setBmc(new BmcFacade().findById(bmcId));
            segmentContainer.initSegmentContainer();
        }
    }

    public String addSegmentContainer(){
        initSegmentContainer();
        return ViewPath.ADD_SEGMENT_CONTAINER + ViewPath.REDIRECT+"projectId="+projectId+"&bmcId="+bmcId+"&create="+true;
    }

    public String addSegment(int typeId){
        segmentContainer.addSegment(typeId);
        return ADD_SEGMENT_CONTAINER + REDIRECT+"projectId="+projectId+"&bmcId="+bmcId;
    }

    public String createSegmentContainer(){
        new SegmentContainerFacade().create(segmentContainer);
        segmentContainer.refreshSegmentList();
        for(Segment segment : segmentContainer.getSegmentList()){
            new SegmentFacade().create(segment);
        }
        segmentContainer = new SegmentContainer();

        return ViewPath.BMC_OVERVIEW + ViewPath.REDIRECT+"projectId="+projectId+"&bmcId="+bmcId;
    }

    public List<Segment> getSegmentList(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(SegmentContainer sc : segmentContainerList){
            for(Segment s : sc.getSegmentList()){
                if(s.getSegmentType().getSegmentTypeId() == type){
                    list.add(s);
                }
            }
        }
        return list;
    }
    public List<Segment> getSegments(int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(Segment s : segmentContainer.getSegmentList()){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }
    public List<Segment> getSegmentsFrom(SegmentContainer sc,int type){
        /**
         * 1 - Customer Segment
         * 2 - Value Prop
         * 3 - Distribution Channel
         * 4 - Customer Relationship
         * 5 - Revenue Stream
         * 6 - Key Resources
         * 7 - Key Activities
         * 8 - Key Partners
         * 9 - Cost Structure
         */
        List<Segment> list = new ArrayList<Segment>();
        for(Segment s : sc.getSegmentList()){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }
}