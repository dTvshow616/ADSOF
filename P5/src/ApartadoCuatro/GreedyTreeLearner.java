package ApartadoCuatro;

import ApartadoDos.DecisionTree;
import ApartadoDos.Node;

import java.util.*;
import java.util.function.Predicate;

public class GreedyTreeLearner<DATA extends Comparable<DATA>, LABEL> {
    DecisionTree<DATA> tree = new DecisionTree<>();
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
        List<String> availableFeatures = new ArrayList<>(dataSet.getFeaturizer().featureNames());

        Node<DATA> learnedRoot = buildTree(dataSet, availableFeatures);

        tree.getRootNode().getNextNodes().put(x -> true, learnedRoot);

        return tree;
    }

    public Node<DATA> buildTree(LabeledDataset<DATA, LABEL> dataset, List<String> availableFeatures) {
        DecisionTree<DATA> tree = new DecisionTree<>();
        HashMap<Object, List<DATA>> splitData = new HashMap<>();

        /* If all labels are the same return single node with that label */
        if (dataset.getLabeledData().size() == 1) {
            String label = dataset.getLabeledData().keySet().stream().toList().get(0).toString();
            Node<DATA> node = new Node<>(tree, label);
            tree.addNode(node);
            return node;
        }

        /* In case of draw, choose most common feature */
        if (availableFeatures.isEmpty()) {
            String commonLabel = getMostCommonLabel(dataset);
            Node<DATA> node = new Node<>(tree, commonLabel);
            tree.addNode(node);
            return node;
        }

        /* Choose best feature to split on */
        String feat = availableFeatures.get(new Random().nextInt(availableFeatures.size()));

        if (this.strategy != null) {
            feat = strategy.execute(dataset, availableFeatures);
        }

        /* Remove feature from the available features' list */
        List<String> remainingFeatures = new ArrayList<>(availableFeatures);
        remainingFeatures.remove(feat);

        /* Current node for this feature*/
        Node<DATA> currentNode = new Node<>(tree, feat);
        tree.addNode(currentNode);

        /* Split data into subsets based on feature */
        for (DATA object : dataset.getObjects()) {
            Object splitResult = dataset.getFeaturizer().featureValue(object, feat);
            List<DATA> splitDataList = splitData.get(splitResult);
            splitDataList.add(object);
            splitData.put(splitResult, splitDataList);
        }

        /* For each subset do */
        for (Map.Entry<Object, List<DATA>> entry : splitData.entrySet()) {
            Object value = entry.getKey();
            List<DATA> entryObjects = entry.getValue();
            LabeledDataset<DATA, LABEL> entryDataset =
                    new LabeledDataset<>(dataset.getFeaturizer(), dataset.getLabelProvider());
            entryDataset.addAll((DATA[]) entryObjects.toArray());

            /* Add "feat==value" condition */
            String finalFeat = feat;
            Predicate<DATA> condition = x -> dataset.getFeaturizer().featureValue(x, finalFeat).equals(value);

            /* Build subtree recursively */
            Node<DATA> childNode = buildTree(entryDataset, remainingFeatures);

            /* Add node to current node */
            currentNode.getNextNodes().put(condition, childNode);
        }

        return currentNode;
    }

    private String getMostCommonLabel(LabeledDataset<DATA, LABEL> dataset) {
        String majorityLabel = "";
        int maxCount = -1;
        for (Map.Entry<LABEL, List<DATA>> entry : dataset.getLabeledData().entrySet()) {
            if (entry.getValue().size() > maxCount) {
                maxCount = entry.getValue().size();
                majorityLabel = entry.getKey().toString();
            }
        }
        return majorityLabel;
    }

    public void setStrategy(FeatureSelectionStrategy<DATA> strategy) {
        this.strategy = strategy;
    }
}