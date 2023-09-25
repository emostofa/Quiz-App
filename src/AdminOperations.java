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


    public void saveQuestions(JSONObject obj) {
        JSONArray existingArray = loadExistingQuestions();

        if (existingArray == null) {
            existingArray = new JSONArray();
        }

        existingArray.add(obj);

        try (FileWriter fileWriter = new FileWriter("quiz.json")) {
            fileWriter.write(existingArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
