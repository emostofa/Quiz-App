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
    public AdminOperations(){

    }

    public static void saveQuestions(JSONObject Obj) throws IOException {
        JSONArray existingArray;
        try (FileReader fileReader = new FileReader("quiz.json")) {

            if (fileReader.read()>-1){
                FileReader fileReader2 = new FileReader("quiz.json");
                existingArray = (JSONArray) new JSONParser().parse(fileReader2);

            }
            else {
                existingArray = new JSONArray();

            }

            existingArray.add(Obj);
            FileWriter fileWriter = new FileWriter("quiz.json"); //remove old data
            fileWriter.write(existingArray.toJSONString()); // write old + new data
            fileWriter.flush();

        } catch (IOException | ParseException e) {
            System.out.println(e.fillInStackTrace());
        }

    }
    public static JSONObject addQuestions() throws InterruptedException {

        JSONObject questionObject = new JSONObject();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input your question: ");
        String question = scanner.nextLine();
        questionObject.put("question", question);
//        TimeUnit.SECONDS.sleep(1);
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

        System.out.println("input answer option key, eg, 1");
        String answerKey = scanner.next();
        questionObject.put("answerkey", answerKey);
        return questionObject;
    }
    public void saveInputs() throws InterruptedException, IOException {


        JSONObject ques = addQuestions();
        saveQuestions(ques);

        System.out.println("Please Wait...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Inputs saved successfully!");
    }

}

