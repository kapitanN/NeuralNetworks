package com.algorithms;

/**
 * Created by nikita on 27.02.2017.
 */
public class HebbianNetwork {

    public HebbianNetwork(){}

    private static final int NUMBER_OF_INPUTS = 25;
    private static final int NUMBER_OF_OUTPUTS = 3;
    private static final int NUMBER_OF_IMAGES = 4;

    private Neuron neurons[] = new Neuron[NUMBER_OF_IMAGES];
    private int weights[][] = new int[NUMBER_OF_OUTPUTS][NUMBER_OF_INPUTS];

    private class Neuron{
        int inputs[];
        int outputs[];
    }

    private void setOutputs(){
        neurons[0].outputs = new int[]{-1,1,1};
        neurons[1].outputs = new int[]{1,-1,1};
        neurons[2].outputs = new int[]{1,1,-1};
        neurons[3].outputs = new int[]{1,-1,-1};
    }
    
    private void setInputs(){
        for (int i = 0; i<NUMBER_OF_IMAGES; i++){
            neurons[i] = new Neuron();
        }
        neurons[0].inputs = new int[]{1,-1,-1,-1,1,1,-1,-1,-1,1,1,1,1,1,1,1,-1,-1,-1,1,1,-1,-1,-1,1};//Н
        neurons[1].inputs = new int[]{1,-1,-1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,1,1,1,1};//Ш
        neurons[2].inputs = new int[]{1,1,1,1,1,1,-1,-1,-1,1,1,-1,-1,-1,-1,1,-1,-1,-1,1,1,1,1,1,1};//С
        neurons[3].inputs = new int[]{1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1};//Е
    }

    private int countOutput(int inputs[], int w[]){
       int output;
        int count = 0;
        for (int i =0; i<inputs.length; i++){
            count += inputs[i]*w[i];
        }

        if (count > 0)
            output = 1;
        else
            output = -1;
        return output;
    }

    private void training(){
        System.out.println("Start training");
        setInputs();
        setOutputs();
        boolean flag;
        int iteration = 0;
        for (;;){
            flag = true;
            iteration++;
            System.out.println(iteration);
            for (int i = 0; i<NUMBER_OF_IMAGES; i++){
                String trainingOutput = "" + neurons[i].outputs[0] + neurons[i].outputs[1] + neurons[i].outputs[2];
                String realOutput = "" + countOutput(neurons[i].inputs, weights[0]) + countOutput(neurons[i].inputs, weights[1]) + countOutput(neurons[i].inputs, weights[2]);
                System.out.println("training: " + trainingOutput + " real: " + realOutput);
                if (!trainingOutput.equals(realOutput)){
                    flag = false;
                    for (int j = 0; j<NUMBER_OF_OUTPUTS; j++){
                        for (int k = 0; k<NUMBER_OF_INPUTS; k++){
                            weights[j][k] += neurons[i].inputs[k] * neurons[i].outputs[j];
                        }
                    }
                }
            }
            if (flag){
                break;
            }
        }
    }

    private String recognize(int inputs[]){
        String outputResult = new String();
        String patternsOutputResult = new String();
        int numberOfResultImage = 0;
        int output[] = new int[NUMBER_OF_OUTPUTS];
        for (int i = 0; i<NUMBER_OF_OUTPUTS; i++){
            output[i] = countOutput(inputs,weights[i]);
            outputResult += output[i];
        }
        for (int i = 0; i<NUMBER_OF_IMAGES; i++){
            patternsOutputResult = "" + neurons[i].outputs[0] + neurons[i].outputs[1] + neurons[i].outputs[2];
            if (outputResult.equals(patternsOutputResult)){
                System.out.println("result: " + outputResult);
                System.out.println("pattern: " + patternsOutputResult);
                numberOfResultImage = i+1;
            }
        }
        return "This is " + numberOfResultImage + " image.";
    }

    public static void main(String args[]){
        HebbianNetwork hebbianNetwork =  new HebbianNetwork();
        hebbianNetwork.training();
        int testInput1[] = new int[]{1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1};//Е
        String result1 = hebbianNetwork.recognize(testInput1);
        System.out.println(result1);
    }
}