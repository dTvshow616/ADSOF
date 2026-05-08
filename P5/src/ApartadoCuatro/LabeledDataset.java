package ApartadoCuatro;

import ApartadoUno.Dataset;
import ApartadoUno.Featurizer;

public class LabeledDataset<G, U> extends Dataset<G> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    LabeledDataset(Featurizer<G> featurizer, LabelProvider<U> labelProvider) {
        super(featurizer);
        //DUE
    }
}