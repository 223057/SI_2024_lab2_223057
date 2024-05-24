import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    private List<Item> createList(Item...elements){
        return new ArrayList<>(Arrays.asList(elements));
    }
    @Test
    void everyBranchTest(){
        //test case one, allItems == null, throws exception
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //test case two, no exceptions, returns true
        Item i1 = new Item(null, "0123", 400, 15f);
        Item i2 = new Item("mleko", "1234", 200, 0f);
        assertTrue(SILab2.checkCart(createList(i1, i2), 10000));

        //test case three, invalid barcode, throws exception
        Item i1_1 = new Item("jabolko", "/123", 400, 15f);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(i1_1), 100));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //test case four, barcode == null, throws exception
        Item i1_2 = new Item("banana", null, 400, 15f);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createList(i1_2), 100));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //test case five, no exceptions, returns false
        Item i1_3 = new Item("jagoda", "0123", 400, 0f);
        assertFalse(SILab2.checkCart(createList(i1_3), 100));
    }

    @Test
    void multipleConditionsTest(){
        //if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
        //FXX
        Item i1_1 = new Item("praska", "1123", 100, 10f);
        assertFalse(SILab2.checkCart(createList(i1_1), 100));

        //TFX
        Item i1_2 = new Item("cresha", "1123", 400, 0f);
        assertFalse(SILab2.checkCart(createList(i1_2), 100));

        //TTF
        Item i1_3 = new Item("malina", "1123", 400, 10f);
        assertTrue(SILab2.checkCart(createList(i1_3), 6000));

        //TTT
        Item i1_4 = new Item("vishna", "0123", 400, 10f);
        assertFalse(SILab2.checkCart(createList(i1_4), 100));
    }
}