package sd;

import java.io.Serializable;
import java.sql.Date;

public class PersonEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Character gender;
    private Boolean vaccinated;
    private String effects;
    private String vaccineType;
    private Integer vaccineCode;
    private Date vaccinationDate;

    public PersonEntity(Integer id, String name, Integer age, Character gender, Boolean vaccinated, String effects, String vaccineType, Integer vaccineCode, Date vaccinationDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.vaccinated = vaccinated;
        this.effects = effects;
        this.vaccineType = vaccineType;
        this.vaccineCode = vaccineCode;
        this.vaccinationDate = vaccinationDate;
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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Integer getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(Integer vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", vaccinated=" + vaccinated +
                ", effects='" + effects + '\'' +
                ", vaccineType='" + vaccineType + '\'' +
                ", vaccineCode=" + vaccineCode +
                ", vaccinationDate=" + vaccinationDate +
                '}';
    }
}
