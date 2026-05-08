package demos;

import dataset.Dataset;
import dataset.LabeledDataset;
import feature.WeatherFeaturizer;
import label.ShouldIPlayTennisToday;
import strategy.*;
import tree.DecisionTree;
import tree.GreedyTreeLearner;
import weather.*;

/**
 * Demo for Ejercicio 5
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class DemoEjCinco {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DecisionTree<Weather> dt1 = learnTree1();
        DecisionTree<Weather> dt2 = learnTree2();
        System.out.println(dt1);
        System.out.println(dt2);
        Dataset<Weather> dataset = buildAnotherDataset();
        System.out.println(dt1.predict(dataset));
        System.out.println(dt2.predict(dataset));
    }

    /**
     * It gets a learned tree
     * @return the decision tree
     */
    public static DecisionTree<Weather> learnTree1() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        FeatureSelectionStrategy<Weather> st = new RandomStrategy<>();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>(st);
        return learner.learn(dataSet);
    }

    /**
     * It gets another learned tree
     * @return the decision tree
     */
    public static DecisionTree<Weather> learnTree2() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        FeatureSelectionStrategy<Weather> st = new MisclassificationStrategy<>();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>(st);
        return learner.learn(dataSet);
    }

    /**
     * It builds a weather labelled dataset
     * @return a weather labelled dataset
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
     * It builds a weather dataset
     * @return a weather dataset
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