package ms.org.model;


import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    public Restaurant() {}

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
}
