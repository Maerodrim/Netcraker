package com.example.laba.service;

import com.example.laba.calc.CalculateMatrixInterface;
import com.example.laba.dto.MatrixDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatrixService {
    private CalculateMatrixInterface calculateMatrixInterface;
    public MatrixDto addition(MatrixDto firstDto, MatrixDto secondDto)
    {
        return new MatrixDto(calculateMatrixInterface.matrixAddition(firstDto.getMatrix(),secondDto.getMatrix()));
    }
}
