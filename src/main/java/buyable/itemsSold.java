package buyable;

import java.util.ArrayList;

/**
 *
 * @author william.li
 */
// ML: dont need this class
class itemsSold extends Buyable {

    public ArrayList<Buyable> soldItems = new ArrayList<Buyable>();

    int numberOfItems = soldItems.size();
    int memory = 5;

    boolean isSold;

    public itemsSold(double price, String name, String category) {
        super(price, name, category);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void isSold() {
        if (isPurchased) {
            isSold = true;
        } else {
            isSold = false;
        }
    }

    private int getMemory() {
        return memory;
    }

    public void addItemsToMemory() {
            //add.soldItems
        if (memory <= 5) {
        } else {
            System.out.println("Not enough storage!");
        }
    }
}