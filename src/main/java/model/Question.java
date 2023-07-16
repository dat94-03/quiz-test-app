package model;

import javafx.scene.image.Image;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;


public class Question {
    public int id = -1;
    public String title;
    public String category;
    public ArrayList<String> choices = new ArrayList<>();
    public String correctAnswer;
    public ArrayList<String> multipleAnswer = new ArrayList<>();

    Question(){
        this.title = "";
        this.category= "";
        this.correctAnswer = "";

    }
    public Question(int id, String category,String title,String correctAnswer,String[] choices){
        this.title = title;
        this.id = id;
        this.category = category;
        this.choices.addAll(Arrays.asList(choices));
        this.correctAnswer = correctAnswer;
    }

    public void addQuestionMedia(String picturePaths) throws IOException {
        String[] paths = picturePaths.split("\n");
        StringBuilder imagesByteData = new StringBuilder();
        int count =0;
        for (String path : paths){
            if(path.equals("null")){
                imagesByteData.append("N").append("\n");
            }else{
                Path source = Path.of(path);
                if(path.endsWith("gif")){
                    Path destinationPath = Path.of("src/main/java/data/picture/"+ this.id +"#"+ count +".gif");
                    Files.copy(source, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    imagesByteData.append("G").append("\n");
                }else if(path.endsWith("mp4")){
                    Path destinationPath = Path.of("src/main/java/data/picture/"+ this.id +"#"+ count +".mp4");
                    Files.copy(source, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    imagesByteData.append("V").append("\n");}
                else
                {
                    File image = new File(path);
                    BufferedImage img = ImageIO.read(image);
                    File out = new File("src/main/java/data/picture/"+ this.id +"#"+ count +".png");
                    ImageIO.write(img,"png",out);
                    imagesByteData.append("P").append("\n");
                }
            }
            count++;
        }
        FileInputStream databaseStream =new FileInputStream("src/main/java/data/QuizTestAppData.xlsx");
        XSSFWorkbook data = new XSSFWorkbook(databaseStream);
        Sheet questionBank = data.getSheet("QuestionBank");
        if(questionBank.getRow(this.id).getLastCellNum()==4){
            questionBank.getRow(this.id).createCell(4).setCellValue(imagesByteData.toString());
        }else {
            questionBank.getRow(this.id).getCell(4).setCellValue(imagesByteData.toString());
        }

        databaseStream.close();

        FileOutputStream storeStream = new FileOutputStream("src/main/java/data/QuizTestAppData.xlsx");
        data.write(storeStream);
        storeStream.close();

    }
    public ArrayList<QuestionMedia> getQuestionMedia() throws IOException {
        ArrayList<QuestionMedia> mediaList = new ArrayList<>();
        FileInputStream databaseStream = new FileInputStream("src/main/java/data/QuizTestAppData.xlsx");
        XSSFWorkbook data = new XSSFWorkbook(databaseStream);
        Sheet questionBank = data.getSheet("QuestionBank");
        if(questionBank.getRow(this.id).getLastCellNum()==4){
            return null;
        }

        String[] flags = questionBank.getRow(this.id).getCell(4).getStringCellValue().split("\n");
        databaseStream.close();
        int count =0;
        // mediaFile;
        Set<Object> pair;
        for (String flag : flags){
            switch (flag) {
                case "N" -> mediaList.add(null);
                case "G" -> {
                    mediaList.add(new QuestionMedia(flag,System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/" + this.id + "#" + count + ".gif"));
                }
                case "V" -> {
                    mediaList.add(new QuestionMedia(flag,System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/" + this.id + "#" + count + ".mp4"));
                }
                case "P" -> {
                    mediaList.add(new QuestionMedia(flag,System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/" + this.id + "#" + count + ".png"));

                }
            }

            count++;
        }
        return mediaList;
    }

    public ArrayList<String> getPathQuestionMedia() throws IOException {
        ArrayList<String> pathList = new ArrayList<>();
        FileInputStream databaseStream = new FileInputStream("src/main/java/data/QuizTestAppData.xlsx");
        XSSFWorkbook data = new XSSFWorkbook(databaseStream);
        Sheet questionBank = data.getSheet("QuestionBank");
        if(questionBank.getRow(this.id).getLastCellNum()==4){
            return null;
        }

        String[] flags = questionBank.getRow(this.id).getCell(4).getStringCellValue().split("\n");
        databaseStream.close();
        int count =0;
        for (String flag : flags){
            if(flag.equals("N")){
                pathList.add(null);
            }else if(flag.equals("G")){
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".gif");
                String path = imageFile.getPath();
                System.out.println(path);
                pathList.add(path);
            }else if (flag.equals("V")){
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".mp4");
                String path = imageFile.getPath();
                System.out.println(path);
                pathList.add(path);
            }else
            {
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".png");
                String path = imageFile.getPath();
                System.out.println(path);
                pathList.add(path);
            }

            count++;
        }
        return pathList;
    }

    public boolean isMultipleChoice(){
        multipleAnswer.clear();
        String[] tmp = correctAnswer.split(",");
        for(String str : tmp){
            if(str.equals("") || str == null || str.equals(" "))   continue;
            str = str.trim();
            multipleAnswer.add(str);
        }
        if(multipleAnswer.size() == 1)      return false;
        return true;
    }

    public boolean isOptionBelongMultipleAns(String option){
        for(String str : multipleAnswer){
            if(option.substring(0,1).equals(str)){
                return true;
            }
        }
        return false;
    }
}
