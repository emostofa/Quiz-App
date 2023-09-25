import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {
    private static final String User_File = "users.json";
    private static final String Quiz_File = "quiz.json";

    public JSONArray userFileToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        try (FileReader fileReader = new FileReader(User_File)) {
            if (fileReader.read() < 1) {
                System.out.println("Error occurred. Please try again!");
            } else {
                FileReader fileReader1 = new FileReader(User_File);
                JSONParser jsonParser = new JSONParser();
                jsonArray = (JSONArray) jsonParser.parse(fileReader1);
                return jsonArray;
            }
            } catch (IOException | ParseException e) {
            throw new RuntimeException(e);


        }
        return jsonArray;
    }


    public JSONArray quizFileToJsonArray() throws FileNotFoundException {
        JSONArray jsonArray;
        try (FileReader fileReader = new FileReader(Quiz_File)) {
            JSONParser jsonParser = new JSONParser();
            jsonArray = (JSONArray) jsonParser.parse(fileReader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonArray;
    }


}

