package ApartadoCuatro;

import java.util.*;

/**
 * Implements the class missclasification strategy
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <DATA> the type of object stored in this strategy
 */
public class MisclassificationStrategy<DATA> implements FeatureSelectionStrategy<DATA> {
    /**
     * Search the best feature for the Greedy Tree
     * @param dataset the dataset we are using for the greedy tree
     * @param availableFeatures the features the function uses
     * @return the best feature
     */
    @Override
    public <LABEL> String execute(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures){
        String bestFeature = null;
        int lowestScore = Integer.MAX_VALUE;

        for (String featureName : availableFeatures) {
            
            Map<Object, List<Object>> labelsByGroup = new LinkedHashMap<>();
            for (DATA obj : dataset.getObjects()) {
                Object val = dataset.getFeaturizer().featureValue(obj, featureName);
                Object label = dataset.getLabelProvider().provideLabel(obj);
                labelsByGroup.computeIfAbsent(val, k -> new ArrayList<>()).add(label);
            }

         
            int score = 0;
            for (List<Object> labels : labelsByGroup.values()) {
                Map<Object, Integer> freq = new HashMap<>();
                for (Object l : labels) freq.merge(l, 1, Integer::sum);
                int majority = Collections.max(freq.values());
                score += labels.size() - majority;
            }

            if (score < lowestScore) {
                lowestScore = score;
                bestFeature = featureName;
            }
        }
        return bestFeature;
    }
}