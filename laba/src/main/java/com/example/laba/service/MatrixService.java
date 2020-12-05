package com.example.laba.service;

import com.example.laba.calc.CalculateMatrix;
import com.example.laba.calc.CalculateMatrixInterface;
import com.example.laba.dto.MatrixDto;
import com.example.laba.model.Matrix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MatrixService {
    private final CalculateMatrixInterface calculateMatrixInterface = new CalculateMatrix();

    public MatrixDto addition(MatrixDto firstDto, MatrixDto secondDto) {
        System.out.println(firstDto);
        System.out.println(secondDto);
        return parseMatrix(calculateMatrixInterface.matrixAddition(parseMatrixDto(firstDto), parseMatrixDto(secondDto)));
    }

    private Matrix parseMatrixDto(MatrixDto matrixDto) {
        return new Matrix(new ArrayList<ArrayList<Double>>() {
            {
                Double[] elements = matrixDto.getElements();
                for (int i = 0; i < matrixDto.getRows(); i++) {
                    ArrayList<Double> elementsList =
                            new ArrayList<Double>(Arrays.asList(elements)
                                    .subList(i * matrixDto.getRows(), matrixDto.getRows() + i * matrixDto.getRows()));
                    this.add(i, elementsList);
                }
            }
        });
    }

    private MatrixDto parseMatrix(Matrix matrix) {
        int row = matrix.getLineNumber();
        int column = matrix.getСolumnNumber();
        Double[][] elements = new Double[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                elements[i][j] = matrix.getElement(i, j);
            }
        }
        return new MatrixDto(row,column,elements);
    }
}
