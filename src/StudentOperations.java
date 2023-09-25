import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StudentOperations {
    private int marks = 0;

    public void showQuestions(int index) throws IOException, ParseException {
        FileOperations fileOperations = new FileOperations();
        JSONArray quesArray = randomizeQuestions(fileOperations.quizFileToJsonArray());
        JSONObject question = (JSONObject) quesArray.get(index);
        String option1 = (String) question.get("option 1");
        String option2 = (String) question.get("option 2");
        String option3 = (String) question.get("option 3");
        String option4 = (String) question.get("option 4");
        String que = (String) question.get("question");

        System.out.println("Q: " + que);
        System.out.println("Option 1: " + option1);
        System.out.println("Option 2: " + option2);
        System.out.println("Option 3: " + option3);
        System.out.println("Option 4: " + option4);
        System.out.print("Your answer: ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();

        if (answer.equals(question.get("answerkey"))) {
            marks += 1;
        }
    }


    private JSONArray randomizeQuestions(JSONArray arr) {
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

    public int getMarks() {
        return marks;
    }
}
