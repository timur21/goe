package kg.goent.models.bmc;

import kg.goent.models.hypothesis.Hypothesis;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by azire on 5/2/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Segment.findAll",
                query = "SELECT s FROM Segment s"),
        @NamedQuery(name = "Segment.findBySegmentType",
                query = "SELECT s FROM Segment s WHERE s.segmentType = :segmentType")
})
public class Segment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int segmentId;
    @Column
    private String segmentTitle;

    @ManyToOne
    @JoinColumn(name = "segmentTypeId")
    private SegmentType segmentType;

    @ManyToOne
    @JoinColumn(name = "segmentContainerId")
    private SegmentContainer segmentContainer;

    @OneToOne(mappedBy = "segment")
    private Hypothesis hypothesis;

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentTitle() {
        return segmentTitle;
    }

    public void setSegmentTitle(String segmentTitle) {
        this.segmentTitle = segmentTitle;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(SegmentType segmentType) {
        this.segmentType = segmentType;
    }

    public SegmentContainer getSegmentContainer() {
        return segmentContainer;
    }

    public void setSegmentContainer(SegmentContainer segmentContainer) {
        this.segmentContainer = segmentContainer;
    }
}