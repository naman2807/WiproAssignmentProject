package guibuildup;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: guibuildup
 * Project Name: WiproAssignmentProject
 * Date: 18-05-2021
 */

public class AddCandidateController {
    @FXML
    private TextField studentName;
    @FXML
    private TextField marks1;
    @FXML
    private TextField marks2;
    @FXML
    private TextField marks3;

    public void addCandidateToDatabase(){
        String name = studentName.getText();
    }
}
