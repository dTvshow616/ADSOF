package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;

public class DemoEjCuatro {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        DecisionTree<Weather> dt = learnTree();
        System.out.println(dt);
        Dataset<Weather> dataset = buildAnotherDataset();
        dt.predict(dataset);
    }

    public static DecisionTree<Weather> learnTree() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
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