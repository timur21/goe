package kg.goent.controllers;

/**
 * Created by tosyak on 6/1/2017.
 */

public class GetReqBean {
    protected int projectId,bmcId,segmentId,segmentContainerId,projectMemberId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getBmcId() {
        return bmcId;
    }

    public void setBmcId(int bmcId) {
        this.bmcId = bmcId;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public int getSegmentContainerId() {
        return segmentContainerId;
    }

    public void setSegmentContainerId(int segmentContainerId) {
        this.segmentContainerId = segmentContainerId;
    }

    public int getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(int projectMemberId) {
        this.projectMemberId = projectMemberId;
    }
}