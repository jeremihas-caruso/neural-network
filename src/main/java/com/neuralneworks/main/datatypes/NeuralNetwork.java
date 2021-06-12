package com.neuralneworks.main.datatypes;

import com.neuralneworks.main.services.MatrixService;

public class NeuralNetwork {
    private Integer input;
    private Integer hidden;
    private Integer output;

    private Matrix biasInputHidden;
    private Matrix biasHiddenOutput;

    private Matrix weightInputHidden;
    private Matrix weightHiddenOutput;

    public NeuralNetwork(Integer input, Integer hidden, Integer output) {
        this.input = input;
        this.hidden = hidden;
        this.output = output;

        this.biasInputHidden = MatrixService.randomize(hidden, 1);
        this.biasHiddenOutput = MatrixService.randomize(output, 1);

        this.weightInputHidden = MatrixService.randomize(hidden, input);
        this.weightHiddenOutput = MatrixService.randomize(output, hidden);
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public Matrix getBiasInputHidden() {
        return biasInputHidden;
    }

    public void setBiasInputHidden(Matrix biasInputHidden) {
        this.biasInputHidden = biasInputHidden;
    }

    public Matrix getBiasHiddenOutput() {
        return biasHiddenOutput;
    }

    public void setBiasHiddenOutput(Matrix biasHiddenOutput) {
        this.biasHiddenOutput = biasHiddenOutput;
    }

    public Matrix getWeightInputHidden() {
        return weightInputHidden;
    }

    public void setWeightInputHidden(Matrix weightInputHidden) {
        this.weightInputHidden = weightInputHidden;
    }

    public Matrix getWeightHiddenOutput() {
        return weightHiddenOutput;
    }

    public void setWeightHiddenOutput(Matrix weightHiddenOutput) {
        this.weightHiddenOutput = weightHiddenOutput;
    }

    @Override
    public String toString() {
        return "NeuralNetwork{" +
                "input=" + input +
                ", hidden=" + hidden +
                ", output=" + output +
                ", biasInputHidden=" + biasInputHidden +
                ", biasHiddenOutput=" + biasHiddenOutput +
                ", weightInputHidden=" + weightInputHidden +
                ", weightHiddenOutput=" + weightHiddenOutput +
                '}';
    }
}
