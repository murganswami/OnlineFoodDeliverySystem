package ms.org.service;

import ms.org.dao.MenuDAO;
import ms.org.model.MenuItem;
import java.util.List;

public class MenuService {
    private MenuDAO dao = new MenuDAO();

    public void addMenu(MenuItem item) {
        dao.addMenuItem(item);
    }

    public void updateMenu(int id, double price) {
        dao.updateMenuItem(id, price);
    }

    public void deleteMenu(int id) {
        dao.deleteMenuItem(id);
    }

    public List<MenuItem> viewMenu() {
        return dao.getAllMenuItems();
    }
}
