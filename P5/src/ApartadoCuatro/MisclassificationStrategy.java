package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;

import java.util.*;

public class MisclassificationStrategy<DATA> implements FeatureSelectionStrategy<DATA> {
    @Override
    public String execute(LabeledDataset<DATA, ?> dataset) {
        String bestFeature = null;
        int lowestScore = Integer.MAX_VALUE;

        for (String featureName : dataset.featureNames()) {
            
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