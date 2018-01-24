package kg.goent.models.hypothesis;

import kg.goent.models.bmc.Segment;

import javax.persistence.*;
import java.util.List;

/**
 * Created by azire on 5/13/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Hypothesis.findAll",
                query = "SELECT h FROM Hypothesis h"),
        @NamedQuery(name = "Hypothesis.findBySegment",
                query = "SELECT h FROM Hypothesis h WHERE h.segment = :segment"),
        @NamedQuery(name = "Hypothesis.findByContainer",
                query = "SELECT h FROM Hypothesis h WHERE h.hypothesisContainer = :container")
})
public class Hypothesis {
    @Id
    @GeneratedValue
    private int hypothesisId;

    @OneToOne
    @JoinColumn(name = "segmentId")
    private Segment segment;

    @Column
    private int status;

    @ManyToOne
    @JoinColumn(name = "hypothesisContainerId")
    private HypothesisContainer hypothesisContainer;

    public int getHypothesisId() {
        return hypothesisId;
    }

    public void setHypothesisId(int hypothesisId) {
        this.hypothesisId = hypothesisId;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        /**
         * 1 = not approved
         * 2 = accepted
         * 3 = rejected
         */
        this.status = status;
    }

    public HypothesisContainer getHypothesisContainer() {
        return hypothesisContainer;
    }

    public void setHypothesisContainer(HypothesisContainer hypothesisContainer) {
        this.hypothesisContainer = hypothesisContainer;
    }
}
