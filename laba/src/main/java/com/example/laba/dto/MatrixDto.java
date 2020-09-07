package com.example.laba.dto;

import com.example.laba.model.Matrix;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class MatrixDto {
    @JsonProperty("Matrix")
    public Matrix matrix;
}
