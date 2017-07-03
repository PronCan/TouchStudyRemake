package org.iptime.proncan.touchstudyremake;

/**
 * Created by 호창 on 2017-07-03.
 */

public class Question {
    public int id;
    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public int answerNum;
    public String comment;
    public int checkAnswer = 0; //1:틀린것, 0:맞은것

    public Question(String question, String answer1, String answer2, String answer3, String answer4, int answerNum, String comment) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answerNum = answerNum;
        this.comment = comment;
    }
}
