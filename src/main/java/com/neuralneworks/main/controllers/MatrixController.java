package com.neuralneworks.main.controllers;

import com.neuralneworks.main.datatypes.Matrix;
import com.neuralneworks.main.datatypes.requests.MatrixRequest;
import com.neuralneworks.main.datatypes.responses.ErrorResponse;
import com.neuralneworks.main.services.MatrixService;
import com.neuralneworks.main.validators.MatrixAddRequestValidator;
import com.neuralneworks.main.validators.MatrixMultiplyRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        path = "matrix/v1",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class MatrixController {

    @Autowired
    MatrixAddRequestValidator matrixAddRequestValidator;

    @Autowired
    MatrixMultiplyRequestValidator matrixMultiplyRequestValidator;

    @PostMapping (path = "/add")
    public ResponseEntity<Object> add(@RequestBody MatrixRequest matrixAddRequest, BindingResult bindingResult)
    {
        matrixAddRequestValidator.validate(matrixAddRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            return getErrorResponseEntity(bindingResult.getGlobalErrors());
        }

        Matrix result = MatrixService.add(matrixAddRequest.getMatrixA(), matrixAddRequest.getMatrixB());

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping (path = "/multiply")
    public Object multiply(@RequestBody MatrixRequest matrixMultiplyRequest, BindingResult bindingResult)
    {
        matrixMultiplyRequestValidator.validate(matrixMultiplyRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            return getErrorResponseEntity(bindingResult.getGlobalErrors());
        }

        Matrix result = MatrixService.multiply(matrixMultiplyRequest.getMatrixA(), matrixMultiplyRequest.getMatrixB());

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    private ResponseEntity<Object> getErrorResponseEntity(List<ObjectError> GlobalErrors)
    {
        return new ResponseEntity<Object>(GlobalErrors.stream().map(e -> new ErrorResponse(e.getCode(),
                                                                                           e.getDefaultMessage()))
                                                               .collect(Collectors.toList()),
                                          HttpStatus.BAD_REQUEST);
    }
}
