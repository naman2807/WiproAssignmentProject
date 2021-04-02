package com.wipro.candidate.dao;


import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;
import com.wipro.candidate.util.DataSource;
import com.wipro.candidate.util.WrongDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CandidateDAO {

    /**
     * This method add candidate to the database.
     *
     * @param studentBean
     * @return status of action. Either Success or Fail.
     * @throws WrongDataException
     */
    public String addCandidate(CandidateBean studentBean) throws WrongDataException {
        String status = "";
        if (studentBean == null) {
            throw new WrongDataException();
        } else {
            boolean result = DataSource.addCandidate(DBUtil.getDBConn(), studentBean);
            if (result) {
                status = "SUCCESS";
            } else {
                status = "FAIL";
            }
            return status;
        }

    }

    /**
     * This method returns the list of candidates based on some criteria i.e pass, fail or all types of students.
     *
     * @param criteria
     * @return List of candidates.
     * @throws SQLException
     * @throws WrongDataException
     */
    public ArrayList<CandidateBean> getByResult(String criteria) throws SQLException, WrongDataException {
        ArrayList<CandidateBean> list = new ArrayList<CandidateBean>();
        if (criteria.equalsIgnoreCase("fail") || criteria.equalsIgnoreCase("pass")) {
            ResultSet resultSet = DataSource.showCandidates(DBUtil.getDBConn(), criteria);
            return addRecordToList(list, resultSet);
        } else if (criteria.equalsIgnoreCase("all")) {
            ResultSet resultSet = DataSource.showAllCandidates(DBUtil.getDBConn());
            return addRecordToList(list, resultSet);
        } else {
            throw new WrongDataException();
        }
    }

    /**
     * adds candidates to list.
     *
     * @param list
     * @param resultSet
     * @return list of candidates
     * @throws SQLException
     */
    private ArrayList<CandidateBean> addRecordToList(ArrayList<CandidateBean> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int m1 = resultSet.getInt(3);
            int m2 = resultSet.getInt(4);
            int m3 = resultSet.getInt(5);
            String result = resultSet.getString(6);
            String grade = resultSet.getString(7);
            list.add(new CandidateBean(id, name, m1, m2, m3, result, grade));
        }
        if (list.size() != 0) {
            return list;
        }
        return null;
    }

    /**
     * This method generates id of candidate.
     *
     * @param name
     * @return id of candidate.
     */
    public String generateCandidateId(String name) {
        String id = "";
        id = name.substring(0, 2);
        return id.toUpperCase();
    }
}
