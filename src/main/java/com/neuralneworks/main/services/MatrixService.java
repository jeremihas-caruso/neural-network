package com.neuralneworks.main.services;

import com.neuralneworks.main.datatypes.Matrix;
import com.neuralneworks.main.datatypes.requests.MatrixRequest;
import com.neuralneworks.main.datatypes.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MatrixService {

    public static Matrix add(Matrix matrixA, Matrix matrixB)
    {
        Matrix result = new Matrix(matrixA.getRows(),
                matrixA.getCols(),
                matrixA.getData());

        Integer size = matrixA.getRows() * matrixA.getCols();
        IntStream.range(0, size)
                .forEach(i -> result.getData().set(i, (result.getData().get(i) + matrixB.getData().get(i))));

        return result;
    }

    public static Matrix multiply(Matrix matrixA, Matrix matrixB)
    {
        Matrix result = new Matrix(matrixA.getRows(),
                matrixB.getCols(),
                new ArrayList<Double>());

        Integer size = matrixA.getRows() * matrixB.getCols();
        IntStream.range(0, size)
                .forEach(i -> result.getData().add(i, calculateMatrixMultiply(matrixA, matrixB, i)));

        return result;
    }

    public static Matrix randomize(Integer rows, Integer cols) {
        Matrix result = new Matrix(rows, cols, new ArrayList<>());

        Random random = new Random();

        IntStream.range(0, rows * cols)
                .forEach(i -> result.getData().add(random.nextDouble()));

        return result;
    }

    private static Double calculateMatrixMultiply(Matrix matrixA, Matrix matrixB, int i)
    {
        List<Double> matrixAFiltered = matrixA.getData().subList((i/matrixB.getCols())*matrixA.getCols(), (i/matrixB.getCols())*matrixA.getCols()+matrixA.getCols());
        List<Double> matrixBFiltered = IntStream
                .range(0, matrixB.getData().size())
                .filter(key -> (key % matrixB.getCols()) == (i % matrixB.getCols()))
                .mapToObj(key -> matrixB.getData().get(key))
                .collect(Collectors.toList());

        return IntStream.range(0, matrixBFiltered.size())
                .mapToDouble(key -> matrixAFiltered.get(key)*matrixBFiltered.get(key))
                .reduce(Double::sum)
                .getAsDouble();
    }
}
