import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class StudentOperations {
    public int marks =0;
    public void showQuestions(int index) throws IOException, ParseException {
        JSONArray quesArray = randomization(arrayParsing());

        JSONObject question = (JSONObject) quesArray.get(index);
        String option1 = (String) question.get("option 1");
        String option2 = (String) question.get("option 2");
        String option3 = (String) question.get("option 3");
        String option4 = (String) question.get("option 4");
        String que = (String) question.get("question");

        System.out.println("Q :"+que);
        System.out.println("Option 1: " + option1 );
        System.out.println("Option 2: " + option2 );
        System.out.println("Option 3: " + option3 );
        System.out.println("Option 4: " + option4 );
        System.out.println("Your answer = ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equals(question.get("answerkey"))){
            marks+= 1;
        }

    }

    public static JSONArray arrayParsing() throws IOException, ParseException {
        JSONArray quesArray;
        FileReader fileReader = new FileReader("quiz.json");
        if (fileReader.read() <1){
            System.out.println("Error occurred. Please try again! ");
        }
        else{
            FileReader fileReader1 = new FileReader("quiz.json");
            quesArray = (JSONArray) new JSONParser().parse(fileReader1);
            return quesArray;
        }
        return null;
    }
    public  JSONArray randomization(JSONArray arr){
        Random rnd = new Random();
        JSONObject temp= new JSONObject();
        for (int i =0;i<arr.size();i++){
            int random = rnd.nextInt(i+1);
            temp = (JSONObject) arr.get(i);
            arr.set(i, arr.get(random));
            arr.set(random, temp);

        }
        return arr;
    }
    public  static void marks(){

    }

}
