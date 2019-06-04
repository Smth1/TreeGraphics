package data.structures.BTree;

import java.util.ArrayList;
import java.util.List;

class BNode<T extends Comparable> {
    int keyNumber;
    List<T> keys;
    boolean isLeaf;
    List<BNode> subTrees;

    BNode() {
        keys = new ArrayList<>();
        subTrees = new ArrayList<>();
    }

    public static BNode allocateNewNode() {
        return new BNode<>();
    }

    public void add(T x) {

    }

    public void add(BNode x) {
        this.subTrees.add(x);
    }
}