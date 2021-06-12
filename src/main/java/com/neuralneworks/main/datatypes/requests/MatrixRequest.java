package com.neuralneworks.main.datatypes.requests;

import com.neuralneworks.main.datatypes.Matrix;

public class MatrixRequest {
    private Matrix matrixA;
    private Matrix matrixB;

    public MatrixRequest(Matrix matrixA, Matrix matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }

    public Matrix getMatrixA() {
        return matrixA;
    }

    public void setMatrixA(Matrix matrixA) {
        this.matrixA = matrixA;
    }

    public Matrix getMatrixB() {
        return matrixB;
    }

    public void setMatrixB(Matrix matrixB) {
        this.matrixB = matrixB;
    }

    @Override
    public String toString() {
        return "MatrixRequest{" +
                "matrixA=" + matrixA +
                ", matrixB=" + matrixB +
                '}';
    }
}
