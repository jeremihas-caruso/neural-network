package com.neuralneworks.main.validators;

import com.neuralneworks.main.datatypes.Matrix;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MatrixValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Matrix.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Matrix matrix = (Matrix) o;

        if (matrix.getData().size() < (matrix.getRows() * matrix.getCols()))
            errors.reject("missing","Data missing!");
        else if (matrix.getData().size() > (matrix.getRows() * matrix.getCols()))
            errors.reject("excessive","Excessive data!");
    }
}
