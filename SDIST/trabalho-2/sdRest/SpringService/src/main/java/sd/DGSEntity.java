package sd;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dgs")
public class DGSEntity {

    @Id
    @SequenceGenerator(name = "dgs_sequence", sequenceName = "dgs_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dgs_sequence")
    Integer id;
    private String centerName;
    private Integer maxNVaccines;
    private Integer nVaccines;
    private Integer nVaccinated;
    private Date date;

    public DGSEntity()
    {
        centerName = "";
        nVaccines = 0;
        nVaccinated = 0;
        date = new Date(0);
    }
    public DGSEntity(String centerName, Integer maxNVaccines)
    {
        this.centerName = centerName;
        this.maxNVaccines = maxNVaccines;
        this.nVaccinated = 0;
    }

    public DGSEntity(String centerName, Integer nVaccines, Date date)
    {
        this.centerName = centerName;
        this.nVaccines = nVaccines;
        this.date = date;
        this.nVaccinated = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Integer getMaxNVaccines() {
        return maxNVaccines;
    }

    public void setMaxNVaccines(Integer maxNVaccines) {
        this.maxNVaccines = maxNVaccines;
    }

    public Integer getnVaccines() {
        return nVaccines;
    }

    public void setnVaccines(Integer nVaccines) {
        this.nVaccines = nVaccines;
    }

    public Integer getnVaccinated() {
        return nVaccinated;
    }

    public void setnVaccinated(Integer nVaccinated) {
        this.nVaccinated = nVaccinated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DGSEntity{" +
                "id=" + id +
                ", centerName='" + centerName + '\'' +
                ", maxNVaccines=" + maxNVaccines +
                ", nVaccines=" + nVaccines +
                ", nVaccinated=" + nVaccinated +
                ", date=" + date +
                '}';
    }
}