package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;

import java.util.List;
import java.util.Random;

public class RandomStrategy<DATA> implements FeatureSelectionStrategy<DATA> {
    @Override
    public String execute(LabeledDataset<DATA, ?> dataset) {
        List<String> features = dataset.featureNames();
        return features.get(new Random().nextInt(features.size()));
    }
}
