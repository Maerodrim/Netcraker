package com.example.laba.controller;

import com.example.laba.dto.MatrixDto;
import com.example.laba.service.MatrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("MatrixController")
@RequiredArgsConstructor
//localhost:8080/MatrixController/addition?...
public class MatrixController {
    private final MatrixService matrixService;

    @PostMapping("addition")
    public MatrixDto additionMatrix(@RequestBody MatrixDto firstDto,
                                          @RequestBody MatrixDto secondDto) {
        return matrixService.addition(firstDto, secondDto);
    }
}
