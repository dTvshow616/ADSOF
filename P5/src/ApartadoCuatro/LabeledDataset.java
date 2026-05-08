package ApartadoCuatro;

import ApartadoUno.Dataset;
import ApartadoUno.Featurizer;

import java.util.HashMap;
import java.util.List;

public class LabeledDataset<DATA, LABEL> extends Dataset<DATA> {
    private final LabelProvider<DATA, LABEL> labelProvider;
    private final HashMap<LABEL, List<DATA>> labeledData = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    LabeledDataset(Featurizer<DATA> featurizer, LabelProvider<DATA, LABEL> labelProvider) {
        super(featurizer);
        this.labelProvider = labelProvider;
    }

    public void labelData(Dataset<DATA> dataset) {
        this.labeledData.clear();
        for (DATA object : dataset.getObjects()) {
            LABEL label = labelProvider.provideLabel(object);
            List<DATA> data = labeledData.get(label);
            data.add(object);
            this.labeledData.put(label, data);
        }
    }

    public LabelProvider<DATA, LABEL> getLabelProvider() {
        return labelProvider;
    }

    public HashMap<LABEL, List<DATA>> getLabeledData() {
        return labeledData;
    }
}