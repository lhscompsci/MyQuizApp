package com.sbtech.myquizapp;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private String mHint;

    public Question(int textResId, boolean answerTrue, String hint){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mHint = hint;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mTextResId=" + mTextResId +
                ", mAnswerTrue=" + mAnswerTrue +
                ", mHint=" + mHint +
                '}';
    }

    public String getHint() {
        return mHint;
    }

    public void setHint(String hint) {
        mHint = hint;
    }
}
