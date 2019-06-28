package com.hacknchange.havelsan.agent.requests;

import java.io.Serializable;
import java.util.List;

public class TrainingDataRequest implements Serializable {
    private String answer ;
    private List<String> questionList ;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }
}
