package sd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Client {

	public static void showMenu()
	{
		System.out.println("################		WELCOME TO VACCINATION SYSTEM		###############");
		System.out.println("#	Insert the number that correspondes to the action you want			  #");
		System.out.println("#	1 - List/Consult Vaccination Centers								  #");
		System.out.println("#	2 - Check QueueSize for a specific existing center					  #");
		System.out.println("#	3 - Apply for Vaccination											  #");
		System.out.println("#	4 - Finish Vaccination												  #");
		System.out.println("#	5 - Report Secundary Effects										  #");
		System.out.println("#	6 - List Total of Vaccinations and SecundaryEffects per Vaccine		  #");
		System.out.println("#	-1 - Exit		  													  #");
		System.out.println("###########################################################################");
	}

    public static void main(String args[]) {
		String regHost = args[0];
		String regPort = args[1];  		//Binder port

		try {
			//objeto que fica associado ao proxy para objeto remoto
			Person person = (Person) java.rmi.Naming.lookup("rmi://" + regHost +":"+regPort +"/person");
			VaccineCenter center = (VaccineCenter) java.rmi.Naming.lookup("rmi://" + regHost +":"+regPort +"/vaccinecenter");
			BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

			//Used variables in try
			String name;
			Integer age;
			Character gender;
			Integer centerId;
			String effects;
			Integer vaccinationCode;
			String vaccineType;
			Integer switchInt=0;

			//Client Menu
			showMenu();

			//Main program while
			while(true) {
				System.out.print("Command: ");
				switchInt = Integer.parseInt(reader.readLine());

				switch (switchInt) {
					case 1:
						System.out.println("#	List Vaccination Centers	#\n");
						LinkedList<VacCenterEntity> centers = center.listAllVaccineCenters();

						for(int i=0 ; i<centers.size(); i++)
						{
							System.out.println("	Center Name: " + centers.get(i).getName());
							System.out.println("	Center Location: " + centers.get(i).getLocation());
							System.out.println("    Center ID: " + centers.get(i).getId());
							System.out.printf("\n");
						}
						break;

					case 2:
						System.out.println("Insert CenterID to check it QueueSize: ");
						centerId = Integer.parseInt(reader.readLine());
						System.out.println("Queue Size for Center X: " + center.checkVacCenterQueueSize(centerId));
						break;

					case 3:
						System.out.println("Insert your full Name:");
						name = reader.readLine();

						System.out.println("Insert your Age:");
						age = Integer.parseInt(reader.readLine());

						System.out.println("Insert your Gender:");
						System.out.println("M ->male OR F->female");
						gender = reader.readLine().charAt(0);

						center.listAllVaccineCenters();
						System.out.println("Select The CenterId that would you like to apply vaccine:");
						centerId = Integer.parseInt(reader.readLine());

						vaccinationCode = center.addPersonToVaccinationApplication(name, age, gender, centerId);
						System.out.println("Inserted to Vaccination");
						System.out.println("	VACCINATION CODE: " + vaccinationCode);
						System.out.println("	Name: " + name);
						System.out.println("	Age: " + age);
						System.out.println("	Gender: " + gender);
						System.out.println("	On Vaccination Center with Id: " + centerId);
						break;

					case 4:
						System.out.println("Insert your VaccinationCode: ");
						vaccinationCode = Integer.parseInt(reader.readLine());
						System.out.println("Insert Vaccine Name: ");
						System.out.println("Existing Vaccines: Astrazeneca, Moderna, Pfizer");
						vaccineType = reader.readLine();
						if(vaccineType.equals("Astrazeneca") || vaccineType.equals("Moderna") || vaccineType.equals("Pfizer")) {
							Integer currPersonId = center.registryFinishedVaccination(vaccinationCode, vaccineType);
							if (currPersonId != 0) {
								System.out.println("Finished Vaccination!");
							} else {
								System.out.println("Impossible to finish the process. Apply for Vacination First!!!");
							}
						}
						else
						{
							System.out.println("[Error]: Insert a correct Vaccine Name");
						}
						break;

					case 5:
						System.out.println("Insert your VaccinationCode: ");
						vaccinationCode = Integer.parseInt(reader.readLine());
						System.out.println("Insert Secundary Effects felt after vaccination: ");
						effects = reader.readLine();
						Integer code = center.reportSecundaryEffects(vaccinationCode, effects);

						if(code != 0)
						{
							System.out.println("Successfuly updated your secundary effects");
						}
						else
						{
							System.out.println("[Error]: Vaccination Code doesnt exists! Insert your correct code!");
						}
						break;

					case 6:
						List<String> vaccines = center.getAllVaccines();
						for(int i=0 ; i<vaccines.size() ; i++)
						{
							Integer totalVaccinations = center.totalVaccinations(vaccines.get(i));
							Integer totalEffects = center.totalSecEffects(vaccines.get(i));

							System.out.println("Vaccine --> " + vaccines.get(i));
							System.out.println("	Total of Vaccinations: " + totalVaccinations);
							System.out.println("	Total of People with secundary effects: " + totalEffects);
						}
						break;

					case -1:
						System.out.println("Thanks for using the system! Good Bye!");
						System.exit(0);

					default:
						System.out.println("[Error]: Insert an existing command!");
						showMenu();
						continue;
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}