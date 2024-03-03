package Controllers;

import Enums.Category;
import entities.Recette;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.RecetteService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DisplayAllRecipesController {

    @FXML
    private TableColumn<Recette, Category> idCategory;

    @FXML
    private TableColumn<Recette, String> idContent;

    @FXML
    private TableColumn<Recette, Integer> idRate;

    @FXML
    private TableColumn<Recette, String> idtitre;

    @FXML
    private TableView<Recette> tableView;
    private final RecetteService recetteService = new RecetteService();
    private Recette selectedRecette;

    @FXML
    void initialize() {
        try {
            List<Recette> recettes = recetteService.afficherRecettes();
            ObservableList<Recette> observableList = FXCollections.observableList(recettes);
            tableView.setItems(observableList);

            idtitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            idRate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
            idContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
            idCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimer(javafx.event.ActionEvent actionEvent) {
        Recette selectedRecette = tableView.getSelectionModel().getSelectedItem();

        if (selectedRecette != null) {
            try {
                String Titre = selectedRecette.getTitre();
                recetteService.supprimerRecette(Titre);
                tableView.getItems().remove(selectedRecette);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void openModificationWindow(Recette selectedRecette, ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifyRecipe.fxml"));
            Parent root = loader.load();

            // Get the controller of the new window
            ModifyRecipeController modifyRecipeController = loader.getController();

            // Pass the selected recipe to the controller
            modifyRecipeController.initData(selectedRecette);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException here (e.g., show an error message)
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle any other exception here
        }
    }

    @FXML
    void modifierAction(javafx.event.ActionEvent actionEvent) {
        Recette selectedRecette = tableView.getSelectionModel().getSelectedItem();

        if (selectedRecette != null) {
            openModificationWindow(selectedRecette, actionEvent);
        } else {
            System.out.println("Aucune Recette sélectionné pour la modification.");
        }
    }
}
