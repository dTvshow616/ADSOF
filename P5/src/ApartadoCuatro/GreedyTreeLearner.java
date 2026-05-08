package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoUno.Dataset;
import java.util.*;

public class GreedyTreeLearner<DATA extends Comparable<DATA>, LABEL> {
    private FeatureSelectionStrategy<DATA> strategy;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    GreedyTreeLearner() {
        this.strategy = null;
    }

    GreedyTreeLearner(FeatureSelectionStrategy<DATA> strategy) {
        this.strategy = strategy;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public DecisionTree<DATA> learn(LabeledDataset<DATA, LABEL> dataSet) {
        return buildTree(dataSet, dataSet.getFeaturizer().featureNames());
    }

    public DecisionTree<DATA> buildTree(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures) {
        DecisionTree<DATA> tree = new DecisionTree<>();
        HashMap<Object, List<DATA>> splitData = new HashMap<>();

        /* If all labels are the same return single node with that label */
        if (dataset.getLabeledData().size() == 1) {
            tree.node(dataset.getLabeledData().keySet().stream().toList().get(0).toString());
            return tree;
        }

        /* Choose best feature to split on */
        String feat = availableFeatures.get(new Random().nextInt(availableFeatures.size()));

        if(this.strategy != null){
            feat = strategy.execute(dataset,availableFeatures);
        } 

        /* Remove feature from the available features' list */
        availableFeatures.remove(feat);

        /* Split data into subsets based on feature */
        for (DATA object : dataset.getObjects()) {
            Object splitResult = dataset.getFeaturizer().featureValue(object, feat);
            List<DATA> splitDataList = splitData.get(splitResult);
            splitDataList.add(object);
            splitData.put(splitResult, splitDataList);
        }

        /* For-each subset do: build subtree recursively */
        for (Object splitResult : splitData.keySet()) {
            Dataset<DATA> splitDataSet = new Dataset<>(dataset.getFeaturizer());
            splitDataSet.addAll((DATA[]) splitData.get(splitResult).toArray());
            DecisionTree<DATA> splitTree = new DecisionTree<>();
            //buildTree();
        }
        dataset.getLabeledData().forEach((LABEL, LIST_DATA) -> {
            //LABEL == feat ? buildTree();
        });
        // añadir la condition "feat==value" y llamada recursiva con el subconjunto de data { x in data | x.feat ==
        // value }
        return null;
    }

    public void setStrategy(FeatureSelectionStrategy<DATA> strategy) {
        this.strategy = strategy;
    }
}