package com.example.laba.calc;

import com.example.laba.model.Matrix;

import java.util.ArrayList;

public class CalculateMatrix implements CalculateMatrixInterface {
    @Override
    public Matrix matrixAddition(Matrix first, Matrix second) {
        if ((first.getLineNumber() == second.getLineNumber()) && (first.getСolumnNumber() == second.getСolumnNumber())) {
            return new Matrix(new ArrayList<>() {
                {
                    for (int i = 0; i < first.getСolumnNumber(); i++) {
                        int finalI = i;
                        ArrayList<Double> list = new ArrayList<>();
                        for (int j = 0; j < first.getLineNumber(); j++) {
                            list.add(first.getElement(finalI, j) + second.getElement(finalI, j));
                        }
                        this.add(list);
                    }
                }
            });
        }
        return null;
    }
}