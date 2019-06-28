package com.hacknchange.havelsan.agent.models;


import java.util.ArrayList;

public class Topic implements  Comparable<Topic> {


    private  int id;
    private String[] train;
    private String answer;
    private String tag;


    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getTrain() {
        return train;
    }

    public void setTrain(String[] train) {
        this.train = train;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int compareTo(Topic o) {
        return 0;
    }
}
