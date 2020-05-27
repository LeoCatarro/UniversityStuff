import java.util.Scanner;

class BicycleRegistration {

 public static void main(String[] args) {
   
  Scanner input = new Scanner(System.in);
  
  Bicycle bike = new Bicycle();
  
  System.out.print("Register bike to who? ");
  String owner_name = input.nextLine();
    
  bike.setName(owner_name);
  
  String id = owner_name.substring(0, 1) + owner_name.substring(owner_name.indexOf(' ')+1, owner_name.indexOf(' ')+2);
  bike.setID(id);

  String ownerName = bike.getName();

  String ownerID = bike.getID();

  System.out.println(ownerName + " owns a bicycle with the ID: " + ownerID);
 }
}