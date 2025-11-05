package ms.org.main;

import ms.org.model.*;
import ms.org.service.MenuService;
import ms.org.db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuService service = new MenuService();

        // Ensure one restaurant exists
        Restaurant restaurant = createDefaultRestaurant();

        while (true) {
            System.out.println("\n===== 🍽️ FOOD MENU MANAGEMENT =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Menu Items");
            System.out.println("3. Update Menu Item Price");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    MenuItem item = new MenuItem(name, price, restaurant);
                    service.addMenu(item);
                    break;
                case 2:
                    List<MenuItem> list = service.viewMenu();
                    System.out.println("\n--- Menu Items ---");
                    for (MenuItem i : list)
                        System.out.println(i.getId() + ". " + i.getItemName() + " - ₹" + i.getPrice());
                    break;
                case 3:
                    System.out.print("Enter Item ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter new Price: ");
                    double newPrice = sc.nextDouble();
                    service.updateMenu(uid, newPrice);
                    break;
                case 4:
                    System.out.print("Enter Item ID to delete: ");
                    int did = sc.nextInt();
                    service.deleteMenu(did);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static Restaurant createDefaultRestaurant() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Restaurant r = session.createQuery("from Restaurant", Restaurant.class)
                              .setMaxResults(1).uniqueResult();
        if (r == null) {
            r = new Restaurant("Domino's", "Chennai");
            session.persist(r);
            System.out.println("Default restaurant created!");
        }
        tx.commit();
        session.close();
        return r;
    }
}
