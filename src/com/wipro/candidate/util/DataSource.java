package com.wipro.candidate.util;

import com.wipro.candidate.bean.CandidateBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: com.wipro.candidate.util
 * Project Name: WiproAssignmentProject
 * Date: 02-04-2021
 */

public class DataSource {

    /**
     * @param connection
     * @param candidate
     * @return result of addition of candidate.
     */
    public static boolean addCandidate(Connection connection, CandidateBean candidate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryBank.addCandidateQuery());
            preparedStatement.setString(1, candidate.getId());
            preparedStatement.setString(2, candidate.getName());
            preparedStatement.setInt(3, candidate.getM1());
            preparedStatement.setInt(4, candidate.getM2());
            preparedStatement.setInt(5, candidate.getM3());
            preparedStatement.setString(6, candidate.getResult());
            preparedStatement.setString(7, candidate.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param connection
     * @param criteria
     * @return ResultSet of all candidates based on either pass or fail criteria.
     */
    public static ResultSet showCandidates(Connection connection, String criteria) {
        ResultSet result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryBank.showCandidateQuery());
            preparedStatement.setString(1, criteria);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param connection
     * @return ResultSet of all candidates.
     */
    public static ResultSet showAllCandidates(Connection connection) {
        ResultSet result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryBank.showAllCandidates());
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
