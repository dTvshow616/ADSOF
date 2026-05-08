package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Feature;

import java.util.List;
import java.util.Random;

public class GreedyTreeLearner<DATA extends Comparable<DATA>, LABEL> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    GreedyTreeLearner() {
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public DecisionTree<DATA> learn(LabeledDataset<DATA, LABEL> dataSet) {
        return null; //DUE
    }

    public DecisionTree<DATA> buildTree(LabeledDataset<DATA, LABEL> dataset, List<Feature<DATA>> availableFeatures) {
        DecisionTree<DATA> tree = new DecisionTree<>();
        /* If all labels are the same return single node with that label */
        if (dataset.getLabeledData().size() == 1) {
            tree.node(dataset.getLabeledData().keySet().stream().toList().get(0).toString());
            return tree;
        }

        Feature<DATA> feat = availableFeatures.get(
                new Random().nextInt(availableFeatures.size())); // elegir la "mejor" feature -> de
        // momento aleatoria
        availableFeatures.remove(feat); // se borra el feat de la lista de features disponible
        /* Split data into subsets based on feat */
        dataset.labelData(dataset);

        /* For-each subset do: build subtree recursively */
        for (LABEL label : dataset.getLabeledData().keySet()) {
            List<DATA> data = dataset.getLabeledData().get(label);
            //buildTree();
        }
        dataset.getLabeledData().forEach((LABEL, LIST_DATA) -> {
            //LABEL == feat ? buildTree();
        });
        // añadir la condition "feat==value" y llamada recursiva con el subconjunto de data { x in data | x.feat ==
        // value }
        return null;
    }
}