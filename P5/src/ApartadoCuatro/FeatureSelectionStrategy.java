package ApartadoCuatro;


public interface FeatureSelectionStrategy<DATA> {
    String execute(LabeledDataset<DATA, ?> dataset);
}
