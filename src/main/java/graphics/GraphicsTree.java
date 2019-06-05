package main.java.graphics;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import data.structures.BinaryTree.*;
import main.java.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Draws the tree and updates the graphics to display according to the
 * searching, inserting, deleting, and traversal options.
 * @author Eric Canull
 * @version 1.0
 */
@SuppressWarnings("serial")
public final class GraphicsTree extends Canvas {

    /**
     * The initial input values for the tree.
     */
    private static final Integer[] NUMBERS_ARRAY = { 50, 25, 30, 12, 10, 75, 70, 80, 110 };

    private BinarySearchTree tree;  	// The BST
    private TreeIterator treeIterator;  // The BST Iterator
    private Circle insertCircle;        // Insert circle
    private int maxTreeHeight; 			// Max tree height;

    /**
     * Draws the tree and updates the graphics to display according to the
     * searching, inserting, deleting, and traversal options.
     */
    public GraphicsTree(BinarySearchTree root) {

        widthProperty().addListener(evt -> drawTree());
        heightProperty().addListener(evt -> drawTree());
        setTree(root);
        createTree();
    }

    /**
     * Changes the tree rendered by this panel.
     */
    public void setTree(BinarySearchTree root) {  tree = root; }

    /**
     * Creates the initial binary search tree with the default values
     * in the numbers array.
     */
    public void createTree() {

        // Create an empty tree
        setMaxTreeHeight(7); 		   // Set the default max tree height

        for (Integer number : NUMBERS_ARRAY) {
            tree.insertItem(number);
        }

        drawTree();
    }

    /**
     * Set the max tree height.
     * @param size a <code>Integer</code> number for the tree max size
     * @return An <code>Integer</code> max tree size
     */
    private int setMaxTreeHeight(int size) {
        this.maxTreeHeight = size;
        return size;
    }

    /**
     * Searches for an search key number in the tree. If the number is found the tree will
     * be repainted to show the path. If the number cannot be found a notification
     * message will be displayed.
     *
     *  searchKey a <code>Integer</code> number for finding a tree.TreeNode
     */


    public void drawSearch(TreeNode node) {//Найден узел node - рисуем дерево
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        TreeIterator iterator = new TreeIterator(tree,tree.root);
        ListIterator<Integer> list = tree.getPath(node).listIterator();

        // If the tree is not empty; draw the lines and circles
        if (tree.root != null) {
            int treeHeight = tree.getHeight(tree.root);

            // Get the tree height
            drawTree(gc, iterator, list, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
            drawCircles(gc, iterator,list, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
        }
    }

    public void drawInsert(TreeNode node) {//Вставлен узел node - рисуем дерево
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        List<Integer> list = new ArrayList<>();
        list.add(node.getSearchKey());
        TreeIterator iterator = new TreeIterator(tree,node);
        ListIterator<Integer> nodeList = list.listIterator();

        // If the tree is not empty; draw the lines and circles
        if (tree.root != null) {
            int treeHeight = tree.getHeight(tree.root);

            // Get the tree height
            drawTree(gc, iterator, nodeList, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
            drawCircles(gc, iterator, nodeList, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
        }
    }

    /**
     * Prints the tree traversal order to the upper left-hand
     * side of the screen.
     * @return outputString
     */
    public String printTree() {

        // Traversal text output string
        StringBuilder outputString = new StringBuilder();

        // Add the next tree iterator to the output
        while (treeIterator.hasNext()) {
            outputString.append(treeIterator.next()).append(" ");
        }

        return outputString.toString(); // return the output string
    }

    /**
     * Retrieves the pre-order traversal option.
     */
    public void setPreorder() {
        treeIterator = new TreeIterator(tree);
        treeIterator.setPreorder();
    }

    /**
     * Retrieves the in-order traversal option.
     */
    public void setInorder() {
        treeIterator = new TreeIterator(tree);
        treeIterator.setInorder();
    }

    /**
     * Retrieves the post-order traversal option.
     */
    public void setPostorder() {
        treeIterator = new TreeIterator(tree);
        treeIterator.setPostorder();
    }



    /**
     * Deletes all the nodes in the tree.
     */
    public void makeEmpty() {
        tree.makeEmpty();
        maxTreeHeight = 6;
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Draws the binary tree on the component.
     */
    public void drawTree() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        List<Integer> list = new ArrayList<>();
        TreeIterator iterator = new TreeIterator(tree,tree.root);
        ListIterator<Integer> nodeList = list.listIterator();

        // If the tree is not empty; draw the lines and circles
        if (tree.root != null) {
            int treeHeight = tree.getHeight(tree.root);

            // Get the tree height
            drawTree(gc, iterator, nodeList, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
            drawCircles(gc, iterator, nodeList, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
        }
    }

    /**
     * Draws the lines recursively until there are no more tree nodes.
     * @param gc graphics2D class for extending drawing tools
     * @param iterator a tree with <code>integer</code> index numbers
     * @param xMin the minimum width to draw on the component
     * @param xMax the minimum width to draw on the component
     * @param yMin the maximum width to draw on the component
     * @param yMax the maximum height to draw on the component
     */
    protected void drawTree(GraphicsContext gc, TreeIterator iterator,ListIterator<Integer> listIterator, double xMin, double xMax, double yMin, double yMax) {

        Point2D linePoint1; 	// Point_1
        Point2D linePoint2;   // Point_2
        Line newLine = new Line();  // Blank line
        Integer key;

        if (listIterator.hasNext())
            key = listIterator.next();
        else
            key = null;

        if (!iterator.hasCurrNode())
            return;

        if (key == iterator.nextNode())
            key = listIterator.next();




        // If left node is not null then draw a line to it
        if (iterator.hasLeft()) {
            newLine.setHighlighter(false);


            if (key == iterator.getLeft()) {
                newLine.setHighlighter(true);
            }

            // Determine the start and end points of the line
            linePoint1 = new Point2D(((xMin + xMax) / 2), yMin + yMax / 2);
            linePoint2 = new Point2D(((xMin + (xMin + xMax) / 2) / 2), yMin + yMax + yMax / 2);
            newLine.setPoint(linePoint1, linePoint2);// Set the points
            newLine.draw(gc);// Draw the line

            // Recurse left circle nodes
            drawTree(gc, iterator.toLeft(), listIterator, xMin, (xMin + xMax) / 2, yMin + yMax, yMax);
        }

        // If right node is not null then draw a line to it
        if (iterator.hasRight()) {
            newLine.setHighlighter(false);


            if (key == iterator.getRight()) {
                newLine.setHighlighter(true);
            }

            // Determine the start and end points of the line
            linePoint1 = new Point2D((xMin + xMax) / 2, yMin + yMax / 2);
            linePoint2 = new Point2D((xMax + (xMin + xMax) / 2) / 2, yMin + yMax + yMax / 2);
            newLine.setPoint(linePoint1, linePoint2);
            newLine.draw(gc);// Draw the line

            // Recurse right circle nodes
            drawTree(gc, iterator.toRight(), listIterator, (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
        }

    }

    /**
     * Draws circles for every root, parent and child tree nodes.
     * @param gc graphics2D class for expanding the drawing tools
     * @param iterator a tree with <code>Integer</code> index numbers
     * @param xMin the minimum width to draw on the component
     * @param xMax the maximum width to draw on the component
     * @param yMin the minimum height to draw on the component
     * @param yMax the maximum height to draw on the component
     */
    public void drawCircles(GraphicsContext gc, TreeIterator iterator,ListIterator<Integer> listIterator, double xMin, double xMax, double yMin, double yMax) {

        // Create a new point
        Point2D point = new Point2D((xMin + xMax) / 2, yMin + yMax / 2);
        main.java.shapes.Circle circle;
        if (iterator.hasCurrNode())
            circle = new main.java.shapes.Circle(iterator.nextNode(),point);
        else
            return;

        Integer key;

        if (listIterator.hasNext())
            key = listIterator.next();
        else
            key = null;

        // treeNodes are flagged for highlight: Search and insertion nodes
        if (iterator.nextNode() == key) {
            circle.setHighlighter(true);
            // default no highlight
        } else {
            circle.setHighlighter(false);
        }

        circle.draw(gc);

        // Recurse left circles
        if (iterator.hasLeft()) {
            drawCircles(gc, iterator.toLeft(), listIterator, xMin, (xMin + xMax) / 2, yMin + yMax,	yMax);
        }

        // Recurse right circles
        if (iterator.hasRight()) {
            drawCircles(gc, iterator.toRight(), listIterator, (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
        }
    }

    public void clearCanvas() {
        getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}


