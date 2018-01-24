package kg.goent.models.bmc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "SegmentType.findAll",
                query = "SELECT st FROM SegmentType st"),
        @NamedQuery(name = "SegmentType.findAllOrdered",
                query = "SELECT st FROM SegmentType st ORDER BY st.segmentOrder")
})
public class SegmentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentTypeId;

    @Column
    private String segmentTypeName;

    @Column
    private int segmentOrder;

    @OneToMany(mappedBy = "segmentType")
    private List<Segment> listSegment = new ArrayList<Segment>();

    public SegmentType() {}

    public SegmentType(String segmentTypeName) {
        this.segmentTypeName = segmentTypeName;
    }

    public SegmentType(String segmentTypeName, int segmentOrder) {
        this.segmentTypeName = segmentTypeName;
        this.segmentOrder = segmentOrder;
    }

    public int getSegmentTypeId() {
        return segmentTypeId;
    }

    public void setSegmentTypeId(int segmentTypeId) {
        this.segmentTypeId = segmentTypeId;
    }

    public String getSegmentTypeName() {
        return segmentTypeName;
    }

    public void setSegmentTypeName(String segmentTypeName) {
        this.segmentTypeName = segmentTypeName;
    }

    public int getSegmentOrder() {
        return segmentOrder;
    }

    public void setSegmentOrder(int segmentOrder) {
        this.segmentOrder = segmentOrder;
    }

    public List<Segment> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<Segment> listSegment) {
        this.listSegment = listSegment;
    }
}