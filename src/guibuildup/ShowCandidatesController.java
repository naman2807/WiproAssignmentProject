package guibuildup;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.service.CandidateMain;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: guibuildup
 * Project Name: WiproAssignmentProject
 * Date: 20-05-2021
 */

public class ShowCandidatesController {
    @FXML
    private TableView<CandidateBean> studentTable;

    public ShowCandidatesController(){}

    public void showStudentRecord(String criteria){
        ObservableList<CandidateBean> list = CandidateMain.showCandidateList(criteria);
        studentTable.setItems(list);
    }

    public void clear(){
        studentTable.getItems().clear();
    }
}
