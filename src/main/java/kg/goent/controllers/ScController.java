package kg.goent.controllers;

import kg.goent.models.bmc.SegmentContainer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by tosyak on 6/5/2017.
 */
@ManagedBean
@SessionScoped
public class ScController extends GetReqBean{
    private SegmentContainer segmentContainer = new SegmentContainer();

    public SegmentContainer getSegmentContainer() {

        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }

}
