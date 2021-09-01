package sd;

import java.io.Serializable;
import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PersonImpl extends UnicastRemoteObject implements Person, Serializable {

    final static PostgresConnector connector = new PostgresConnector("localhost", "db1", "user1", "sample1");

    public PersonImpl() throws RemoteException {
    }

    @Override
    public void listAllPeople() throws Exception {
        connector.connect();
        Statement statement = connector.getStatement();

        if(statement != null) {
            ResultSet result = statement.executeQuery("SELECT person_id, name,age,gender,vaccinated,sec_effects,vaccine_type,vaccine_code,vaccination_date FROM person");
            System.out.println("[Server]: LISTING ALL PEOPLE IN PERSON DB");
            while (result.next()) {
                PersonEntity p = new PersonEntity(
                        result.getInt(1),                   //Id
                        result.getString(2),                //Name
                        result.getInt(3),                   //Age
                        result.getString(4).charAt(0),      //Gender
                        result.getBoolean(5),               //Vaccinated
                        result.getString(6),                //Secundary Effects
                        result.getString(7),                //Vaccine Type
                        result.getInt(8),                   //Vaccine Code
                        result.getDate(9)                   //Vaccination Date
                );
                System.out.println(p);
            }
        }
    }
}
