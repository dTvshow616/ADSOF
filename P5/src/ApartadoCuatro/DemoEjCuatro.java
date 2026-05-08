package ApartadoCuatro;

import ApartadoDos.DecisionTree;

public class DemoEjCuatro {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
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
}