package demos;

import dataset.Dataset;
import dataset.LabeledDataset;
import feature.WeatherFeaturizer;
import label.ShouldIPlayTennisToday;
import tree.DecisionTree;
import tree.GreedyTreeLearner;
import weather.*;

/**
 * Demo for Ejercicio 4
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class DemoEjCuatro {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DecisionTree<Weather> dt = learnTree();
        System.out.println(dt);
        Dataset<Weather> dataset = buildAnotherDataset();
        System.out.println(dt.predict(dataset));
    }

    /**
     * Learn tree decision tree.
     * @return the decision tree
     */
    public static DecisionTree<Weather> learnTree() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
        return learner.learn(dataSet);
    }

    /**
     * It builds a Weather labelled dataset
     * @return a Weather labelled dataset
     */
    private static LabeledDataset<Weather, Boolean> buildDataSet() {
        Weather[] conditions = {new Weather(WeatherCondition.RAINY, Temperature.COLD),
                                new Weather(WeatherCondition.RAINY, Temperature.HOT),
                                new Weather(WeatherCondition.CLOUDY, Temperature.HOT),
                                new Weather(WeatherCondition.SUNNY, Temperature.HOT),
                                new Weather(WeatherCondition.CLOUDY, Temperature.COLD)};

        LabeledDataset<Weather, Boolean> ds =
                new LabeledDataset<>(new WeatherFeaturizer(), new ShouldIPlayTennisToday());
        ds.addAll(conditions);
        return ds;
    }

    /**
     * It builds a Weather dataset
     * @return a Weather dataset
     */
    private static Dataset<Weather> buildAnotherDataset() {
        Weather[] conditions = {new Weather(WeatherCondition.CLOUDY, Temperature.COLD),
                                new Weather(WeatherCondition.RAINY, Temperature.HOT),
                                new Weather(WeatherCondition.SUNNY, Temperature.HOT),
                                new Weather(WeatherCondition.SUNNY, Temperature.COLD),
                                new Weather(WeatherCondition.CLOUDY, Temperature.COLD)};

        Dataset<Weather> dataSet = new Dataset<>(new WeatherFeaturizer());
        dataSet.addAll(conditions);
        return dataSet;
    }

}