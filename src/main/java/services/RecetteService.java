package services;
import entities.Recette;
import Enums.Category;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecetteService implements IRecette{
    private final Connection connection;

    public RecetteService() {
        this.connection = MyDataBase.getInstance().getConnection() ;
    }


    @Override
    public void ajouterRecette(Recette R) throws SQLException {
        String sql = "INSERT INTO recette (Titre, Rate, Content, Category) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, R.getTitre());
        ps.setInt(2, R.getRate());
        ps.setString(3, R.getContent());
        ps.setString(4, R.getCategory().toString());
        ps.executeUpdate();
    }

    @Override
    public void modifierRecette(String Titre, Recette recette) throws SQLException {
        String sql = "UPDATE recette SET Titre = ?, Rate = ?, Content = ?, Category = ? WHERE Titre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, recette.getTitre());
        ps.setInt(2, recette.getRate());
        ps.setString(3, recette.getContent());
        ps.setString(4, recette.getCategory().toString());
        ps.setString(5, Titre);
        ps.executeUpdate();
    }

    @Override
    public void supprimerRecette(String Titre) throws SQLException {
        String sql = "DELETE FROM recette WHERE Titre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, Titre);
        ps.executeUpdate();
    }

    @Override
    public List<Recette> afficherRecettes() throws SQLException {
        String sql = "SELECT * FROM recette";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Recette> recettes = new ArrayList<>();
        while (resultSet.next()) {
            Recette recette = new Recette();
            recette.setTitre(resultSet.getString("Titre"));
            recette.setRate(resultSet.getInt("Rate"));
            recette.setContent(resultSet.getString("Content"));
            recette.setCategory(Category.valueOf(resultSet.getString("Category")));
            recettes.add(recette);
            System.out.println("Titre: " + recette.getTitre() +
                    ", Rate: " + recette.getRate() +
                    ", Content: " + recette.getContent() +
                    ", Category: " + recette.getCategory());
        }

        return recettes;
    }

    @Override
    public void RechercherRecette(String titre) throws SQLException {
        String sql = "SELECT * FROM recette WHERE Titre = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, titre);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            System.out.println("Titre: " + resultSet.getString("Titre") +
                    ", Rate: " + resultSet.getInt("Rate") +
                    ", Content: " + resultSet.getString("Content") +
                    ", Category: " + resultSet.getString("Category"));
        } else System.out.println("Doesn't exist !");

    }

    @Override
    public void RechercherRecetteParCategorie(Category category) throws SQLException {
        String sql = "SELECT * FROM recette WHERE Category = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, category.toString());
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            System.out.println("Titre: " + resultSet.getString("Titre") +
                    ", Rate: " + resultSet.getInt("Rate") +
                    ", Content: " + resultSet.getString("Content") +
                    ", Category: " + resultSet.getString("Category"));
        } else System.out.println("Doesn't exist !");
    }
}
