package data.structures.BinaryTree;

/**
 * A binary tree using circle objects.
 * @author Roman
 * @version 1.0
 */
public class TreeNode {


    public TreeNode left;
    public TreeNode right;

    protected Integer searchKey = -1;

    /**
     * A binary tree using circle objects.
     * @param left a left subtree
     * @param right a right subtree
     */
    public TreeNode(TreeNode left, TreeNode right,int key) {
        this.left = left;
        this.right = right;
        this.searchKey = key;
    }

    public TreeNode(int key) {
        this.left = null;
        this.right = null;
        this.searchKey = key;
    }

    public Integer getSearchKey() {
        return this.searchKey;
    }

}