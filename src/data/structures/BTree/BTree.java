/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures.BTree;

/**
 *
 * @author Roman
 */

import java.util.*;
import javafx.util.Pair;

public class BTree<T extends Comparable<T>> {

    BNode<T> root; // root of the tree
    int height; // height of the tree
    int minimumDegree; // fixed number of keys in a node

    public BTree() {
        // set minimumDegree equals 3
        this.minimumDegree = 3;
        this.height = 0;
    }

    public Pair<BNode<T>,Integer> search(BNode<T> node, T key) {
        int i = 0;

        //
        while(i < node.keyNumber && key.compareTo(node.keys.get(i)) > 0 ) {   //  x.key < k
            i++;
        }

        if (i < node.keyNumber && key.compareTo(node.keys.get(i)) == 0) {
            return new Pair(node,i);
        }
        else if (node.isLeaf) {
            return null;
        }
        else{
            return search(node.subTrees.get(i),key);
        }
    }

    public void diskWrite(BNode <T> n) {

    }

    public BNode<T> allocateNode() {
        return new BNode();
    }

    void treeCreate() {
        BNode<T> x;

        //Allocate memory for an empty node
        x = BNode.allocateNewNode();
        x.isLeaf = true;
        x.keyNumber = 0;
        root = x;
    }

    void treeSpleetChild(BNode<T> x,int i)  { // parameters: node,index
        BNode<T> y,z;
        int j;
        z = allocateNode();
        y = x.subTrees.get(i);
        z.isLeaf = y.isLeaf;
        z.keyNumber = this.minimumDegree-1;
        for (j = 0; j < this.minimumDegree - 1; j++) { //move half the keys to the node z
            z.keys.add(y.keys.get(j + this.minimumDegree));
        }
        if (!y.isLeaf) { //move half the subtrees to the node z
            for (j = 0; j < this.minimumDegree; j++) {
                z.subTrees.add(y.subTrees.get(j + this.minimumDegree));
            }
        }

        y.keyNumber = this.minimumDegree - 1;

        //for(j = y.keyNumber;j > this.minimumDegree - 1; j--) { //
        //  y.subTrees.remove(j);
        //}

        x.subTrees.add(null);
        for (j = x.keyNumber; j >= i + 1; j--) { //shift by 1 and insert to subtree key z
            x.subTrees.set(j + 1, x.subTrees.get(j));
        }

        x.subTrees.set(i + 1, z);

        x.keys.add(null);
        for (j = x.keyNumber - 1; j >= i; j--) { // shift by 1 and insert key z
            x.keys.set(j + 1, x.keys.get(j));
        }

        x.keys.set(i, y.keys.get(this.minimumDegree -1));

        for(j = y.keys.size() - 1; j >= y.keyNumber; j--) {
            y.keys.remove(j);
        }

        x.keyNumber++;

        //diskWrite(y);
        //diskWrite(z);
        //diskWrite(x);
    }

    void treeInsert(T key) {
        BNode<T> r = this.root;
        BNode s;
        if (r.keyNumber == 2*this.minimumDegree - 1) {
            s = BNode.allocateNewNode();
            this.root = s;
            s.isLeaf = false;
            s.keyNumber = 0;
            s.subTrees.add(r);
            this.treeSpleetChild(s, 0);
            this.treeInsertNonFull(s, key);
        }
        else {
            this.treeInsertNonFull(r, key);
        }
    }

    void treeInsertNonFull(BNode<T> x,T key) {
        int i;
        i = x.keyNumber - 1;
        if (x.isLeaf) {
            x.keys.add(key);
            while(i >=0 && key.compareTo(x.keys.get(i)) < 0) { // k < x.key[i]
                x.keys.set(i + 1, x.keys.get(i));
                i--;
            }
            x.keys.set(i+1,key);
            x.keyNumber ++;
            //diskWrite(x);
        }
        else {
            while(i>=0 && key.compareTo(x.keys.get(i)) < 0) { // k < x.key[i]
                i--;
            }
            i++;
            //diskRead(x.c[i]);
            if (x.subTrees.get(i) == null) {     // print
                System.out.println("null");
                for (int j=0;j<x.keys.size();j++) {
                    System.out.println(x.keys.get(i));
                }
            }
            if (x.subTrees.get(i).keyNumber == this.minimumDegree * 2 - 1) {
                this.treeSpleetChild(x, i);
                if (key.compareTo(x.keys.get(i)) > 0) {
                    i++;
                }

            }
            this.treeInsertNonFull(x.subTrees.get(i), key);
        }
    }

    public void printToConsole() {

    }
}

