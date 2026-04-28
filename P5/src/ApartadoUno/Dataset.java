package ApartadoUno;

import java.util.List;

public class Dataset<T> {
    private final Featurizer<T> featurizer;
    private List<T> objects;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public Dataset(Featurizer<T> featurizer) {
        this.featurizer = featurizer;
    }

    public void addAll(T[] objects) {
        this.objects.addAll(List.of(objects));
    }

    public void addData(T object) {
        // DUE
    }

    public List<T> getData() {
        return null; //DUE
    }
}