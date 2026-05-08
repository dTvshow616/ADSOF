package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;

/**
 * Demo for Ejercicio 1
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class DemoEjCinco {
        /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        DecisionTree<Weather> dt1 = learnTree1();
        DecisionTree<Weather> dt2 = learnTree2();
        System.out.println(dt1);
        System.out.println(dt2);
        Dataset<Weather> dataset = buildAnotherDataset();
        System.out.println(dt1.predict(dataset));
        System.out.println(dt2.predict(dataset));
    }

    public static DecisionTree<Weather> learnTree1() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        FeatureSelectionStrategy<Weather> st = new RandomStrategy<>();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>(st);
        DecisionTree<Weather> tree = learner.learn(dataSet);
        return tree;
    }

    public static DecisionTree<Weather> learnTree2() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        FeatureSelectionStrategy<Weather> st = new MisclassificationStrategy<>();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>(st);
        DecisionTree<Weather> tree = learner.learn(dataSet);
        return tree;
    }

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
