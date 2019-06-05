package main.java.graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import data.structures.BinaryTree.*;


/**
 * Constructs the GUI components and performs events for displaying and
 * manipulating the binary tree.
 * @author Eric Canull
 * @version 1.0
 */
public final class GraphicsController implements Initializable {

    // Panels and other GUI components
    @FXML private BorderPane root_container;
    @FXML private BorderPane root_container2;
    @FXML private TextArea traversal_textarea;
    @FXML private TextField input_field;

    // The center panel for drawing the tree
    private main.java.graphics.GraphicsTree graphicsTree;
    BinarySearchTree bstTree;
    /**
     * Constructs the GUI components and performs events for displaying and
     * changing the data in the binary tree.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bstTree = new BinarySearchTree();

        graphicsTree = new GraphicsTree(bstTree);
        // Add the panels onto the border pane
        root_container.setCenter(graphicsTree);

        // Bind canvas size to stack pane size.
        graphicsTree.widthProperty().bind(root_container.widthProperty());
        graphicsTree.heightProperty().bind(root_container.heightProperty().subtract(50));
    }

    /**
     *  Performs the action when the search button is clicked.
     */

    @FXML private void searchOnAction(ActionEvent event) {
        try {
            TreeNode node = bstTree.retrieveItem(Integer.parseInt(input_field.getText().trim()));
            graphicsTree.drawSearch(node);
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Error searching for value. The input field can only accept numbers.", ButtonType.OK);

            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());

        }
    }


    /**
     * Performs the action when the delete button is clicked.
     */

    @FXML private void deleteOnAction(ActionEvent event) {
        try {
            bstTree.deleteItem(Integer.parseInt(input_field.getText().trim()));
            graphicsTree.drawTree();
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting value. The input field can only accept numbers.",
                    ButtonType.OK);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }


    private void clearTree() {
        graphicsTree.makeEmpty();
        traversal_textarea.setText("");
    }
    /**
     * Performs the action when the clear button is clicked.
     */
    @FXML private void clearOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to empty the tree?", ButtonType.OK);
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> clearTree());

    }

    /**
     *  Performs the action when the insert button is clicked.
     */

    @FXML private void insertOnAction(ActionEvent event) {
        try {

            bstTree.insertItem(Integer.parseInt(input_field.getText().trim()));
            graphicsTree.drawTree();
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error inserting value. The input field can only accept numbers.",
                    ButtonType.OK);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());
        }
    }


    /**
     * Performs the action when the first traversal button is clicked.
     */
    @FXML private void inorderOnAction(ActionEvent event) {
        graphicsTree.setInorder();
        traversal_textarea.setText(graphicsTree.printTree());
    }

    /**
     *  Performs the action when the second traversal button is clicked.
     */
    @FXML private void preorderOnAction(ActionEvent event) {
        graphicsTree.setPreorder();
        traversal_textarea.setText(graphicsTree.printTree());
    }

    /**
     *  Performs the action when the third traversal button is clicked.
     */
    @FXML private void postorderOnAction(ActionEvent event) {
        graphicsTree.setPostorder();
        traversal_textarea.setText(graphicsTree.printTree());
    }
}
