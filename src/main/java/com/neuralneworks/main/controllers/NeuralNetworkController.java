package com.neuralneworks.main.controllers;

import com.neuralneworks.main.datatypes.Matrix;
import com.neuralneworks.main.datatypes.NeuralNetwork;
import com.neuralneworks.main.services.MatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "neuralNetwork/v1",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class NeuralNetworkController {

    @PostMapping(path = "/feedForward")
    public ResponseEntity<Object> feedForward(@RequestBody Matrix input) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(1,3,1);

        Matrix hidden = MatrixService.multiply(neuralNetwork.getWeightInputHidden(), input);

        System.out.println(hidden);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
