package ms.org.model;


import jakarta.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;
    private double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public MenuItem() {}

    public MenuItem(String itemName, double price, Restaurant restaurant) {
        this.itemName = itemName;
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public Restaurant getRestaurant() { return restaurant; }

    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setPrice(double price) { this.price = price; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}
