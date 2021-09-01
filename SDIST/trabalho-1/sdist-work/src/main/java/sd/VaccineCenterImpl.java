package sd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;


public class VaccineCenterImpl extends UnicastRemoteObject implements VaccineCenter, Serializable {

    final static PostgresConnector connector = new PostgresConnector("localhost", "db1", "user1", "sample1");

    public VaccineCenterImpl() throws RemoteException {
    }

    @Override
    public LinkedList<VacCenterEntity> listAllVaccineCenters() throws Exception {
        LinkedList<VacCenterEntity> centers = new LinkedList<>();
        connector.connect();
        Statement statement = connector.getStatement();

        if(statement != null) {
            ResultSet result = statement.executeQuery("SELECT center_id,name,center_location FROM vaccenter");
            System.out.println("[Server]: Listing All Vaccine Centers");
            while (result.next()) {
                Integer centerId = result.getInt(1);
                String name = result.getString(2);
                String location = result.getString(3);

                VacCenterEntity center = new VacCenterEntity(centerId, name, location);
                centers.add(center);
                System.out.println(center);
            }
        }
        return centers;
    }

    @Override
    public Integer checkVacCenterQueueSize(Integer centerId) throws Exception {
        Integer size=0;
        ResultSet result;

        connector.connect();
        Statement statement = connector.getStatement();

        if(statement != null) {
            //Find Vaccine center by centerId passed as method argument
            result = statement.executeQuery("SELECT COUNT(center_id) AS total FROM center_queue WHERE center_id=" + centerId);
            if(result.next()) {
                size = result.getInt("total");
                System.out.println("TOTAL:"+ size);
            }
        }
        return size;
    }

    @Override
    public Integer addPersonToVaccinationApplication(String name, Integer age, Character gender, Integer centerId) throws Exception {
        Integer currPersonId=0;
        String centerName="";
        Integer personVacCode=0;

        //Established connection to database
        connector.connect();
        Statement statement = connector.getStatement();
        ResultSet rs;

        //Insert person to Person's Table
        String sql = "INSERT INTO person(name, age, gender, vaccinated, sec_effects, vaccine_type)" + "VALUES('"+ name + "','" + age + "','" + gender + "','" + false + "','" + null + "','" + null + "')";
        statement.executeUpdate(sql);

        connector.connect();

        //get current person_id
        sql = "SELECT MAX(person_id) FROM person";
        rs = statement.executeQuery(sql);
        if(rs.next()) {
            currPersonId = rs.getInt(1);
        }

        personVacCode = currPersonId;

        sql = "SELECT name FROM vaccenter WHERE vaccenter.center_id='"+ centerId +"'";
        rs = statement.executeQuery(sql);
        if(rs.next()) {
           centerName = rs.getString(1);
        }

        //Insert person to Specified Center Queue
        sql = "INSERT INTO center_queue(person_id, center_id, center_name, person_name, person_age)" + "VALUES('"+ currPersonId + "','" + centerId + "','" + centerName + "','" + name + "','" + age + "')";
        statement.executeUpdate(sql);

        System.out.println("[Server]: Inscrito para vacinação: Id: " + currPersonId + "; Name: " + name + "; Age: " + age +"; Gender: " + gender + "; Vaccination Code: " + personVacCode);
        return personVacCode;
    }

    @Override
    public Integer reportSecundaryEffects(Integer vaccinationCode, String reportEffects) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sql;
        ResultSet rs;
        Integer personId=0;

        connector.connect();
        Statement statement = connector.getStatement();

        //Get personId from vaccinationCode
        sql = "SELECT person_id FROM person WHERE person.vaccine_code='" + vaccinationCode + "'";
        rs = statement.executeQuery(sql);
        if(rs.next()) {
            personId = rs.getInt(1);
        }

        if(personId != 0) {
            //Update sec_effects column
            sql = "UPDATE person " + "SET sec_effects='" + reportEffects + "'" + " WHERE person.person_id='" + personId + "'";
            statement.executeUpdate(sql);

            System.out.println("[Server]: PersonId " + personId + " reported secundary effects");
        }
        else
        {
            System.out.println("[Server]: Error - User vaccination code does not exists!");
        }
        return personId;
    }

    @Override
    public Integer registryFinishedVaccination(Integer vaccinationCode, String vaccineType) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");      //Get current date to insert in table column
        LocalDateTime now = LocalDateTime.now();
        String sql;
        Statement statement;
        ResultSet rs;
        Integer currPersonId=0;

        connector.connect();
        statement = connector.getStatement();

        sql = "SELECT person_id FROM person WHERE person.vaccine_code='" + vaccinationCode + "'";
        rs = statement.executeQuery(sql);
        if(rs.next())
        {
            currPersonId = rs.getInt(1);
        }

        //Just finished vaccination if have been applied first
        if(currPersonId != 0) {
            //Update Vaccination Date && Vaccine Type && Check Vaccinated
            sql = "UPDATE person " + "SET vaccination_date='" + dtf.format(now) + "' ,vaccinated='" + true + "'" + ", vaccine_type='" + vaccineType + "'" + " WHERE person.vaccine_code='" + vaccinationCode + "'";
            statement.executeUpdate(sql);

            //Remove person from Center Queue by it PersonID that equals to vaccinationCode
            sql = "DELETE FROM center_queue WHERE center_queue.person_id='" + vaccinationCode + "'";
            statement.executeUpdate(sql);

            System.out.println("[Server]: User with vaccine code " + vaccinationCode + " sucessfully finished vaccination! with vaccine " + vaccineType);
            return currPersonId;
        }
        else
        {
            System.out.println("[Server]: Error - User not applied for vaccination!");
        }
        return currPersonId;
    }

    @Override
    public Integer totalVaccinations(String vaccine) throws Exception {
        Integer count = 0;
        connector.connect();
        Statement statement = connector.getStatement();
        String sql = "SELECT COUNT(person_id) FROM person WHERE person.vaccinated=true and person.vaccine_type IS NOT NULL and person.vaccine_type='" + vaccine + "'";
        ResultSet rs = statement.executeQuery(sql);

        if(rs.next())
        {
            count = rs.getInt(1);
        }

        System.out.println("[Server]: " + count + " People vaccinated with Vaccine " + vaccine);
        return count;
    }

    @Override
    public Integer totalSecEffects(String vaccine) throws Exception {
        Integer count = 0;
        connector.connect();
        Statement statement = connector.getStatement();

        String sql = "SELECT COUNT(sec_effects), vaccine_type AS total FROM person WHERE person.vaccinated=true and person.vaccine_type IS NOT NULL and person.sec_effects IS NOT NULL GROUP BY(vaccine_type)";
        ResultSet rs  = statement.executeQuery(sql);

        if(rs.next())
        {
            count = rs.getInt(1);
        }

        System.out.println("[Server]: " + count + " People had secundary effects for Vaccine " + vaccine);

        return count;
    }

    @Override
    public List<String> getAllVaccines() throws Exception {
        List<String> vaccines = new LinkedList<>();
        connector.connect();
        Statement statement = connector.getStatement();

        String sql = "SELECT DISTINCT vaccine_type FROM person WHERE person.vaccine_type!='null'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next())
        {
            vaccines.add(rs.getString(1));
        }

        return vaccines;
    }
}