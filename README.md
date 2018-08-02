1. **I** DESCRIPTION OF PROJECT

In this project a neuronal network gets trained to learn classifying a given dataset of attributes of mushrooms.

The given dataset of attributes of mushrooms is a dataset form the UCI Machine Learning Repository 0. For training the neural network two third of the dataset is used. The other third of instances is used for testing the network.

The project is implemented in Java. For training and testing the neural network the Java library &quot;Java Neural Network Framework&quot; 0 was used. Before using the interface of this library the dataset has to be converted into a readable format. For this conversion the ImportHelper Class in ImportHelper.java is used. The dataset has the following structure:

_p,x,s,n,t,p,f,c,n,k,e,e,s,s,w,w,p,w,o,p,k,s,u_

The first letter is either p for a poisonous or e for a edible mushroom instance. All the other letters are attributes which can have various values. The ImportHelper class converts every row of the dataset into the following structure:

_0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0__,0,0,0,0,0,1,0,0,0…_

This binary values for each column is readable for the neural network interface. Every attribute of the original structure is divided into x attributes of the new structure. x means the amount of values of the corresponding attribute. The result, if the mushroom is poisonous or edible is appended to the string at the end with &quot;,0,1&quot; for edible and &quot;,1,0&quot; for poisonous.

These converted instances of the training set are all transferred to the neural network interface. The number of attributes of the mushrooms is 22. After converting the number of attributes is 126. For this reason a MultiLayer Perceptron of 126 input nodes and 2 output nodes is used in this project. The hidden layer of the MultiLayer Perceptron has 50 nodes.

After training the neural network get tested. Different variables are counting the different results. At the end the result of the testing is presented. The final result exists out of

- Number of test instances
- Correctly predicted instances and the ratio of the test instances
- Number of poisonous classified as edible true negative
- Number of edibles classified as poisonous false positive
- The ratio of the correctly predicted instances

This is how the application of the final results look like:

_ _

_FINAL RESULTS_

_Total test instances                                                 :  2708_

_Correctly predicted instances                                :  2452          (90.54652880354506 %)_

_Number of poisonous classified as edible              :  10                 (0.3692762186115214 %)_

_Number of edibles classified as poisonous             :  244          (9.010339734121123 %)_

_Correct classified ratio                                 :  90.54652880354506 %_

The constant variables that can be adjusted are the parameters of the neural network. One constant is the learning rate, and the other one is the momentum.

With a total number of 8124 instances the neural network has an average correct classified ratio of 90 %.

0 https://archive.ics.uci.edu/ml/datasets.html

0 http://neuroph.sourceforge.net/
