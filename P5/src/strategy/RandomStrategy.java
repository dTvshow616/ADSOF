package strategy;

import dataset.LabeledDataset;

import java.util.List;
import java.util.Random;

/**
 * Implements the class random strategy
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <DATA> the type of object stored in this strategy
 */
public class RandomStrategy<DATA> implements FeatureSelectionStrategy<DATA> {
    /**
     * Search the best feature for the Greedy Tree
     * @param dataset the dataset we are using for the greedy tree
     * @param availableFeatures the features the function uses
     * @return the best feature
     */
    @Override
    public <LABEL> String execute(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures){
        return availableFeatures.get(new Random().nextInt(availableFeatures.size()));
    }
}