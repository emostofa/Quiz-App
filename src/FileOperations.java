import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    private static final String User_File = "users.json";
    private static final String Quiz_File = "quiz.json";

    public JSONArray fileToJsonArray(String file) {
        JSONArray jsonArray = new JSONArray();
        try (FileReader fileReader = new FileReader(file)) {
            if (fileReader.read() < 1) {
                System.out.println("Error occurred. Please try again!");
            } else {
                FileReader fileReader1 = new FileReader(file);
                JSONParser jsonParser = new JSONParser();
                jsonArray = (JSONArray) jsonParser.parse(fileReader1);
                return jsonArray;
            }
            } catch (IOException | ParseException e) {
            throw new RuntimeException(e);


        }
        return jsonArray;
    }



    public void writeFile(String file_name, String info) throws IOException {
        FileWriter fileWriter = new FileWriter(file_name);
        fileWriter.write(info);
        fileWriter.flush();
        fileWriter.close();
    }



}

