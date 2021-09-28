package sd;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.jayway.jsonpath.JsonPath;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

public class Client {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void showMenu() {
        System.out.println("################		WELCOME TO VACCINATION SYSTEM		###############");
        System.out.println("#	Insert the number that correspondes to the action you want			  #");
        System.out.println("#	1 - List/Consult Vaccination Centers								  #");
        System.out.println("#	2 - Login and Check for Notifications                                 #");
        System.out.println("#	3 - Register And Apply for Vaccination                            	  #");
        System.out.println("#	-1 - Exit		  													  #");
        System.out.println("###########################################################################");
    }


    public String sendGet(String url) {

        HttpGet request = new HttpGet(url);
        String result = "";

        try (CloseableHttpResponse response = httpClient.execute(request)) {


            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String sendPost(String url) {
        HttpPost post = new HttpPost(url);
        String s = "";

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            s = EntityUtils.toString(response.getEntity());

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;

    }

    public void sendPut(String url) {
        HttpPut put = new HttpPut(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(put)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            //Used variables in try
            Client client = new Client();
            String name;
            String center;
            Integer age;
            String email;
            String url;
            Integer switchInt = 0;
            String date = "";
            String s = "";
            Object obj = new Object();

            while (true) {

                //Client Menu
                showMenu();

                ArrayList<String> centers = new ArrayList<>();

                System.out.print("Command: ");
                switchInt = Integer.parseInt(reader.readLine());

                switch (switchInt) {

                    case 1:
                        System.out.println("\n");
                        System.out.println("#	List Vaccination Centers	#\n");

                        url = "http://www.localhost:8090/center/getAllCenters";

                        s = client.sendGet(url);

                        JSONArray arr = JsonPath.read(s, "$.[?(@['name'])]['name']");


                        for (int i = 0; i < arr.toArray().length; i++) {
                            obj = arr.get(i);

                            if (!centers.contains(obj.toString()))
                                centers.add(obj.toString());
                        }

                        System.out.println(centers);

                        break;

                    case 2:
                        System.out.println("\n");
                        System.out.println("Insert your email for Login please: ");
                        email = reader.readLine();

                        url = "http://www.localhost:8090/person/getPerson?" + "email=" + email;

                        s = client.sendGet(url);

                        while (!s.equals("true")) {
                            System.out.println("Email non Existant!\n");
                            System.out.println("Insert your email for Login please: ");
                            email = reader.readLine();

                            url = "http://www.localhost:8090/person/getPerson?" + "email=" + email;

                            s = client.sendGet(url);
                        }

                        url = "http://www.localhost:8090/notifications/getNotification?" + "email=" + email;

                        s = client.sendGet(url);


                        if (!s.equals("")) {

                            if (!s.contains("Your Vaccination Date has been re-scheduled to the following date:"))
                            {
                                obj = JsonPath.read(s, "$.[?(@['message'])]['message']");

                            }
                        }

                        System.out.println("\n");

                        if (!obj.toString().equals("[\"Email not found!\\n\"]"))
                        {
                            System.out.println("YOU HAVE A NOTIFICATION: \n");
                            System.out.println(s);
                        }
                        else
                        {
                            System.out.println("YOU HAVE NO NOTIFICATIONS!\n");
                        }

                        break;

                    case 3:
                        System.out.println("\n");
                        System.out.println("Insert your full Name:");
                        name = reader.readLine();

                        System.out.println("Insert your Age:");
                        age = Integer.parseInt(reader.readLine());

                        System.out.println("Insert your email:");
                        email = reader.readLine();

                        System.out.println("Insert desired Center for Vaccination:");
                        center = reader.readLine();

                        System.out.println("Insert Vaccination Date:");
                        date = reader.readLine();

                        url = "http://www.localhost:8090/person/insertPerson?" + "name=" + name + "&age=" + age + "&email=" + email + "&date=" + date + "&centerName=" + center;

                        s = client.sendPost(url);

                        //Date is not OK to insert
                        if (!s.equals(""))
                        {
                            obj = JsonPath.read(s, "$.[?(@['message'])]['message']");


                            if (obj.toString().equals("[\"This day is already full cannot make an appointment, choose another date\"]")) {
                                while (obj.toString().equals("[\"This day is already full cannot make an appointment, choose another date\"]")) {

                                    System.out.printf("This day is already full cannot make an appointment, choose another date\n");
                                    date = reader.readLine();

                                    url = "http://www.localhost:8090/person/insertPerson?" + "name=" + name + "&age=" + age + "&email=" + email + "&date=" + date + "&centerName=" + center;

                                    s = client.sendPost(url);

                                    //Date is not OK to insert
                                    if (!s.equals(""))
                                        obj = JsonPath.read(s, "$.[?(@['message'])]['message']");
                                    else {

                                        url = "http://www.localhost:8090/center/insertPersonIntoCenter?" + "name=" + center + "&email=" + email + "&date=" + date;
                                        client.sendPost(url);
                                        break;
                                    }
                                }

                            }
                        }
                        else
                        {
                            url = "http://www.localhost:8090/center/insertPersonIntoCenter?" + "name=" + center + "&email=" + email + "&date=" + date;
                            client.sendPost(url);
                            break;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}