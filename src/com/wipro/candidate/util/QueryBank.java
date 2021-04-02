package com.wipro.candidate.util;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: com.wipro.candidate.util
 * Project Name: WiproAssignmentProject
 * Date: 02-04-2021
 */

public class QueryBank {
    private static final String TABLE_NAME = "candidate_tbl";
    private static final String ID = "ID";
    private static final String NAME = "name";
    private static final String MARKS1 = "m1";
    private static final String MARKS2 = "m2";
    private static final String MARKS3 = "m3";
    private static final String RESULT = "result";
    private static final String GRADE = "grade";

    public static String addCandidateQuery() {
        return "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    public static String showCandidateQuery() {
        return "SELECT * FROM " + TABLE_NAME + " WHERE " + RESULT + " = ?";
    }

    public static String showAllCandidates(){
        return "SELECT * FROM " + TABLE_NAME;
    }
}
