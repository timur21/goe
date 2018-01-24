package kg.goent.models.bmc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 5/11/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "BmcStatus.findAll",
                query = "SELECT bs FROM BmcStatus bs"),
        @NamedQuery(name = "BmcStatus.findByStatus",
                query = "SELECT bs FROM BmcStatus bs WHERE bs.status = :status")
})
public class BmcStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bmcStatusId;

    @Column
    private String status;

    @OneToMany(mappedBy = "bmcStatus")
    private List<Bmc> bmc = new ArrayList<Bmc>();

    public BmcStatus() {
    }

    public BmcStatus(String status) {
        this.status = status;
    }

    public int getBmcStatusId() {
        return bmcStatusId;
    }

    public void setBmcStatusId(int bmcStatusId) {
        this.bmcStatusId = bmcStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bmc> getBmc() {
        return bmc;
    }

    public void setBmc(List<Bmc> bmc) {
        this.bmc = bmc;
    }
}
