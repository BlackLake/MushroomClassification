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

public class MushroomClassification {

    static double learningRate = 0.7;
    static double momentum = 0.4;

    static int numberOfEdiblesClassifiedAsPoisonous = 0;
    static int numberOfPoisonousClassifiedAsEdible = 0;

    static ArrayList<int[]> givenTestOutputs = new ArrayList<>();
    static ArrayList<int[]> givenTrainingOutputs = new ArrayList<>();

    static String trainingSetFileName = "training.data";
    static String testingSetFileName = "test.data";
    static String kohonenTrainingSetFileName = "kohonenTraining.data";
    static String kohonenTestSetFileName = "kohonenTest.data";

    public static void main(String[] args) {

        ImportHelper.readFromURLAndWriteToFile();

        ImportHelper.convertDataFiles();


        int inputsCount = 126;
        int outputsCount = 2;
        int kohonenSetInputsCount = 15;
        int kohonenSetOutputsCount = 2;

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

        //Read given training outputs for later use in testing the networks
        readGivenTrainingOutputs();


        // create kohonen network
        NeuralNetwork kohonenNeuralNetwork = new Kohonen(inputsCount, kohonenSetInputsCount);
        kohonenNeuralNetwork.learn(trainingSet);

        runAndPrintKohonenToFile(kohonenNeuralNetwork, trainingSet, kohonenTrainingSetFileName);

        // create kohonen set
        DataSet kohonenTrainingSet = null;
        try {
            kohonenTrainingSet = TrainingSetImport.importFromFile(kohonenTrainingSetFileName, kohonenSetInputsCount, kohonenSetOutputsCount, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading file or bad number format!");
        }

        // create multi layer perceptron
        System.out.println("Creating neural network");
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, kohonenSetInputsCount, 5, kohonenSetOutputsCount);

        // set learning parametars
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.setMaxIterations(10);
        learningRule.setLearningRate(learningRate);
        learningRule.setMomentum(momentum);

        // learn the training set
        System.out.println("Training neural network...");
        neuralNet.learn(kohonenTrainingSet);
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

        //Read given test outputs for later use in testing the networks
        readGivenTestOutputs();

        runAndPrintKohonenToFile(kohonenNeuralNetwork, testingSet, kohonenTestSetFileName);

        // create kohonen set
        DataSet kohonenTestingSet = null;
        try {
            kohonenTestingSet = TrainingSetImport.importFromFile(kohonenTestSetFileName, kohonenSetInputsCount, kohonenSetOutputsCount, ",");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading file or bad number format!");
        }

        // test perceptron
        System.out.println("Testing trained neural network");
        testClassificationOfMushroomsPoisonous(neuralNet, kohonenTestingSet);

    }

    public static void runAndPrintKohonenToFile(NeuralNetwork nnet, DataSet dset, String file) {
        ArrayList<double[]> kohonenOutputs = new ArrayList<>();
        for (DataSetRow trainingElement : dset.getRows()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            kohonenOutputs.add(networkOutput.clone());
            //System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            //System.out.println(" Output: " + Arrays.toString(networkOutput));
        }

        try {
            FileWriter kohonenFileWriter = new FileWriter(file);
            PrintWriter kohonenPrintWriter = new PrintWriter(kohonenFileWriter);

            int counter = 0;
            for (double[] out : kohonenOutputs) {
                String kohonenRow = "";
                for (int i = 0; i < out.length; i++) {
                    //kohonenRow += (int) Math.round(out[i]) + ",";
                    //kohonenRow += out[i] / 10 + ",";
                    kohonenRow += out[i]/10 + ",";
                }
                if (file == kohonenTrainingSetFileName)
                    kohonenRow += givenTrainingOutputs.get(counter)[0] + "," + givenTrainingOutputs.get(counter)[1];
                if (file == kohonenTestSetFileName)
                    kohonenRow += givenTestOutputs.get(counter)[0] + "," + givenTestOutputs.get(counter)[1];


                    //System.out.println(counter + "    " + kohonenRow);
                kohonenPrintWriter.println(kohonenRow);

                counter++;
            }
            //System.out.println(counter);
            kohonenFileWriter.close();
            kohonenPrintWriter.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readGivenTrainingOutputs() {
        File fileInput = new File("training.data");
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
                givenTrainingOutputs.add(temp);

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
    }

    public static void readGivenTestOutputs() {
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
    }

    public static void testClassificationOfMushroomsPoisonous(NeuralNetwork nnet, DataSet dset) {

        int trueCounter = 0;
        int totalCounter = 0;


        for (DataSetRow trainingElement : dset.getRows()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();

            System.out.println("_Output: " + Arrays.toString(networkOutput));
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

            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println("Output: " + Arrays.toString(networkOutput));

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
