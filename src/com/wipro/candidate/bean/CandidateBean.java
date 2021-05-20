package com.wipro.candidate.bean;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CandidateBean {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty m1;
    private SimpleIntegerProperty m2;
    private SimpleIntegerProperty m3;
    private SimpleStringProperty result;
    private SimpleStringProperty grade;

    public CandidateBean() {
    }

    public CandidateBean(String id, String name, int m1, int m2, int m3, String result, String grade) {
        this.id.set(id);
        this.name.set(name);
        this.m1.set(m1);
        this.m2.set(m2);
        this.m3.set(m3);
        this.result.set(result);
        this.grade.set(grade);
    }

    public CandidateBean(String name, int m1, int m2, int m3) {
        this.name.set(name);
        this.m1.set(m1);
        this.m2.set(m2);
        this.m3.set(m3);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getM1() {
        return m1.get();
    }

    public SimpleIntegerProperty m1Property() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1.set(m1);
    }

    public int getM2() {
        return m2.get();
    }

    public SimpleIntegerProperty m2Property() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2.set(m2);
    }

    public int getM3() {
        return m3.get();
    }

    public SimpleIntegerProperty m3Property() {
        return m3;
    }

    public void setM3(int m3) {
        this.m3.set(m3);
    }

    public String getResult() {
        return result.get();
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    @Override
    public String toString() {
        return String.format("%-10s%-30s%-10d%-10d%-10d%-15s%-15s" +
                "", getId(), getName(), getM1(), getM2(), getM3(), getResult(), getGrade());
    }
}
