import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckFilePhoneNumbers {
    private static final String RELATIVE_PATH = "src/main/resources/file.txt";

    public void getPhoneNumber(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (line.matches("^\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}") || line.matches("^\\d{3}\\-\\d{3}\\-\\d{4}"))
                    System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        File file = new File(RELATIVE_PATH);
        CheckFilePhoneNumbers phoneNumbers = new CheckFilePhoneNumbers();
        phoneNumbers.getPhoneNumber(file);
    }

}