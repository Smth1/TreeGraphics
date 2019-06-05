package data.structures.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import main.java.shape.Circle;
import sun.reflect.generics.tree.Tree;

/**
 * Binary search tree.
 * Inherits isEmpty(), makeEmpty(), getRootItem(), and the
 * use of the constructors from tree.BinaryTreeBasis Assumption: A tree contains at
 * most one item with a given search key at any time.
 *
 * @author Roman
 * @version 1.0
 */
public class BinarySearchTree extends BinaryTreeBasis {

    /**
     * Binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Binary search tree.
     * @param rootKey
     * @Overload Default constructor
     */
    public BinarySearchTree(int rootKey) {
        super(rootKey);
    }

    /**
     * Inserts a new circle into the tree.
     * @param keyNumber
     */
    public TreeNode insertItem(Integer keyNumber) {

        TreeNode treeNode = insertItem(root,keyNumber);
        if(root == null)
            root = treeNode;
        return  treeNode;
    }

    /**
     * Inserts a new circle into the tree.
     * @param tNode a tree node
     * @return
     * @Overload insertItem()
     */
    protected TreeNode insertItem(TreeNode tNode,Integer keyNumber) {
        if (tNode == null) {
            tNode = new TreeNode(keyNumber);
            return tNode;
        }

        if(Objects.equals(keyNumber, tNode.getSearchKey())) {
            return tNode;
        }

        if (tNode.getSearchKey() > keyNumber && tNode.left == null) {
            tNode.left = new TreeNode(keyNumber);
            return tNode.left;
        }
        else if (tNode.getSearchKey() > keyNumber && tNode.left != null) {
            return  insertItem(tNode.left,keyNumber);
        } else if (tNode.getSearchKey() < keyNumber && tNode.right != null) {
            return insertItem(tNode.right,keyNumber);
        } else if (tNode.getSearchKey() < keyNumber && tNode.right == null) {
            tNode.right = new TreeNode(keyNumber);
            return tNode.right;
        }

        return null;
    }

    /**
     * Retrieves a circle from the tree.
     * @param searchKey a unique identifying value
     * @return An integer search key number
     */
    public TreeNode retrieveItem(Integer searchKey) {
        return retrieveItem(root, searchKey);
    }


    /**
     * Searches for a circle from the tree.
     * @param tNode a tree node
     * @param searchKey a unique identifying value
     * @return An integer search key number
     * @Overload retrieveItem()
     */
    protected TreeNode retrieveItem(TreeNode tNode, Integer searchKey) {
        if (tNode == null) {
            return null;

        } else {


            if (Objects.equals(searchKey, tNode.getSearchKey())) {
                return tNode;
            } else if (searchKey < tNode.getSearchKey()) {
                return retrieveItem(tNode.left, searchKey);
            } else {
                return retrieveItem(tNode.right, searchKey);
            }
        }
    }

    /**
     * Deletes a circle from the tree.
     * @param searchKey a unique identifying value
     * @throws TreeException if search key cannot be located.
     */
    public void deleteItem(Integer searchKey) throws TreeException {
        root =  deleteItem(root, searchKey);
    }

    /**
     * Deletes a circle from the tree.
     * @param tNode a tree node
     * @param searchKey a unique identifying value
     * @return A tree.TreeNode from within the tree
     * @Overload deleteItem()
     */
    protected TreeNode deleteItem(TreeNode tNode, Integer searchKey) {
        TreeNode newSubtree;

        if (tNode == null) {
            throw new TreeException("tree.TreeException: Item not found");
        }

        if (Objects.equals(searchKey, tNode.getSearchKey())) {
            tNode = deleteNode(tNode);

        } else if (searchKey < tNode.getSearchKey()) {
            newSubtree = deleteItem(tNode.left, searchKey);
            tNode.left = newSubtree;
        }

        else {
            newSubtree = deleteItem(tNode.right, searchKey);
            tNode.right = newSubtree;
        }

        return tNode;
    }

    public List<Integer> getPath(TreeNode tNode) {
        List<Integer> list = new ArrayList<>();

        if (tNode == null) {
            throw new TreeException("tree.TreeException: Item not found");
        }

        TreeNode nodeItem = this.root;
        while(true) {
            if (nodeItem == null)
                throw new TreeException("tree.TreeException: Item not found");
            list.add(nodeItem.getSearchKey());

            if (Objects.equals(tNode.getSearchKey(), nodeItem.getSearchKey())) {
                return list;

            } else if (tNode.getSearchKey() < nodeItem.getSearchKey()) {
                nodeItem = nodeItem.left;
            } else {
                nodeItem = nodeItem.right;

            }
        }

    }

    /**
     * Helper method finds and replaces a deleted node.
     * @param tNode A tree.TreeNode from within the tree
     * @return A tree.TreeNode from within the tree
     */
    protected TreeNode deleteNode(TreeNode tNode) {

        Integer replacementItem;

        if ((tNode.left == null) && (tNode.right == null)) {
            return null;
        }

        else if (tNode.left == null) {
            return tNode.right;
        }

        else if (tNode.right == null) {
            return tNode.left;
        } else {

            replacementItem = findLeftmost(tNode.right);
            tNode.searchKey = replacementItem;
            tNode.right = deleteLeftmost(tNode.right);
            return tNode;
        }
    }

    /**
     * Helper method for searching and deleting left-side nodes.
     * @param tNode
     * @return
     */
    protected Integer findLeftmost(TreeNode tNode) {
        if (tNode.left == null) {
            return tNode.getSearchKey();
        }
        return findLeftmost(tNode.left);
    }

    /**
     * Helper method for searching and deleting right-side nodes.
     * @param tNode
     * @return
     */
    protected TreeNode deleteLeftmost(TreeNode tNode) {
        if (tNode.left == null) {
            return tNode.right;
        }
        tNode.left = deleteLeftmost(tNode.left);
        return tNode;
    }

    /**
     * Gets the height of the tree
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * Gets the size of the tree
     * @param root
     * @return
     */
    public int getSize(TreeNode root) {
        if (root == null)
            return 0;
        return (getSize(root.left) + getSize(root.right)) + 1;
    }

    @Override
    public void setRootItem(TreeNode newItem) {
        root = new TreeNode(newItem.left, newItem.right, newItem.searchKey);
    }
}
