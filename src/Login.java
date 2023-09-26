import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Login {
    private static String user;
    private static final String User_File = "users.json";



    public static String userRole(String name, String password) {
        FileOperations fileOperations = new FileOperations();

            JSONArray jsonArray = fileOperations.fileToJsonArray(User_File);
            for (Object obj : jsonArray) {
                JSONObject userObj = (JSONObject) obj;
                String storedName = (String) userObj.get("name");
                String storedPassword = (String) userObj.get("password");

                if (storedName.equals(name) && storedPassword.equals(password)) {
                    return (String) userObj.get("role");
                }
            }

        return "";
    }

    public void userLogin() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter your username");
        String name = scanner.next();
        System.out.println("Please Enter your password");
        String password = scanner.next();
        String role = userRole(name, password);

        if ("admin".equals(role)) {
            user = name;
            adminLogin();
        } else if ("student".equals(role)) {
            user = name;
            studentLogin();
        } else {
            System.out.println("Please input valid credentials");
        }
    }

    public static void adminLogin() throws IOException, InterruptedException {

        System.out.println("Welcome admin!");
        Thread.sleep(520);
        AdminOperations adminOperations = new AdminOperations();
        adminOperations.adminPrompts();

    }

    public static void studentLogin() throws InterruptedException, IOException {
        System.out.println("Welcome Student");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Welcome to the quiz! We will throw you 10 questions. Each MCQ mark is 1, and there is no negative marking.");
        System.out.println("You will be given 10 Minutes. Are you ready? Press 's' for start or 'q' to quit.");
        StudentOperations studentOperations = new StudentOperations();
        studentOperations.studentPrompt(user);

    }





}
