package kg.goent.models.bmc;

import kg.goent.facade.bmc.BmcFacade;
import kg.goent.facade.bmc.SegmentTypeFacade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "SegmentContainer.findAll",
                query = "SELECT sc FROM SegmentContainer sc"),
        @NamedQuery(name = "SegmentContainer.findByBmc",
                query = "SELECT sc FROM SegmentContainer sc WHERE sc.bmc = :bmc")
})
public class SegmentContainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentContainerId;

    @OneToMany(mappedBy = "segmentContainer",fetch = FetchType.EAGER)
    private List<Segment> segmentList = new ArrayList<Segment>();

    @ManyToOne
    @JoinColumn(name = "bmcId")
    private Bmc bmc;

    @Transient
    private List<Segment> vpList,dsList,crList,rsList,krList,kaList,kpList, costSList;
    @Transient
    private Segment customerSegment;

    public SegmentContainer() {

    }

    public int getSegmentContainerId() {
        return segmentContainerId;
    }

    public void setSegmentContainerId(int segmentContainerId) {
        this.segmentContainerId = segmentContainerId;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }

    public Bmc getBmc() {
        return bmc;
    }

    public void setBmc(Bmc bmc) {
        this.bmc = bmc;
    }

    public Segment getCustomerSegment() {
        for (Segment segment: segmentList){
            if(segment.getSegmentType().getSegmentTypeId()==1) {
                customerSegment = segment;
                if(vpList == null)
                    initLists();
            }
        }
        return customerSegment;
    }

    public void setCustomerSegment(Segment customerSegment) {
        this.customerSegment = customerSegment;
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
        for(Segment s : segmentList){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s);
            }
        }
        return list;
    }

    public List<String> getSegmentsAsStrList(int type){
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
        List<String> list = new ArrayList<String>();
        for(Segment s : segmentList){
            if(s.getSegmentType().getSegmentTypeId() == type){
                list.add(s.getSegmentTitle());
            }
        }
        System.out.println("type: "+type+" | len: "+list.size());
        return list;
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

    public void initLists(){
        if(segmentList.size() == 0){
            return;
        }
        vpList = new ArrayList<Segment>();
        dsList = new ArrayList<Segment>();
        crList = new ArrayList<Segment>();
        rsList = new ArrayList<Segment>();
        krList = new ArrayList<Segment>();
        kaList = new ArrayList<Segment>();
        kpList = new ArrayList<Segment>();
        costSList = new ArrayList<Segment>();
        if(segmentList.size() > 0)
            for(Segment segment : segmentList){
                switch (segment.getSegmentType().getSegmentTypeId()){
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

    public void refreshSegmentList(){
        segmentList = new ArrayList<Segment>();
        segmentList.add(customerSegment);
        segmentList.addAll(vpList);
        segmentList.addAll(dsList);
        segmentList.addAll(crList);
        segmentList.addAll(rsList);
        segmentList.addAll(krList);
        segmentList.addAll(kaList);
        segmentList.addAll(kpList);
        segmentList.addAll(costSList);
    }

    public void addSegment(int typeId){
        Segment segment = new Segment();
        segment.setSegmentType(new SegmentTypeFacade().findById(typeId));
        segment.setSegmentContainer(this);
        switch (typeId){
            case 2:
                if(vpList == null)
                    vpList =new ArrayList<Segment>();
                vpList.add(segment);break;
            case 3:
                if(dsList == null)
                    dsList = new ArrayList<Segment>();
                dsList.add(segment);break;
            case 4:
                if(crList == null)
                    crList = new ArrayList<Segment>();
                crList.add(segment);break;
            case 5:
                if(rsList == null)
                    rsList = new ArrayList<Segment>();
                rsList.add(segment);break;
            case 6:
                if(krList == null)
                    krList = new ArrayList<Segment>();
                krList.add(segment);break;
            case 7:
                if(kaList == null)
                    kaList = new ArrayList<Segment>();
                kaList.add(segment);break;
            case 8:
                if(kpList == null)
                    kpList = new ArrayList<Segment>();
                kpList.add(segment);break;
            case 9:
                if(costSList == null)
                    costSList = new ArrayList<Segment>();
                costSList.add(segment);break;
        }
    }

    public void initSegmentContainer(){
        setSegmentList(new ArrayList<Segment>());
        List<SegmentType> segmentTypeList = new SegmentTypeFacade().findAllOrdered();
        for(SegmentType st : segmentTypeList){
            Segment segment = new Segment();
            segment.setSegmentType(st);
            segment.setSegmentContainer(this);
            segmentList.add(segment);
        }
        this.initLists();
    }
}
