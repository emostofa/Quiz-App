import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StudentOperations {
    private static int marks = 0;

    private static final String Quiz_File = "quiz.json";

    public static void showQuestions(int index)  {
        FileOperations fileOperations = new FileOperations();
        JSONArray quesArray = randomizeQuestions(fileOperations.fileToJsonArray(Quiz_File));
        JSONObject question = (JSONObject) quesArray.get(index);
        String option1 = (String) question.get("option 1");
        String option2 = (String) question.get("option 2");
        String option3 = (String) question.get("option 3");
        String option4 = (String) question.get("option 4");
        String que = (String) question.get("question");

        System.out.println("Q "+index+":" + que);
        System.out.println("Option 1: " + option1);
        System.out.println("Option 2: " + option2);
        System.out.println("Option 3: " + option3);
        System.out.println("Option 4: " + option4);
        System.out.print("Your answer: ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals(question.get("answerkey"))) {
            marks += 1;
        }
    }


    private static JSONArray randomizeQuestions(JSONArray arr) {
        Random rnd = new Random();

        for (int i = arr.size() - 1; i > 0; i--) {
            int randomIndex = rnd.nextInt(i + 1);

            // Swap the elements at i and randomIndex
            Object temp = arr.get(i);
            arr.set(i, arr.get(randomIndex));
            arr.set(randomIndex, temp);
        }

        return arr;
    }
    public  void studentPrompt(String user) throws IOException {
        do {
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.next();
            int i = 10;

            if ("s".equals(confirmation)) {
                while (i > 0) {
                    showQuestions(i);
                    i--;
                }

            } else if ("q".equals(confirmation)) {
                break;
            }

            System.out.println("Would you like to start again? press 's' to start and 'q' to quit! ");
            studentMarks(user);
            marks =0;
        } while (true);
    }
    public static void studentMarks(String user) throws IOException {

        FileOperations fileOperations = new FileOperations();
        JSONObject userMark = new JSONObject();
        userMark.put("mark",String.valueOf(marks));
        userMark.put("name", user);
        JSONArray markArr = new JSONArray();
        markArr.add(userMark);
        fileOperations.writeFile("marks.json", markArr.toJSONString());
        if (marks>=8){
            System.out.println("Excellent! You have got "+marks+" out of 10");
        } else if (marks>=5) {
            System.out.println("Good. You have got "+marks+" out of 10");
        } else if (marks>=2) {
            System.out.println("Very poor. You have got "+marks+" out of 10");
        }
        else {
            System.out.println("Sorry, you have failed. You have got "+marks+" out of 10");
        }

    }

}
