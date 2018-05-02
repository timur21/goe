package kg.goent.controllers;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
import kg.goent.models.bmc.Bmc;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentContainer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class SegmentController {

    private List<Segment> csList,vpList,dsList,crList,rsList,krList,kaList,kpList, costSList;
    private BmcFacade bmcFacade;
    private SegmentContainerFacade scFacade;
    public int bmcId;

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

    public List<Segment> getDsList() {
        return dsList;
    }

    public void setDsList(List<Segment> dsList) {
        this.dsList = dsList;
    }

    public List<Segment> getCrList() {
        return crList;
    }

    public void setCrList(List<Segment> crList) {
        this.crList = crList;
    }

    public List<Segment> getRsList() {
        return rsList;
    }

    public void setRsList(List<Segment> rsList) {
        this.rsList = rsList;
    }

    public List<Segment> getKrList() {
        return krList;
    }

    public void setKrList(List<Segment> krList) {
        this.krList = krList;
    }

    public List<Segment> getKaList() {
        return kaList;
    }

    public void setKaList(List<Segment> kaList) {
        this.kaList = kaList;
    }

    public List<Segment> getKpList() {
        return kpList;
    }

    public void setKpList(List<Segment> kpList) {
        this.kpList = kpList;
    }

    public List<Segment> getCostSList() {
        return costSList;
    }

    public void setCostSList(List<Segment> costSList) {
        this.costSList = costSList;
    }

    public int getBmcId() {
        return bmcId;
    }

    public void setBmcId(int bmcId) {
        this.bmcId = bmcId;
    }

    @PostConstruct
    public void init(){
        bmcFacade = new BmcFacade();
        scFacade = new SegmentContainerFacade();
        csList = new ArrayList<Segment>();
        vpList = new ArrayList<Segment>();
        dsList = new ArrayList<Segment>();
        crList = new ArrayList<Segment>();
        rsList = new ArrayList<Segment>();
        krList = new ArrayList<Segment>();
        kaList = new ArrayList<Segment>();
        kpList = new ArrayList<Segment>();
        costSList = new ArrayList<Segment>();
    }

    public void updateLists(){
        updateLists(bmcId);
    }

    public void updateLists(int bmcId){
        Bmc bmc = bmcFacade.findById(bmcId);
        List<SegmentContainer> scList = scFacade.findByBmc(bmc);
        for(SegmentContainer container : scList){
            for(Segment segment : container.getSegmentList()){
                addToList(segment);
            }
        }
    }

    private void addToList(Segment segment){
        switch (segment.getSegmentType().getSegmentTypeId()){
            case 1: csList.add(segment);break;
            case 2: vpList.add(segment);break;
            case 3: dsList.add(segment);break;
            case 4: crList.add(segment);break;
            case 5: rsList.add(segment);break;
            case 6: krList.add(segment);break;
            case 7: kaList.add(segment);break;
            case 8: kpList.add(segment);break;
            case 9: costSList.add(segment);break;
        }
    }

}
