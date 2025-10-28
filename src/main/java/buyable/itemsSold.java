package buyable;

import java.util.ArrayList;

/**
 *
 * @author william.li
 */
class itemsSold extends Buyable {

    public ArrayList<Buyable> soldItems = new ArrayList<Buyable>();

    int numberOfItems = soldItems.size();

    public itemsSold(double price, String name, String category) {
        super(price, name, category);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }
}
