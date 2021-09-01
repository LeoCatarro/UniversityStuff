package sd;


import java.util.LinkedList;
import java.util.List;

public interface VaccineCenter extends java.rmi.Remote{

    LinkedList<VacCenterEntity> listAllVaccineCenters() throws Exception;

    Integer checkVacCenterQueueSize(Integer centerId) throws Exception;

    Integer addPersonToVaccinationApplication(String name, Integer age, Character gender, Integer centerId) throws Exception;

    Integer reportSecundaryEffects(Integer vaccinationCode, String reportEffects) throws Exception;

    Integer registryFinishedVaccination(Integer vaccinationCode, String vaccineType) throws Exception;

    Integer totalVaccinations(String vaccine) throws Exception;

    Integer totalSecEffects(String vaccine) throws Exception;

    List<String> getAllVaccines() throws Exception;
}
