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

    public static String userRole(String name, String password) throws IOException{
        FileOperations fileOperations = new FileOperations();

            JSONArray jsonArray = fileOperations.userFileToJsonArray();
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

    public void userLogin() throws IOException, ParseException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter your username");
        String name = scanner.next();
        System.out.println("Please Enter your password");
        String password = scanner.next();
        String role = userRole(name, password);

        if ("admin".equals(role)) {
            adminLogin();
        } else if ("student".equals(role)) {
            studentLogin();
        } else {
            System.out.println("Please input valid credentials");
        }
    }

    public static void adminLogin() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome admin!");
        Thread.sleep(520);
        System.out.println("Choose any one and enter: ");
        System.out.println("1. Add question to question bank ");
        System.out.println("2. View all the questions");
        System.out.println("3. Update existing questions ");
        System.out.println("4. View the marks of students");


        Thread.sleep(1000);

        switch (scanner.nextInt()){
            case 1: addQuestions();
            case 2: showQuestions();

        }


    }

    public static void studentLogin() throws IOException, ParseException, InterruptedException {
        System.out.println("Welcome Student");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Welcome to the quiz! We will throw you 10 questions. Each MCQ mark is 1, and there is no negative marking.");
        System.out.println("You will be given 10 Minutes. Are you ready? Press 's' for start or 'q' to quit.");

        StudentOperations studentOperations = new StudentOperations();

        do {
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.next();
            int i = 10;

            if ("s".equals(confirmation)) {
                while (i > 0) {
                    studentOperations.showQuestions(i);
                    i--;
                }
                System.out.println("You got " + studentOperations.getMarks() + " numbers out of 10");
                break;
            } else if ("q".equals(confirmation)) {
                break;
            }
        } while (true);
    }

    public static void addQuestions() throws IOException, InterruptedException {
        System.out.println("Please create new questions in the question bank.");
        Scanner scanner = new Scanner(System.in); // Move this outside the loop
        AdminOperations operations = new AdminOperations();

        do {
            JSONObject ques = operations.addQuestions();

            System.out.println("Enter 'y' to confirm and 'n' to discard");
            String confirmation = scanner.next();
            if ("y".equals(confirmation)) {
                operations.saveInputs(ques);
            } else {
                System.out.println("Inputs discarded");
            }

            System.out.println("Do you want to add more questions? Please enter 'y' to add and 'q' to quit");

            scanner.nextLine();

            String continueInput = scanner.nextLine();
            if (continueInput.equals("q")) {
                break;
            }
        } while (true);

        scanner.close();
    }


    public static void showQuestions() throws FileNotFoundException, InterruptedException {
        FileOperations fileOperations = new FileOperations();
        JSONArray questionArray = fileOperations.quizFileToJsonArray();
        System.out.println("Total questions: "+questionArray.size());
        System.out.println("The questions are:- ");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Loading... Please wait");
        TimeUnit.SECONDS.sleep(1);
        for (Object obj : questionArray ){
            JSONObject jsonObject = (JSONObject) obj;

            System.out.println(jsonObject.get("question"));
        }
    }
}
