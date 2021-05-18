package guibuildup;

import com.wipro.candidate.util.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: guibuildup
 * Project Name: WiproAssignmentProject
 * Date: 09-05-2021
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainWindow.fxml")));
        primaryStage.setTitle("GLA University Exam Portal");
        primaryStage.setScene(new Scene(root, 1200, 500));
        primaryStage.show();
        DBUtil.connectToDatabase();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
