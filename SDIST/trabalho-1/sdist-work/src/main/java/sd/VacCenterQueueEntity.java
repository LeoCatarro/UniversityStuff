package sd;

public class VacCenterQueueEntity {
    private Integer personId;
    private Integer centerId;
    private String personName;
    private String centerName;
    private String personVaccineType;

    public VacCenterQueueEntity(Integer personId, Integer centerId, String personName, String centerName, String personVaccineType) {
        this.personId = personId;
        this.centerId = centerId;
        this.personName = personName;
        this.centerName = centerName;
        this.personVaccineType = personVaccineType;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getPersonVaccineType() {
        return personVaccineType;
    }

    public void setPersonVaccineType(String personVaccineType) {
        this.personVaccineType = personVaccineType;
    }

    @Override
    public String toString() {
        return "VacCenterQueueEntity{" +
                "personId=" + personId +
                ", centerId=" + centerId +
                ", personName='" + personName + '\'' +
                ", centerName='" + centerName + '\'' +
                ", personVaccineType='" + personVaccineType + '\'' +
                '}';
    }
}
