package sd;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe que ativa o serviço via RMI.
 * 
 * @author jsaias
 */
public class Server {

	public static void addVacCentersToDataBase(PostgresConnector dbConnection) throws Exception {
		int size=0;
		dbConnection.connect();
		Statement statement = dbConnection.getStatement();

		//Find Vaccine center by centerId passed as method argument
		ResultSet result = statement.executeQuery("SELECT COUNT(center_id) AS total FROM vaccenter");
		while (result.next()) {
			size = result.getInt("total");
			System.out.println("[Server]: Number of Vaccination Centers: " + size);
		}

		if (size == 0) {
			String sql = "INSERT INTO vaccenter(name, center_location) VALUES('Cento Eborense', 'Evora')";
			statement.executeUpdate(sql);

			sql = "INSERT INTO vaccenter(name, center_location) VALUES('Cento Lisboeta', 'Lisboa')";
			statement.executeUpdate(sql);

			sql = "INSERT INTO vaccenter(name, center_location) VALUES('Cento do Porto', 'Porto')";
			statement.executeUpdate(sql);

			sql = "INSERT INTO vaccenter(name, center_location) VALUES('Cento Algarvio', 'Algarve')";
			statement.executeUpdate(sql);

			System.out.println("[Server]: Inserted 4 VacCenters to VacCenter Table");
		}
		else
		{
			while (result.next()) {
				size = result.getInt("total");
				System.out.println("TOTAL:" + size);
			}
		}
	}

	public static void main(String args[]) {
		try {
			String host = args[0];
			int regPort = Integer.parseInt(args[1]);

			PostgresConnector dbConnection = new PostgresConnector("localhost", "db1", "user1", "sample1");

			//Remote Objets Declaration
			Person person = new PersonImpl();
			VaccineCenter center = new VaccineCenterImpl();

			java.rmi.registry.LocateRegistry.createRegistry(regPort);
			java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

			// Bind. Args: nome do serviço e objeto remoto
			registry.rebind("person", person);
			registry.rebind("vaccinecenter", center);

			System.out.println("[Server]: RMI object bound to service in registry");
			System.out.println("[Server]: SERVER READY");

			//If Vaccination Centers Database if empty add some to it
			addVacCentersToDataBase(dbConnection);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
