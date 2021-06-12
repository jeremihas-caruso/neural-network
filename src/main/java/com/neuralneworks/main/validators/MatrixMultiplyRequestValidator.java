package com.neuralneworks.main.validators;

import com.neuralneworks.main.datatypes.Matrix;
import com.neuralneworks.main.datatypes.requests.MatrixRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MatrixMultiplyRequestValidator implements Validator {

    private final Validator matrixValidator;

    public MatrixMultiplyRequestValidator(Validator matrixValidator) {
        if (matrixValidator == null) {
            throw new IllegalArgumentException(
                    "The supplied [Validator] is required and must not be null.");
        }
        if (!matrixValidator.supports(Matrix.class)) {
            throw new IllegalArgumentException(
                    "The supplied [Validator] must support the validation of [Matrix] instances.");
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

            ValidationUtils.invokeValidator(this.matrixValidator, matrixRequest.getMatrixA(), errors);
            ValidationUtils.invokeValidator(this.matrixValidator, matrixRequest.getMatrixB(), errors);
        } finally {
            errors.popNestedPath();
        }

        if(matrixRequest.getMatrixA().getCols() != matrixRequest.getMatrixB().getRows()) {
            errors.reject("colsA&rowsB", "Columns quantity of the first matrix must be equal to rows quantity of the second one!");
        }
    }
}
