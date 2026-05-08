package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;

import java.util.List;
import java.util.Random;

public class RandomStrategy<DATA> implements FeatureSelectionStrategy<DATA> {
    @Override
    public <LABEL> String execute(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures){
        return availableFeatures.get(new Random().nextInt(availableFeatures.size()));
    }
}
