import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

import javax.xml.crypto.Data;


public class MushroomClassification {

    static double learningRate = 0.7;
    static double momentum = 0.4;

    static int numberOfEdiblesClassifiedAsPoisonous=0;
    static int numberOfPoisonousClassifiedAsEdible=0;


    public static void main(String[] args) {

        ImportHelper.readFromURLAndWriteToFile();

        ImportHelper.convertDataFiles();

        String trainingSetFileName = "training.data";
        String testingSetFileName = "test.data";
        int kohonenInputsCount = 126;
        int kohonenOutputsCount = 15;
        int inputsCount = 126;
        int outputsCount = 2;

        System.out.println("Running Sample");
        System.out.println("Using training set " + trainingSetFileName);
        System.out.println();

        // create training set
        DataSet trainingSet = null;
        try {
            trainingSet = TrainingSetImport.importFromFile(trainingSetFileName, inputsCount, outputsCount, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading file or bad number format!");
        }
/*
        // create kohonen set
        DataSet kohonenSet = null;
        try {
            kohonenSet = TrainingSetImport.importFromFile(trainingSetFileName, kohonenInputsCount, kohonenOutputsCount, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading file or bad number format!");
        }

        // create kohonen network
        NeuralNetwork kohonenNeuralNetwork = new Kohonen(kohonenInputsCount,kohonenOutputsCount);
        kohonenNeuralNetwork.learn(kohonenSet);*/


        // create multi layer perceptron
        System.out.println("Creating neural network");
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 126, 50, 2);


        // set learning parametars
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.setLearningRate(learningRate);
        learningRule.setMomentum(momentum);

        // learn the training set
        System.out.println("Training neural network...");
        neuralNet.learn(trainingSet);
        System.out.println("Done!");

        // create testing set
        DataSet testingSet = null;
        try {
            testingSet = TrainingSetImport.importFromFile(testingSetFileName, inputsCount, outputsCount, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading file or bad number format!");
        }

        // test perceptron
        System.out.println("Testing trained neural network");
        testClassificationOfMushroomsPoisonous(neuralNet, testingSet);

    }
/*
    public static ArrayList<double[]> testKohonen(NeuralNetwork nnet, DataSet dset) {
        ArrayList<double[]> kohonenOutputs = new ArrayList<>();
        for (DataSetRow trainingElement : dset.getRows()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            kohonenOutputs.add(networkOutput);
            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));

            //DataSet dataSet = null;
            //dataSet = TrainingSetImport.importFromFile();
        }
        return kohonenOutputs;
    }*/

    public static void testClassificationOfMushroomsPoisonous(NeuralNetwork nnet, DataSet dset) {

        int trueCounter = 0;
        int totalCounter = 0;
        ArrayList<int[]> givenTestOutputs = new ArrayList<>();

        File fileInput = new File("test.data");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(fileInput);
            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            while (dis.available() != 0) {

                // this statement reads the line from the file and print it to
                // the console.
                //System.out.println(dis.readLine());

                String row = dis.readLine();
                String[] elements = row.split(",");
                int[] temp = {Integer.parseInt(elements[126]), Integer.parseInt(elements[127])};
                givenTestOutputs.add(temp);

            }
            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (DataSetRow trainingElement : dset.getRows()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            if (networkOutput[0] < 0.5) {
                networkOutput[0] = 0;
            } else {
                networkOutput[0] = 1;
            }
            if (networkOutput[1] < 0.5) {
                networkOutput[1] = 0;
            } else {
                networkOutput[1] = 1;
            }

            //System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            //System.out.println(" Output: " + Arrays.toString(networkOutput));

            if (networkOutput[0] == givenTestOutputs.get(totalCounter)[0] && networkOutput[1] == givenTestOutputs.get(totalCounter)[1]) {
                trueCounter++;
                if (networkOutput[0] == 0 && networkOutput[1] == 1) {
                    System.out.println("mushroom number " + totalCounter + " is edible");
                } else {
                    System.out.println("mushroom number " + totalCounter + " is posionus");
                }
            } else if (networkOutput[0] == 0 && networkOutput[1] == 1) {
                System.out.println("mushroom number " + totalCounter + " is edible   (FALSE)");
                numberOfPoisonousClassifiedAsEdible++;
            } else if (networkOutput[0] == 1 && networkOutput[1] == 0) {
                System.out.println("mushroom number " + totalCounter + " is posionus (FALSE)");
                numberOfEdiblesClassifiedAsPoisonous++;
            }
            totalCounter++;


        }
        System.out.println();
        System.out.println("                       FINAL RESULTS");
        System.out.println("Total test instances                        :  " + totalCounter);
        System.out.println("Correctly predicted instances               :  " + trueCounter + "\t(" + ((double) trueCounter / totalCounter) * 100 + " %)");
        System.out.println("Number of poisonous classified as edible    :  " + numberOfPoisonousClassifiedAsEdible + "\t(" + ((double) numberOfPoisonousClassifiedAsEdible / totalCounter) * 100 + " %)");
        System.out.println("Number of edibles classified as poisonous   :  " + numberOfEdiblesClassifiedAsPoisonous + "\t(" + ((double) numberOfEdiblesClassifiedAsPoisonous / totalCounter) * 100 + " %)");
        System.out.println("Percentage : " + ((double) trueCounter / totalCounter) * 100 + " %");
    }


}
