package com.neuralneworks.main.datatypes;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Matrix {
    private Integer rows;
    private Integer cols;
    private ArrayList<Double> data;

    public Matrix(Integer rows, Integer cols, ArrayList<Double> data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public ArrayList<Double> getData() {
        return data;
    }

    public void setData(ArrayList<Double> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", data=" + data +
                '}';
    }
}
