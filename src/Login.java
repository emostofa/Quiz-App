import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Login {
    public static String userRole(String name, String password) throws IOException, ParseException {
        String role = "";
        JSONParser jsonParser = new JSONParser();
        Object obj =  (jsonParser.parse(new FileReader("users.json")));
        JSONArray jsonArray = (JSONArray) obj;
        for (Object obj1 : jsonArray) {
            JSONObject usObj = (JSONObject) obj1;
            if (usObj.get("name").equals(name) && usObj.get("password").equals(password)){
                role = (String) usObj.get("role");
            }
        }
        return role;
    }

    public  void userLogin() throws IOException, ParseException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter your username");
        String name = scanner.next();
        System.out.println("Please Enter your password");
        String password = scanner.next();
        String role = userRole(name, password); // finds the user role

        if (role.equals("admin")) {
            Thread.sleep(520);
            System.out.println("Welcome admin!");
            Thread.sleep(1400);
            System.out.println("Please create new questions in the question bank.");
            Thread.sleep(1000);
            adminLogin(); // calling the admin panel

        }
        else if (role.equals("student")){
            System.out.println("Welcome Student");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Welcome to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking.You will be given 10 Minutes. Are you ready? Press 's' for start or 'q' to quit.");
            studentLogin();

        }
        else {
            System.out.println("Please input valid credentials");
        }
    }

    public static void adminLogin() throws IOException, InterruptedException {
        AdminOperations operations = new AdminOperations();

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter 'y' toConfirm and 'n' to discard");
            String confirmation = scanner.next();
            if (confirmation.equals("y")){
               operations.saveInputs();
            }
            else {
                System.out.println("Inputs discarded");
            }

            System.out.println("Do you want to add more questions? Please enter 'y' to add and 'q' to quit");
            String continueInput =scanner.next();

            if (continueInput.equals("q")){
                break;
            }

        }while (true);

    }

    public static void studentLogin() throws IOException, ParseException {
        StudentOperations studentOperations = new StudentOperations();

        do {
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.next();
            int i =10;
            if (confirmation.equals("s")) {
                while (i>0) {
                    studentOperations.showQuestions(i);
                    i--;
                }
                System.out.println("You got "+studentOperations.marks+" numbers out of 10");
                break;
            }
           else if (confirmation.equals("q")){
               break;
            }
        }
        while (true);


    }
    }

