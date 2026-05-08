package ApartadoCuatro;

import java.util.List;

/**
 * Implements the interface Feature selection strategy
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <DATA> the type of object stored in this strategy
 */
public interface FeatureSelectionStrategy<DATA> {
    /**
     * Search the best feature for the Greedy Tree
     * @param dataset the dataset we are using for the greedy tree
     * @param availableFeatures the features the function uses
     * @return the best feature
     */
    <LABEL> String execute(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures);
}
