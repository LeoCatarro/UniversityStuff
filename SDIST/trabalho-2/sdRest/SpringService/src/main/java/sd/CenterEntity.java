package sd;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "center")
public class CenterEntity {
    @Id
    @SequenceGenerator(name = "center_sequence", sequenceName = "center_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "center_sequence")
    private Integer id;
    private String name;
    private String location;
    private Integer nVaccines;
    private Integer personId;
    private Date date;

    public CenterEntity()
    {
        name = "";
        location = "";
        nVaccines = 0;
        date = new Date(0);
    }

    public CenterEntity(String name)
    {
        this.name = name;
    }

    public CenterEntity(String name, String location)
    {
        this.name = name;
        this.location = location;
    }

    public CenterEntity(String name, String location, Integer nVaccines)
    {
        this.name = name;
        this.location = location;
        this.nVaccines = nVaccines;
    }

    public CenterEntity(String name, String location, Integer nVaccines, Date date, Integer personId)
    {
        this.name = name;
        this.location = location;
        this.nVaccines = nVaccines;
        this.date = date;
        this.personId = personId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getnVaccines() {
        return nVaccines;
    }

    public void setnVaccines(Integer nVaccines) {
        this.nVaccines = nVaccines;
    }


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CenterEntity{" +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}