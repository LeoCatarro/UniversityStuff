package sd;

import java.io.Serializable;

public class VacCenterEntity implements Serializable {
    private Integer id;
    private String name;
    private String location;

    public VacCenterEntity(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
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

    public void setId(Integer id) {this.id = id; }

    public Integer getId() {return id;}

    @Override
    public String toString() {
        return "VacCenterEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
