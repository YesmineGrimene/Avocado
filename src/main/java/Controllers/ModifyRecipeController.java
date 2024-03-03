package Controllers;

import Enums.Category;
import entities.Recette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.RecetteService;

import java.sql.SQLException;

public class ModifyRecipeController {

    @FXML
    private ComboBox<Category> idCat; // ComboBox for category selection

    @FXML
    private TextArea idContent;

    @FXML
    private Button idDisplay;

    @FXML
    private TextField idRate;

    @FXML
    private TextField idtitre;

    private Recette r;

    public void initData(Recette selectedRecette) {
        r = selectedRecette;
        idtitre.setText(selectedRecette.getTitre());
        idRate.setText(String.valueOf(selectedRecette.getRate()));
        idContent.setText(selectedRecette.getContent());

        // Populate the category ComboBox
        idCat.getItems().setAll(Category.values());
        idCat.setValue(selectedRecette.getCategory()); // Set the initial value
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws SQLException {
        String oldTitle = r.getTitre();
        Integer newRate;
        String newName;
        String newContent;
        Category newCategory; // Define newCategory variable

        try {
            newName = idtitre.getText();
            newRate = Integer.parseInt(idRate.getText());
            newContent = idContent.getText();
            newCategory = idCat.getValue(); // Get selected category from ComboBox

            System.out.println("New Name: " + newName);
            System.out.println("New Rate: " + newRate);
            System.out.println("New Content: " + newContent);
            System.out.println("New Category: " + newCategory);

            Recette editedRecette = new Recette();
            editedRecette.setTitre(newName);
            editedRecette.setRate(newRate);
            editedRecette.setContent(newContent);
            editedRecette.setCategory(newCategory);

            System.out.println("Edited Recette: " + editedRecette);

            RecetteService recetteService = new RecetteService();
            recetteService.modifierRecette(oldTitle, editedRecette);

            System.out.println("mise à jour avec succés");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Recette modifiée avec succès");
            alert.showAndWait();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors de la modification de la recette.");
            alert.showAndWait();
        }
    }
}
