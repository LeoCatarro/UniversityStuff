package sd;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
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
import java.net.http.HttpClient;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DGS {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void showMenu () {
        System.out.println("\n");
        System.out.println("################		WELCOME TO VACCINATION SYSTEM		###############");
        System.out.println("#	Insert the number that correspondes to the action you want			  #");
        System.out.println("#	1 - Insert Vaccines for a Center in a desired Date (YYYY-MM-dd)		  #");
        System.out.println("#	2 - List people to be Vaccinated per day                         	  #");
        System.out.println("#	3 - List Number of Vaccinated people per vaccine type per day   	  #");
        System.out.println("#	-1 - Exit		  													  #");
        System.out.println("###########################################################################");
        System.out.println("\n");
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

    public void sendPost (String url)
    {
        HttpPost post = new HttpPost(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendPut (String url)
    {
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
            DGS dgs = new DGS();
            String name;
            Integer n_vaccines;
            String url;
            Integer switchInt = 0;
            String date = "";
            String s = "";



            while (true) {

                //Client Menu
                showMenu();

                System.out.print("Command: ");
                switchInt = Integer.parseInt(reader.readLine());

                switch (switchInt) {
                    case 1:

                        System.out.println("Insert Name of desired Center: \n");
                        name = reader.readLine();

                        System.out.println("Insert Number of Vaccines: \n");
                        n_vaccines = Integer.parseInt(reader.readLine());

                        System.out.println("Insert desired Date (YYYY-MM-dd): \n");
                        date = reader.readLine();

                        url = "http://www.localhost:8090/dgs/updateDateAndVaccines?" + "name=" + name + "&n_vaccines=" + n_vaccines + "&date=" + date;

                        dgs.sendPut(url);
                        
                        break;

                    case 2:

                        System.out.println("Insert desired Date (YYYY-MM-dd): \n");
                        date = reader.readLine();

                        url = "http://www.localhost:8090/dgs/getListOfPeopleToBeVaccinatedPerDay?" + "date=" + date;

                        s = dgs.sendGet(url);

                        JSONArray toBeVaccinated = JsonPath.read(s, "$..['name', 'age', 'email', 'vacType']");

                        for (int i = 0; i < toBeVaccinated.toArray().length; i++)
                        {
                            Object obj = toBeVaccinated.get(i);

                            System.out.println(obj);
                        }

                        break;

                    case 3:

                        System.out.println("Insert desired Date (YYYY-MM-dd): \n");
                        date = reader.readLine();

                        url = "http://www.localhost:8090/dgs/getVaccinatedPerDay?" + "date=" + date;

                        s = dgs.sendGet(url);

                        JSONArray vaccinatedPeople = JsonPath.read(s, "$..['name', 'age', 'email', 'vacType']");

                        for (int i = 0; i < vaccinatedPeople.toArray().length; i++)
                        {
                            Object obj = vaccinatedPeople.get(i);

                            System.out.println(obj);
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