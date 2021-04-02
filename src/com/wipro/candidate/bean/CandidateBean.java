package com.wipro.candidate.bean;

public class CandidateBean {
    private String id;
    private String name;
    private int m1;
    private int m2;
    private int m3;
    private String result;
    private String grade;

    public CandidateBean() {
    }

    public CandidateBean(String id, String name, int m1, int m2, int m3, String result, String grade) {
        this.id = id;
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.result = result;
        this.grade = grade;
    }

    public CandidateBean(String name, int m1, int m2, int m3) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getM3() {
        return m3;
    }

    public void setM3(int m3) {
        this.m3 = m3;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-30s%-10d%-10d%-10d%-15s%-15s" +
                "", getId(), getName(), getM1(), getM2(), getM3(), getResult(), getGrade());
    }
}
