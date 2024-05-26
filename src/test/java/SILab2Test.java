import org.example.Item;
import org.example.SILab2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SILab2Test {
    @Test
    public void everyBranchTest() {
        RuntimeException e;
        ArrayList<Item> list = new ArrayList<>();

        // 1
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(e.getMessage().contains("allItems list can't be null!"));

        // 2
        list.add(new Item("Item 1", null, 30, 0));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(e.getMessage().contains("No barcode!"));

        // 3
        list.clear();
        list.add(new Item("Item 1", "0023", 70, 0));
        list.add(new Item("Item 2", "0123", 100, 0));
        list.add(new Item("Item 3", "THIS", 250, 0.3f));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(e.getMessage().contains("Invalid character in item barcode!"));

        list.clear();
        list.add(new Item("Item 1", "1234", 50, 0.1f));
        list.add(new Item("Item 2", "5331", 80, 0.2f));
        list.add(new Item("Item 3", "21231", 350, 0));

        // 4
        assertTrue(SILab2.checkCart(list, 500));

        // 5
        assertFalse(SILab2.checkCart(list, 100));
    }

    @Test
    public void multipleConditionTest() {
        ArrayList<Item> list = new ArrayList<>();

        // item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'


        list.add(new Item("Item", "123456", 400, 0.3f));
        assertTrue(SILab2.checkCart(list, 800));


        list.clear();
        list.add(new Item("Item", "123456", 400, 0));
        assertTrue(SILab2.checkCart(list, 1000));


        list.clear();
        list.add(new Item("Item", "1234", 900, 0));
        assertTrue(SILab2.checkCart(list, 1500));


        list.clear();
        list.add(new Item("Item", "0123", 50, 0.3f));
        assertTrue(SILab2.checkCart(list, 1000));
    }
}
