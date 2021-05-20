package com.wipro.candidate.service;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.DBUtil;
import com.wipro.candidate.util.WrongDataException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class CandidateMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CandidateDAO candidateDAO = new CandidateDAO();

    /**
     * This method shows instructions.
     */
    private static void instructions() {
        System.out.println("Following are the functions which can be performed with this software:\n" +
                "Press 1 to add candidate to record.\n" +
                "Press 2 to show candidates list based on result criteria.\n" +
                "Press 3 to show instructions.\n" +
                "Press 4 to exit\n");
    }

    /**
     * This method takes details of candidate from the user.
     */
    public static void addCandidateToRecord(CandidateBean candidateBean) {
        String name = candidateBean.getName();
        int m1 = candidateBean.getM1();
        int m2 = candidateBean.getM2();
        int m3 = candidateBean.getM3();
        if (name == null || name.isBlank() || name.isEmpty() || name.length() < 2 || m1 < 0 || m1 > 100
                || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
            addCandidate(null);
        } else {
            String id = candidateDAO.generateCandidateId(name);
            String result = calculateResult(m1, m2, m3);
            String grade = calculateGrade(m1, m2, m3);
            addCandidate(new CandidateBean(id, name, m1, m2, m3, result, grade));
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
    private static void addCandidate(CandidateBean studBean) {
        try {
            String status = candidateDAO.addCandidate(studBean);
            if (status.equalsIgnoreCase("fail")) {
                showAlert(Alert.AlertType.ERROR,"Error","Cannot add student to record",
                        "Please check data again." );
            } else {
                showAlert(Alert.AlertType.INFORMATION,"Confirmation",studBean.getId() + ":" + status,
                       "Added!. Click on OK to close window." );
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

    /**
     * Show list of candidates.
     */
    public static void showCandidateList(String criteria) {
        ArrayList<CandidateBean> beanArrayList = displayAll(criteria);
        if (beanArrayList == null) {
            System.err.println("No record found");
        } else {
            System.out.println(String.format("%-10s%-30s%-10s%-10s%-10s%-15s%-15s", "ID", "Name", "Marks1", "Marks2", "Marks3", "Result", "Grade"));
            beanArrayList.forEach(System.out::println);
        }
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
