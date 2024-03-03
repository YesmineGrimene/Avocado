package services;

import Enums.Category;
import entities.Recette;

import java.sql.SQLException;
import java.util.List;

public interface IRecette {
    void ajouterRecette (Recette R) throws SQLException;
    void modifierRecette (String Titre, Recette R) throws SQLException;
    void supprimerRecette(String id) throws SQLException;
    List<Recette> afficherRecettes() throws SQLException ;
    void RechercherRecette(String titre) throws SQLException ;
    // void RechercherRecetteParIngredient (...) throws SQLException ;
    void RechercherRecetteParCategorie (Category category) throws SQLException ;

    //List<Ingredient> recuperer() throws SQLException;

}
