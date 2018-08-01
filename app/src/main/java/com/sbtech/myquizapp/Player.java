package com.sbtech.myquizapp;

import android.support.annotation.NonNull;

public class Player implements Comparable<Player> {

    private String mName;
    private int mScore;

    public Player(String name, int score) {
        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "mName='" + mName + '\'' +
                ", mScore=" + mScore +
                '}';
    }

    @Override
    public int compareTo(@NonNull Player o) {
        return getScore()-o.getScore();
    }
}
