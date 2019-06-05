package data.structures.BinaryTree;

import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Iterates through the tree using the java.util.Iterator<E> interface.
 * @author Roman
 * @version 1.0
 */
public final class TreeIterator implements Iterator<Integer> {

    private BinarySearchTree binaryTree;
    private TreeNode currentNode;
    private LinkedList<TreeNode> queue;

    /**
     * Iterates through the tree using the java.util.Iterator<E> interface.
     * @param binaryTree the abstract binary tree class
     */
    public TreeIterator(BinarySearchTree binaryTree) {
        this.binaryTree = binaryTree;
        currentNode = null;
        queue = new LinkedList<>();
    }

    public TreeIterator(BinarySearchTree binaryTree,TreeNode node) {
        this.binaryTree = binaryTree;
        this.currentNode = node;
        queue = new LinkedList<>();
    }

    /**
     * Determines if there are elements in the queue.
     * @Return true if the iteration has more elements
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * Gets the next element in the queue.
     * @return An <code>Integer</code> numbered search key.
     */
    public Integer next() throws NoSuchElementException {
        try {
            currentNode = queue.remove();
            return currentNode.getSearchKey();
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public boolean hasCurrNode() {
        return currentNode != null;
    }

    public Integer nextNode() throws NoSuchElementException {
        try {
            return currentNode.getSearchKey();
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public Boolean hasLeft() throws NoSuchElementException {
        try {
            return currentNode.left != null;
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public TreeIterator toLeft() throws NoSuchElementException {
        try {
            return new TreeIterator(binaryTree,currentNode.left);
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public Integer getLeft() throws NoSuchElementException {
        try {

            return currentNode.left.getSearchKey();
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public Boolean hasRight() {
        return currentNode.right != null;
    }

    public TreeIterator toRight() throws NoSuchElementException {
        try {
            return new TreeIterator(binaryTree,currentNode.right);
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }

    public Integer getRight() throws NoSuchElementException {
        try {

            return currentNode.right.getSearchKey();
        } catch (QueueException e) {
            throw new NoSuchElementException();
        }
    }


    /**
     * Unsupported remove operation. Throws an exception when invoked.
     */
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the tree traversal to in-order
     */
    public void setPreorder() {
        queue.clear();
        preorder(binaryTree.root);
    }

    /**
     * Recursively traverses the tree in-order
     * @param treeNode A tree with nodes
     */
    private void preorder(TreeNode treeNode) {
        if (treeNode != null) {
            queue.add(treeNode);
            preorder(treeNode.left);
            preorder(treeNode.right);
        }
    }

    /**
     * Sets the tree traversal to in-order
     */
    public void setInorder() {
        queue.clear();
        inorder(binaryTree.root);
    }

    /**
     * Recursively traverses the tree in-order
     * @param treeNode A tree with nodes
     */
    private void inorder(TreeNode treeNode) {
        if (treeNode != null) {
            inorder(treeNode.left);
            queue.add(treeNode);
            inorder(treeNode.right);
        }
    }

    /**
     * Sets the tree to traverse in post-order
     */
    public void setPostorder() {
        queue.clear();
        postorder(binaryTree.root);
    }

    /**
     * Recursively traverses the tree post-order
     * @param treeNode A tree with nodes
     */
    private void postorder(TreeNode treeNode) {
        if (treeNode != null) {
            postorder(treeNode.left);
            postorder(treeNode.right);
            queue.add(treeNode);
        }
    }
}