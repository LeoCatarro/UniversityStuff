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
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

public class Center {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void showMenuLoginAndRegister () {

        System.out.println("\n");
        System.out.println("################		WELCOME TO VACCINATION SYSTEM		###############");
        System.out.println("#	Insert the number that correspondes to the action you want             ");
        System.out.println("#	1 - Register Center                                                    ");
        System.out.println("#	2 - Login Center                                                       ");
        System.out.println("#   -1 - Exit                                                              ");
        System.out.println("###########################################################################");
        System.out.println("\n");
    }

    public static void showMenu () {

        System.out.println("\n");
        System.out.println("################		WELCOME TO VACCINATION SYSTEM		###############");
        System.out.println("#	Insert the number that correspondes to the action you want             ");
        System.out.println("#	1 - Apply Vaccine                                                      ");
        System.out.println("#	2 - Send Vaccinated List to DGS                                        ");
        System.out.println("#   -1 - Exit                                                              ");
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

    public void sendPost (String url) {
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
            Center center = new Center();
            String name = "";
            String location;
            Integer n_vaccines;
            Integer age;
            String email;
            String url;
            Integer switchInt = 0;
            String date = "";
            String s = "";
            boolean login = false;

            while (login != true) {

                showMenuLoginAndRegister();

                System.out.print("Command: ");
                switchInt = Integer.parseInt(reader.readLine());

                switch (switchInt) {
                    case 1:
                        System.out.println("Insert Center name: \n");
                        name = reader.readLine();

                        System.out.println("Insert Center Location: \n");
                        location = reader.readLine();

                        System.out.println("Maximum number of Vaccines per day: \n");
                        n_vaccines = Integer.parseInt(reader.readLine());

                        url = "http://www.localhost:8090/center/addCenter?" + "name=" + name + "&location=" + location + "&n_vaccines=" + n_vaccines;

                        center.sendPost(url);

                        break;

                    case 2:
                        System.out.println("Insert Center : \n");
                        name = reader.readLine();

                        url = "http://www.localhost:8090/center/getCenter?" + "name=" + name;

                        s = center.sendGet(url);

                        JSONArray arr = JsonPath.read(s, "$.[?(@['name'])]['name']");

                        while (arr.toArray().length == 0)
                        {
                            System.out.println("Invalid Center Name, please check spelling or Register your Center!\n");

                            System.out.println("Insert Center : \n");
                            name = reader.readLine();

                            url = "http://www.localhost:8090/center/getCenter?" + "name=" + name;

                            s = center.sendGet(url);

                            arr = JsonPath.read(s, "$.[?(@['name'])]['name']");

                            if (arr.toArray().length != 0)
                            {
                                if (arr.get(0).toString().equals(name))
                                {
                                    login = true;
                                    break;
                                }
                            }
                        }

                        if (arr.get(0).toString().equals(name))
                        {
                            login = true;
                            break;
                        }

                        break;

                    case -1:
                        System.out.println("Thanks for using the system! Good Bye!");
                        System.exit(0);

                    default:
                        System.out.println("[Error]: Insert an existing command!");
                        showMenuLoginAndRegister();
                        continue;

                }
            }

            while (login) {

                showMenu();

                System.out.print("Command: ");
                switchInt = Integer.parseInt(reader.readLine());

                switch (switchInt) {
                    case 1:
                        System.out.println("CENTRO " + name + "\n");

                        System.out.println("Insert Email : \n");
                        email = reader.readLine();


                        url = "http://www.localhost:8090/center/applyVaccine?" + "email=" + email;

                        center.sendPut(url);
                        break;

                    case 2:
                        System.out.println("CENTRO " + name + "\n");

                        url = "http://www.localhost:8090/center/getVaccinated?" + "name=" + name;

                        s = center.sendGet(url);

                        JSONArray arr = JsonPath.read(s, "$..['name', 'age', 'email', 'vacDate', 'vacType']");


                        for (int i = 0; i < arr.toArray().length; i++)
                        {
                            Object obj = arr.get(i);

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