package guibuildup;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.service.CandidateMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> showCandidateRecord;
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
        AddCandidateController candidateController = fxmlLoader.getController();
        stage.show();
        candidateController.getAddCandidateRecord().setOnAction(event -> candidateController.addCandidateToDatabase(stage));
    }

    public void showCandidateRecord() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showCandidates.fxml"));
        Parent root = loader.load();
        stage.setTitle("Student Data");
        stage.initOwner(mainWindowController.getScene().getWindow());
        stage.setScene(new Scene(root));
        String criteria = showCandidateRecord.getSelectionModel().getSelectedItem();
        ShowCandidatesController showCandidatesController = loader.getController();
        ObservableList<CandidateBean> list = CandidateMain.showCandidateList(criteria);
        if(list != null){
            showCandidatesController.showStudentRecord(list);
            stage.show();
            stage.setOnCloseRequest(windowEvent -> showCandidatesController.clear());
        }
    }

    public void closeSoftware(){

    }
}
