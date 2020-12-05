package com.example.laba.controller;

import com.example.laba.dto.MatrixDto;
import com.example.laba.service.MatrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("MatrixController")
@RequiredArgsConstructor
//localhost:8080/MatrixController/addition?...
public class MatrixController {
    private final MatrixService matrixService;

    @PostMapping("addition")
    public MatrixDto additionMatrix(@RequestBody MatrixDto[] dto) {
        System.out.println(Arrays.toString(dto));
        return matrixService.addition(dto[0], dto[1]);
    }
}
