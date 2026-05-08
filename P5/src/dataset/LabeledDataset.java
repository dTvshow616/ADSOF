package dataset;

import feature.Featurizer;
import label.LabelProvider;

import java.util.*;

public class LabeledDataset<DATA, LABEL> extends Dataset<DATA> {
    private final LabelProvider<DATA, LABEL> labelProvider;
    private final HashMap<LABEL, List<DATA>> labeledData = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public LabeledDataset(Featurizer<DATA> featurizer, LabelProvider<DATA, LABEL> labelProvider) {
        super(featurizer);
        this.labelProvider = labelProvider;
    }

    public void labelData(Dataset<DATA> dataset) {
        this.labeledData.clear();
        for (DATA object : dataset.getObjects()) {
            LABEL label = labelProvider.provideLabel(object);
            List<DATA> data = labeledData.get(label);
            if (data == null) {
                data = new ArrayList<>();
            }
            data.add(object);
            this.labeledData.put(label, data);
        }
    }

    @Override
    public void addAll(DATA[] array) {
        if (array == null) {
            System.out.println("FUCK");
        }
        Dataset<DATA> aux = new Dataset<>(this.getFeaturizer());
        aux.addAll(array);
        labelData(aux);
    }

    public LabelProvider<DATA, LABEL> getLabelProvider() {
        return labelProvider;
    }

    public HashMap<LABEL, List<DATA>> getLabeledData() {
        return labeledData;
    }
}