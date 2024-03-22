package entity;

public class Book {
    private int id;
    private String title;
    private String publicationYear;
    private double price;
    private int autor;

    public Book(int id, String title, String publicationYear, double price, int autor) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.autor = autor;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAutor() {
        return autor;


    }

    public void setIdAutor(int autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", idAutor=" + autor +
                '}';
    }
}
