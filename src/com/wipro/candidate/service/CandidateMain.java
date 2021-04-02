package com.wipro.candidate.service;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.DBUtil;
import com.wipro.candidate.util.WrongDataException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class CandidateMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CandidateDAO candidateDAO = new CandidateDAO();

    public static void main(String[] args) {
        DBUtil.connectToDatabase();
        boolean isProcess = true;
        int choice;
        instructions();
        while (isProcess) {
            System.out.println("Enter Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addCandidateToRecord();
                case 2 -> showCandidateList();
                case 3 -> instructions();
                case 4 -> isProcess = false;
                default -> instructions();
            }
        }

    }

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
    private static void addCandidateToRecord() {
        System.out.println("Enter candidate's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter marks of subject 1: ");
        int m1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter marks of subject 2: ");
        int m2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter marks of subject 3: ");
        int m3 = scanner.nextInt();
        scanner.nextLine();
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
    public static void addCandidate(CandidateBean studBean) {
        try {
            String status = candidateDAO.addCandidate(studBean);
            if (status.equalsIgnoreCase("fail")) {
                System.out.println("Error");
            } else {
                System.out.println(studBean.getId() + ":" + status);
            }
        } catch (WrongDataException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Show list of candidates.
     */
    private static void showCandidateList() {
        System.out.println("Enter the criteria from the following on the basis of which you want the data of candidates:\n" +
                "PASS/FAIL/ALL");
        String criteria = scanner.nextLine();
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
    public static ArrayList<CandidateBean> displayAll(String criteria) {
        ArrayList<CandidateBean> list;
        try {
            list = candidateDAO.getByResult(criteria);
        } catch (SQLException | WrongDataException e) {
            System.out.println(e.toString());
            return null;
        }
        return list;
    }
}
