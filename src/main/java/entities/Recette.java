package entities;
import Enums.Category;
public class Recette {
    private int id;
    private String Titre;
    private int Rate;
    private String Content;

    private Category category ;
    public Recette() {
    }


    public Recette(String titre, int rate, String content, Category category) {
        Titre = titre;
        Rate = rate;
        Content = content;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "Titre='" + Titre + '\'' +
                ", Rate=" + Rate +
                ", Content='" + Content + '\'' +
                ", category=" + category +
                '}';
    }
}
