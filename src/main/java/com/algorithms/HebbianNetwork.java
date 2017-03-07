package com.algorithms;

/**
 * Created by nikita on 27.02.2017.
 */
public class HebbianNetwork {

    public HebbianNetwork(){}

    private static final int NUMBER_OF_INPUTS = 25;
    private static final int NUMBER_OF_OUTPUTS = 2;
    private static final int NUMBER_OF_IMAGES = 4;

    private Neuron neurons[] = new Neuron[NUMBER_OF_IMAGES];
    public int weights[][] = new int[NUMBER_OF_OUTPUTS][NUMBER_OF_INPUTS];

    private class Neuron{
        int inputs[];
        int outputs[];
    }

    //устанавливаем выходные значения нейронов для обучения
    private void setOutputs(){
        neurons[0].outputs = new int[]{-1,-1};
        neurons[1].outputs = new int[]{-1,1};
        neurons[2].outputs = new int[]{1,-1};
        neurons[3].outputs = new int[]{1,1};
    }

    //устанавливаем входные значние нейронов
    public void setInputs(int[][] inputArray){
        for (int i = 0; i<inputArray.length; i++){
            neurons[i] = new Neuron();
        }
        for (int i = 0; i<inputArray.length; i++){
            neurons[i].inputs = inputArray[i];
            System.out.println();
            for (int j = 0; j < inputArray[i].length; j++) {
                System.out.println(inputArray[i][j]);
            }
        }
//        neurons[0].inputs = inputArray;//Н
//        neurons[1].inputs = new int[]{1,-1,-1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,-1,1,-1,1,1,1,1,1,1};//Ш
//        neurons[2].inputs = new int[]{1,1,1,1,1,1,-1,-1,-1,1,1,-1,-1,-1,-1,1,-1,-1,-1,1,1,1,1,1,1};//С
//        neurons[3].inputs = new int[]{1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1};//Е
    }

    private int countOutput(int inputs[], int w[]){
        int count = 0;
        for (int i =0; i<inputs.length; i++){
            count += inputs[i]*w[i];
        }
        if (count > 0)
            return 1;
        else
            return -1;
    }

    public void training(int[][] inputArray){
        System.out.println("Start training");
        setInputs(inputArray);
        setOutputs();
        boolean flag;
        int iteration = 0;
        do{
            flag = true;
            iteration++;
            System.out.println(iteration);
            for (int i = 0; i<NUMBER_OF_IMAGES; i++){
                String trainingOutput = "" + neurons[i].outputs[0] + neurons[i].outputs[1]; //записываем значение выходных нейронов для обучения
                String realOutput = "" + countOutput(neurons[i].inputs, weights[0]) + countOutput(neurons[i].inputs, weights[1]); //получаем значения на выходах нейронов исходя из входных значений и весов связей
                System.out.println("training: " + trainingOutput + " real: " + realOutput);
                //если ожидаемые значения выходных нейронов и действитеельные не совпадают, то корректируем веса связей
                if (!trainingOutput.equals(realOutput)){
                    flag = false;
                    for (int j = 0; j<NUMBER_OF_OUTPUTS; j++){
                        for (int k = 0; k<NUMBER_OF_INPUTS; k++){
                            //умножаем каждое входное значение значение одного изображения на ожидаемое значение выходного нейрона.
                            // если значние отличаются(1 и -1), то вес связи уменьшается, если значние равны(-1 и -1)- то вес увеличивается.
                            weights[j][k] += neurons[i].inputs[k] * neurons[i].outputs[j];
                        }
                    }
                }
            }
        }
        while (!flag);
    }

    public String recognize(int inputs[]){
        String outputResult = "";
        String patternsOutputResult ;
        int numberOfResultImage = 0;
        int output[] = new int[NUMBER_OF_OUTPUTS];
        // считаем значения выходных нейронов исходя из входных значений
        for (int i = 0; i<NUMBER_OF_OUTPUTS; i++){
            output[i] = countOutput(inputs,weights[i]);
            outputResult += output[i];
        }
        // сравниваем получаенные значния с обучающей выборкой и определяем поданное изображение
        for (int i = 0; i<NUMBER_OF_IMAGES; i++){
            patternsOutputResult = "" + neurons[i].outputs[0] + neurons[i].outputs[1];
            if (outputResult.equals(patternsOutputResult)){
                System.out.println("result: " + outputResult);
                System.out.println("pattern: " + patternsOutputResult);
                numberOfResultImage = i+1;
            }
        }
        return "This is " + numberOfResultImage + " image.";
    }

//    public static void main(String args[]){
//        HebbianNetwork hebbianNetwork =  new HebbianNetwork();
//        hebbianNetwork.training();
//        int testInput1[] = new int[]{1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1,1,-1,-1,-1,-1,1,1,1,1,1};//Е
//        String result1 = hebbianNetwork.recognize(testInput1);
//        System.out.println(result1);
//    }
}
