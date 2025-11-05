package ms.org.dao;

import ms.org.db.HibernateUtil;
import ms.org.model.MenuItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MenuDAO {

    public void addMenuItem(MenuItem menuItem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(menuItem);
        tx.commit();
        session.close();
        System.out.println("Menu Item Added Successfully!");
    }

    public List<MenuItem> getAllMenuItems() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<MenuItem> list = session.createQuery("from MenuItem", MenuItem.class).list();
        session.close();
        return list;
    }

    public void updateMenuItem(int id, double newPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        MenuItem item = session.get(MenuItem.class, id);
        if (item != null) {
            item.setPrice(newPrice);
            session.merge(item);
            tx.commit();
            System.out.println("Menu Item Updated!");
        } else {
            System.out.println("Menu Item not found!");
            tx.rollback();
        }
        session.close();
    }

    public void deleteMenuItem(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        MenuItem item = session.get(MenuItem.class, id);
        if (item != null) {
            session.remove(item);
            tx.commit();
            System.out.println("Menu Item Deleted!");
        } else {
            System.out.println("Menu Item not found!");
            tx.rollback();
        }
        session.close();
    }
}

