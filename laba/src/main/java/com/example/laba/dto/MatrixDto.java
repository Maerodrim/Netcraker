package com.example.laba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatrixDto {
    public MatrixDto() {
    }

    public MatrixDto(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new Double[rows * columns];
    }

    @JsonProperty("rows")
    private int rows;
    @JsonProperty("columns")
    private int columns;
    @JsonProperty("elements")
    private Double[] elements;

    public MatrixDto(int rows, int columns, Double[][] elements) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new Double[rows * columns];
        for (int i = 0; i < rows; i++) {
            if (columns >= 0) System.arraycopy(elements[i], 0, this.elements, i * columns, columns);
        }
    }

    public void set(Object obj, int i, int j) {
        this.elements[i*columns + j] = (Double) obj;
    }
}

