package Controllers;

import Enums.Category;
import entities.Recette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.RecetteService;

import java.io.IOException;
import java.sql.SQLException;

public class AddRecipeController {

    @FXML
    private ComboBox<?> idCat;

    @FXML
    private TextArea idContent;

    @FXML
    private TextField idRate;

    @FXML
    private TextField idtitre;

    private final RecetteService RS = new RecetteService();

    @FXML
    void ajouter(ActionEvent event) {
        try {
            String categoryText = idCat.getSelectionModel().getSelectedItem().toString().toUpperCase().trim();
            Category category = Category.valueOf(categoryText);
            RS.ajouterRecette(new Recette(
                    idtitre.getText(),
                    Integer.parseInt(idRate.getText()),
                    idContent.getText(),
                    category
            ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Recette ajoutée avec succès");
            alert.showAndWait();
        } catch (IllegalArgumentException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Erreur lors de la conversion des enums : " + e.getMessage());
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void naviguer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayAllRecipes.fxml"));
            Parent root = loader.load();
            idtitre.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}