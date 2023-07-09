package model;

import javafx.scene.image.Image;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;


public class Question {
    public int id = -1;
    public String title;
    public String category;
    public ArrayList<String> choices = new ArrayList<>();
    public String correctAnswer;
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

    public void addQuestionImage(String picturePaths) throws IOException {
        String[] paths = picturePaths.split("\n");
        StringBuilder imagesByteData = new StringBuilder();
        int count =0;
        for (String path : paths){
            if(path.equals("null")){
                imagesByteData.append("N").append("\n");
            }else{
                if(path.endsWith("gif")){
//                    Image img = new Image(new File(path).toURI().toString());
//                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
//                   File image = new File(path);
//                    BufferedImage img = ImageIO.read(image);
//                    File out = new File("src/main/java/data/picture/"+ this.id +"#"+ count +".gif");
//                    ImageIO.write(img,"gif",out);
                    Path sourcePath = Path.of(path);
                    Path destinationPath = Path.of("src/main/java/data/picture/"+ this.id +"#"+ count +".gif"); // Replace with the desired destination path
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    imagesByteData.append("G").append("\n");
                }else{
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
        questionBank.getRow(this.id).createCell(4).setCellValue(imagesByteData.toString());
        databaseStream.close();

        FileOutputStream storeStream = new FileOutputStream("src/main/java/data/QuizTestAppData.xlsx");
        data.write(storeStream);
        storeStream.close();

    }
    public ArrayList<Image> getQuestionImage() throws IOException {
        ArrayList<Image> imageList = new ArrayList<>();
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
                imageList.add(null);
            }else if(flag.equals("G")){
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".gif");
                imageList.add(new Image(imageFile.toURI().toString()));
            }else {
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".png");
                imageList.add(new Image(imageFile.toURI().toString()));
            }

            count++;
        }
        return imageList;
    }

    public ArrayList<String> getPathQuestionImage() throws IOException {
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
            }else {
                File imageFile = new File(System.getProperty("user.dir") + File.separator + "src/main/java/data/picture/"+ this.id +"#"+ count +".png");
                String path = imageFile.getPath();
                System.out.println(path);
                pathList.add(path);
            }

            count++;
        }
        return pathList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Question{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", choices=").append(choices);
        sb.append(", correctAnswer='").append(correctAnswer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
