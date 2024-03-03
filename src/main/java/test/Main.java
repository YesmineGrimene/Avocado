package test;

import Enums.Category;
import entities.Recette;
import services.RecetteService;
import utils.MyDataBase;

import java.sql.SQLException;

public class Main {



    public static void main(String[] args) {
        MyDataBase db = MyDataBase.getInstance();
        RecetteService rs = new RecetteService() ;
        Recette recette1 = new Recette("Omek Houria",5,"Sfenerya w ter7iha kahaw ", Category.VEG);
        Recette recette2 = new Recette("mloukhia",5,"kesha", Category.NON_VEG);
        Recette recette3 = new Recette("Makrouna",2,"cream fraiche", Category.VEG);
        Recette recette4 = new Recette("rouz",4,"maktouue rouz", Category.VEG);

        try {
            //rs.ajouterRecette(recette3);
            //rs.modifierRecette(1, recette2);
            //rs.supprimerRecette(3);
            //rs.afficherRecettes();
            //rs.RechercherRecette("mlkhia");
            rs.RechercherRecetteParCategorie(Category.VEG);

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
