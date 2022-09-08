import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CheckFileWords {
    private static final String RELATIVE_PATH = "src/main/resources/words.txt";

    public void countWords(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line + " ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        String textWords = sb.toString();
        String[] arrayTextWords = textWords.replaceAll("\\s+", " ").split(" ");
        String wordsNotRepeat = "";

        for (int i = 0; i < arrayTextWords.length; i++) {
            if (!wordsNotRepeat.contains(arrayTextWords[i])) {
                wordsNotRepeat += arrayTextWords[i] + " ";
            }
        }
        StringBuilder builder = new StringBuilder();
        String[] arrayWordsNotRepeat = wordsNotRepeat.trim().split(" ");
        for (int i = 0; i < arrayWordsNotRepeat.length; i++) {
            int countRepeatWord = 0;
            for (int j = 0; j < arrayTextWords.length; j++) {
                if (arrayWordsNotRepeat[i].equals(arrayTextWords[j])) {
                    countRepeatWord++;
                }
            }
            builder.append(arrayWordsNotRepeat[i] + " " + countRepeatWord + "\n");
        }
        String countWordsInText = builder.toString();
        String[] arr = countWordsInText.split("\n");

        for (int i = 0; i < arr.length - 1; i++) {
            int countFirst = Character.getNumericValue(arr[i].charAt(arr[i].length() - 1));
            int countNext = Character.getNumericValue(arr[i + 1].charAt(arr[i + 1].length() - 1));
            if (countFirst < countNext) {
                String temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        File file = new File(RELATIVE_PATH);
        CheckFileWords word = new CheckFileWords();
        word.countWords(file);

    }

}