package sd;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Date vacDate;
    private boolean isVaccinated;
    private String vacType;
    private String centerName;

    public PersonEntity()
    {
        age = 0;
        name = "";
        email = "";
        vacDate = new Date(0);
        isVaccinated = false;
        vacType = "Pfizer";
        centerName = "";
    }

    public PersonEntity(String name, Integer age, String email, Date vacDate, String centerName) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.vacDate = vacDate;
        this.centerName = centerName;
        vacType = "Pfizer";
        isVaccinated = false;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getVacDate() {
        return vacDate;
    }

    public void setVacDate(Date vacDate) {
        this.vacDate = vacDate;
    }

    public boolean getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public String getVacType() {
        return vacType;
    }

    public void setVacType(String vacType) {
        this.vacType = vacType;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", vacDate=" + vacDate +
                ", isVaccinated=" + isVaccinated +
                ", vacType='" + vacType + '\'' +
                ", centerName='" + centerName + '\'' +
                '}';
    }
}


