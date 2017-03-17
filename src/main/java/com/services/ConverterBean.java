package com.services;

/**
 * Created by nikita on 15.02.2017.
 */
public class ConverterBean {

    int value[][];
    int recognize[][];

    public int[][] getRecognize() {
        return recognize;
    }

    public void setRecognize(int[][] recognize) {
        this.recognize = recognize;
    }

    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }
}
