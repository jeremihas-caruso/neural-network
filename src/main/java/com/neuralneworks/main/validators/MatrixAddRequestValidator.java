package com.neuralneworks.main.validators;

import com.neuralneworks.main.datatypes.Matrix;
import com.neuralneworks.main.datatypes.requests.MatrixRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MatrixAddRequestValidator implements Validator {

    private final MatrixValidator matrixValidator;

    public MatrixAddRequestValidator(MatrixValidator matrixValidator) {
        if (matrixValidator == null) {
            throw new IllegalArgumentException(
                    "The supplied [MatrixValidator] is required and must not be null.");
        }
        if (!matrixValidator.supports(Matrix.class)) {
            throw new IllegalArgumentException(
                    "The supplied [MatrixValidator] must support the validation of [Matrix] instances.");
        }
        this.matrixValidator = matrixValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MatrixRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MatrixRequest matrixRequest = (MatrixRequest) o;

        try {
            errors.pushNestedPath("matrix");

            this.matrixValidator.validate(matrixRequest.getMatrixA(), errors);
            this.matrixValidator.validate(matrixRequest.getMatrixB(), errors);
        } finally {
            errors.popNestedPath();
        }

        if(matrixRequest.getMatrixA().getRows() != matrixRequest.getMatrixB().getRows())
            errors.reject("rows","Rows quantity must be equal!");

        if(matrixRequest.getMatrixA().getCols() != matrixRequest.getMatrixB().getCols())
            errors.reject("cols","Columns quantity must be equal!");
    }
}
