# Assignment 1 README</h1>

### FeatureFileAsDistribution object
+ __fromFile(filename)__: gets the probability distribution and conditional probability distribution of the features and 
values contained in the file provided. 

    #### Testing
    A testing option is provided by running main and adding the test flag. This will run the data2.txt file, calculate, and display some probabilities. To see this, type

    ```
    sbt "run-main nlp.a1.FeatureFileAsDistribution -test"
    ```

### ProbabilityDistribution class
+ __apply(x)__: calculates and returns probability of a label based on the total count for labels

+ __sample()__: samples the probability distribution with a random number

### ConditionalProbability class
+ __apply(a, given)__: calculates and returns conditional probability of a value given a particular label

+ __sample(a)__: samples the conditional probability distribution with a random number given a particular condition