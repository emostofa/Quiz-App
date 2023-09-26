import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AdminOperations {
    private static final String Quiz_File = "quiz.json";
    private static final String Mark_File = "marks.json";

    public void saveQuestions(JSONObject obj) throws IOException {
        JSONArray existingArray = loadExistingQuestions();

        if (existingArray == null) {
            existingArray = new JSONArray();
        }
        existingArray.add(obj);

       FileOperations fileOperations = new FileOperations();
       fileOperations.writeFile("quiz.json", existingArray.toJSONString());
    }

    public JSONObject addQuestions() {
        JSONObject questionObject = new JSONObject();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input your question: ");
        String question = scanner.nextLine();
        questionObject.put("question", question);

        System.out.println("Input Option 1");
        String option1 = scanner.nextLine();
        questionObject.put("option 1", option1);

        System.out.println("Input Option 2");
        String option2 = scanner.nextLine();
        questionObject.put("option 2", option2);

        System.out.println("Input Option 3");
        String option3 = scanner.nextLine();
        questionObject.put("option 3", option3);

        System.out.println("Input Option 4");
        String option4 = scanner.nextLine();
        questionObject.put("option 4", option4);

        System.out.println("Input answer option key, e.g., 1");
        String answerKey = scanner.next();
        questionObject.put("answerkey", answerKey);

        return questionObject;
    }

    public void saveInputs(JSONObject ques) throws InterruptedException, IOException {

        saveQuestions(ques);

        System.out.println("Please Wait...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Inputs saved successfully!");
    }

    private JSONArray loadExistingQuestions() {
        try (FileReader fileReader = new FileReader("quiz.json")) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);

            if (obj instanceof JSONArray) {
                return (JSONArray) obj;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void adminPrompts() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose any one and enter: ");
        System.out.println("1. Add question to question bank ");
        System.out.println("2. View all the questions");
//        System.out.println("3. Update existing questions ");
        System.out.println("3. View the marks of students");


        Thread.sleep(1000);
        int input = scanner.nextInt();
        if (!(input == 1 || input == 2 || input == 3 )){
            System.out.println("Wrong input! Please try again");
//            break;
        }
        switch (input) {
            case 1:
                addInputQues();
                break;
            case 2:
                showQuestions();
                System.out.println();
                break;
            case 3:
                showMarks();
                break;
            default:
                System.out.println("Please enter a valid input!");

        }
    }
    public static void addInputQues() throws IOException, InterruptedException {
        System.out.println("Please create new questions in the question bank.");
        Scanner scanner = new Scanner(System.in);
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


    public static void showQuestions() throws  InterruptedException {
        FileOperations fileOperations = new FileOperations();
        JSONArray questionArray = fileOperations.fileToJsonArray(Quiz_File);
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
    public static void showMarks() {
        FileOperations fileOperations = new FileOperations();
        JSONArray marksArr = fileOperations.fileToJsonArray(Mark_File);
        System.out.println(marksArr.toJSONString());

    }

}
