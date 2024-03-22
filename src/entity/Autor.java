package entity;

public class Autor {
    private int id;
    private String name;
    private String nationality;

    public Autor(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.nationality = country;
    }

    public Autor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String country) {
        this.nationality = country;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + nationality + '\'' +
                '}';
    }
}
