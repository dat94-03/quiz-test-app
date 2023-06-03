package model;

import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AikenFormatReader {
    FileChooser filechooser = new FileChooser();

    private String filePath;

    public AikenFormatReader(String filePath) {
        this.filePath = filePath;
    }

    public void readQuestions() throws IOException {
        BufferedReader reader = null;
        int questionNumber = 1;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("ANSWER:")) {
                    String answer = line.substring(7).trim();
                    System.out.println(" Answer: " + answer);
                    questionNumber++;
                } else if (!(line.startsWith("A.") || line.startsWith("B.") || line.startsWith("C.") || line.startsWith("D.") || line.isEmpty())) {
                    System.out.println("Question " + questionNumber + ": " + line);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
