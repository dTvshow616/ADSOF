package ApartadoCuatro;

import java.util.List;

public interface FeatureSelectionStrategy<DATA> {
    <LABEL> String execute(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures);
}
