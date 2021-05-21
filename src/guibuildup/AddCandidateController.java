package guibuildup;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.service.CandidateMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: guibuildup
 * Project Name: WiproAssignmentProject
 * Date: 18-05-2021
 */

public class AddCandidateController {
    @FXML
    public BorderPane addCandidateBorderPane;
    @FXML
    private TextField studentName;
    @FXML
    private TextField marks1;
    @FXML
    private TextField marks2;
    @FXML
    private TextField marks3;
    @FXML
    private Button addCandidateRecord;

    public AddCandidateController() {
    }

    public Button getAddCandidateRecord() {
        return addCandidateRecord;
    }

    public void addCandidateToDatabase(Stage stage){
        String name = studentName.getText();
        int maths = Integer.parseInt(marks1.getText());
        int physics = Integer.parseInt(marks2.getText());
        int chemistry = Integer.parseInt(marks3.getText());
        CandidateMain.addCandidateToRecord(new CandidateBean(name, maths, physics, chemistry), stage);
    }
}
