package ApartadoTres;

import ApartadoDos.DecisionTree;

public class GreedyTreeLearner<G, U> {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static DecisionTree<Weather> learnTree() {
        LabeledDataset<Weather, Boolean> dataSet = buildDataSet();
        GreedyTreeLearner<Weather, Boolean> learner = new GreedyTreeLearner<>();
        DecisionTree<Weather> tree = learner.learn(dataSet);
        return tree;
    }

    private static LabeledDataset<Weather, Boolean> buildDataSet() {
        Weather[] conditions = {new Weather(WeatherCondition.RAINY, Temperature.COLD),
                                new Weather(WeatherCondition.RAINY, Temperature.HOT)
                                // más objetos...
        };
        LabeledDataset<Weather, Boolean> ds =
                new LabeledDataset<>(new WeatherFeaturizer(), new ShouldIPlayTennisToday());
        ds.addAll(conditions);
        return ds;
    }
}