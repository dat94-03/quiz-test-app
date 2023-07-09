package javaFx.bundle.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class QuizTestApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizTestApplication.class.getResource("Home.fxml"));

        Image image = new Image(getClass().getResource("/Img.img/moodle.jpg").toExternalForm());
        stage.setTitle("Quiz Test App");
        Scene scene = new Scene(fxmlLoader.load(), 960, 760 );
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) throws IOException, InvalidFormatException {

//
        QuestionManage qm = new QuestionManage();


//        QuestionManage.questionsList.get(10).addQuestionImage("1\n2\n3\n4\n5\n");
        ////////add thêm ảnh vào câu nào thì gọi hàm bên trên ở object của câu hỏi đó tham số là đường dẫn đến ảnh
        //nếu mà phaanf nào không có ảnh thì để null cách nhau bởi \n
        // vị trí 1 trên kia sẽ là ảnh của title, sau đấy là ảnh của choices, có bao nhiêu choices thì phải có ngần đấy path
        // nếu k có thì bắt buộc thay thế bằng null
        //truyền số linh tinh như treen kia là lỗi đấy
        //ví dụ câu hỏi có 4 choices thì phải có 5 path như câu trên kia, k cos thì để null
        ////////ArrayList<Image> img = QuestionManage.questionsList.get(10).getQuestionImage();
        //còn hàm này thì quá đơn giản gọi phương thưc ở question nào thì nó sẽ trả về danh sách ảnh tương ứng của question đấy
        //nếu mà câu hỏi đấy chưa được set ảnh bao giờ thì sẽ trả về 1 giá trị duy nhất là null
        //nhưng nếu có ít nhất 1 ảnh được thêm vào rồi thì sẽ trả về 1 mảng
        //trong mảng đấy thứ tự các ảnh giống như thứ tự lúc goij hàm bên trên kia
        //nếu chỗ nào không có ảnh thì phần tử tương ứng trân mảng đấy sẽ là null
        //ví dụ <null,ảnh nè,null,>
        //lưu ý phần tử null trong mảng và giá trị null duy nhất trả về khi question chưa hề được add ảnh là khác nhau
        //là sinh viên bách khoa cái gì mà mình có theer tư duy 1 chút mà có câu trả lời thì không nên đi hỏ

        launch();

//        QuestionManage.questionsList.get(0).addQuestionImage("\"E:\\btl oop\\Câu hỏi có ảnh\\1.jpg\"\nnull" +
//                "\nnull\nnull\nnull");

    }
}