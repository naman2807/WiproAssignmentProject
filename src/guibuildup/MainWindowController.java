package guibuildup;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: guibuildup
 * Project Name: WiproAssignmentProject
 * Date: 09-05-2021
 */

public class MainWindowController {
    @FXML
    private Button addCandidate;
    @FXML
    private ComboBox<FXCollections> showCandidateRecord;
    @FXML
    private Button exit;
    @FXML
    private BorderPane mainWindowController;

    public void setAddCandidate() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addCandidate.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Add Candidate");
        stage.initOwner(mainWindowController.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showCandidateRecord(){
        Stage stage = new Stage();
    }
}
