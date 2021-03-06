package com.wipro.candidate.service;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.DBUtil;
import com.wipro.candidate.util.WrongDataException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class CandidateMain {
    private static final CandidateDAO candidateDAO = new CandidateDAO();

    /**
     * This method takes details of candidate from the user.
     */
    public static void addCandidateToRecord(CandidateBean candidateBean, Stage stage) {
        String name = candidateBean.getName();
        int m1 = candidateBean.getM1();
        int m2 = candidateBean.getM2();
        int m3 = candidateBean.getM3();
        if (name == null || name.isBlank() || name.isEmpty() || name.length() < 2 || m1 < 0 || m1 > 100
                || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
            addCandidate(null, stage);
        } else {
            String id = candidateDAO.generateCandidateId(name);
            String result = calculateResult(m1, m2, m3);
            String grade = calculateGrade(m1, m2, m3);
            addCandidate(new CandidateBean(id, name, m1, m2, m3, result, grade), stage);
        }
    }

    /**
     * This method calculates grade of candidate.
     *
     * @param m1
     * @param m2
     * @param m3
     * @return Grade of candidate
     */
    private static String calculateGrade(int m1, int m2, int m3) {
        if ((m1 + m2 + m3) >= 240) {
            return "Distinction";
        } else if ((m1 + m2 + m3) >= 180 && (m1 + m2 + m3) < 240) {
            return "First Class";
        } else if ((m1 + m2 + m3) >= 150 && (m1 + m2 + m3) < 180) {
            return "Second Class";
        } else if ((m1 + m2 + m3) >= 105 && (m1 + m2 + m3) < 150) {
            return "Third Class";
        } else {
            return "No Grade";
        }
    }

    /**
     * This method calculate result.
     *
     * @param m1
     * @param m2
     * @param m3
     * @return result
     */
    private static String calculateResult(int m1, int m2, int m3) {
        if ((m1 + m2 + m3) >= 240) {
            return "PASS";
        } else if ((m1 + m2 + m3) >= 180 && (m1 + m2 + m3) < 240) {
            return "PASS";
        } else if ((m1 + m2 + m3) >= 150 && (m1 + m2 + m3) < 180) {
            return "PASS";
        } else if ((m1 + m2 + m3) >= 105 && (m1 + m2 + m3) < 150) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    /**
     * Add candidate to database.
     *
     * @param studBean
     */
    private static void addCandidate(CandidateBean studBean, Stage stage) {
        try {
            String status = candidateDAO.addCandidate(studBean);
            if (status.equalsIgnoreCase("fail")) {
                showAlert(Alert.AlertType.ERROR,"Error","Cannot add student to record",
                        "Please check data again.", stage );
            } else {
                showAlert(Alert.AlertType.INFORMATION,"Confirmation",studBean.getId() + ":" + status,
                       "Added!. Click on OK to close window." , stage);
            }
        } catch (WrongDataException e) {
            System.out.println(e.toString());
        }

    }

    private static void showAlert(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
        }
    }

    private static void showAlert(Alert.AlertType type, String title, String headerText, String contentText, Stage stage){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
            stage.close();
        }
    }

    /**
     * Show list of candidates.
     */
    public static ObservableList<CandidateBean> showCandidateList(String criteria) {
        ObservableList<CandidateBean> beanArrayList = displayAll(criteria);
        if (beanArrayList == null) {
            showAlert(Alert.AlertType.ERROR,"Warning!", "No Data Found",
                    "Please enter some data to show records");
        }
        return beanArrayList;
    }

    /**
     * @param criteria
     * @return list of candidates.
     */
    public static ObservableList<CandidateBean> displayAll(String criteria) {
        ObservableList<CandidateBean> list;
        try {
            list = candidateDAO.getByResult(criteria);
        } catch (SQLException | WrongDataException e) {
            System.out.println(e.toString());
            return null;
        }
        return list;
    }
}
