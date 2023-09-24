import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Users {
    public void createUsers() throws IOException {
        JSONArray usersArray = new JSONArray();
        //Create default users
        JSONObject userObject1 = new JSONObject();
        userObject1.put("name", "admin");
        userObject1.put("password", "12345");
        userObject1.put("role", "admin");
        usersArray.add(userObject1);

        JSONObject userObject2 = new JSONObject();
        userObject2.put("name", "Emon");
        userObject2.put("password", "1234");
        userObject2.put("role", "student");
        usersArray.add(userObject2);

        JSONObject userObject3 = new JSONObject();
        userObject3.put("name", "Salman");
        userObject3.put("password", "4321");
        userObject3.put("role", "student");
        usersArray.add(userObject3);

        FileWriter fileWriter = new FileWriter("users.json");
        fileWriter.write(usersArray.toString());
        fileWriter.flush();
        fileWriter.close();

    }
}
