
import buyable.BuyableFood;
import buyable.BuyableClothing;
import buyable.BuyableElectronics;
import buyable.BuyableGame;
import buyable.Buyable;
import java.util.ArrayList;

public class StoreInventory {

    private ArrayList<BuyableClothing> clothesForSale = new ArrayList<BuyableClothing>();
    private ArrayList<BuyableFood> foodForSale = new ArrayList<BuyableFood>();
    private ArrayList<BuyableGame> gamesForSale = new ArrayList<BuyableGame>();
    private ArrayList<BuyableElectronics> electronicsForSale = new ArrayList<BuyableElectronics>();
    private ArrayList<Buyable> recentPurchases = new ArrayList<Buyable>();

    public StoreInventory() {
        populateClothesInventory();
        populateFoodInventory();
        populateGamesInventory();
        populateEletronicsInventory();
    }

    // Getters and setters for inventory lists
    public ArrayList<BuyableClothing> getClothesInventory() {
        return clothesForSale;
    }

    public ArrayList<BuyableFood> getFoodInventory() {
        return foodForSale;
    }

    public ArrayList<BuyableGame> getGamesInventory() {
        return gamesForSale;
    }

    public ArrayList<BuyableElectronics> getElectronicsInventory() {
        return electronicsForSale;
    }

    // Returns a master list of all inventory items at once
    public ArrayList<Buyable> getFullInventoryList() {
        ArrayList<Buyable> fullInventory = new ArrayList<Buyable>();
        fullInventory.addAll(clothesForSale);
        fullInventory.addAll(foodForSale);
        fullInventory.addAll(gamesForSale);

        return fullInventory;
    }

    // TODO 4: create a separate database to keep track of items that have been sold (give it a memory).
    // If isPurchased is true, add the item in itemsSold list
    // TODO 4a: viewRecentPurchases();
    // report the 3 (or fewer) most recent items they bought in the store
    // Example:
    //     Implement a getter function "getRecentPurchases()"
    //     - Get the last three items from itemsSold 
    //           int numberOfItems = itemsSold.size();
    //           Buyable lastItem = itemsSold.get(numberOfItems);
    public void removeItemFromInventory(Buyable item, boolean isPurchased) {

        if (item instanceof BuyableClothing) {
            clothesForSale.remove((BuyableClothing) item);
        } else if (item instanceof BuyableFood) {
            foodForSale.remove((BuyableFood) item);
        } else if (item instanceof BuyableGame) {
            foodForSale.remove((BuyableGame) item);
        }
        isPurchased = true;
    }

    public ArrayList<Buyable> getItemsSold(Buyable item, boolean isPurchased) {
        
        if (isPurchased = true) {
            System.out.println("Sold items: ");
            //System.out.println(soldItems);
        }
        //return 0;
        return null;
    }

    public void viewRecentPurchases() {
        System.out.println("Recently purchased: ");
        System.out.println(recentPurchases);
    }

    public ArrayList<Buyable> getRecentPurchases() {
        return recentPurchases;
    }

    public void restockItemToInventory(Buyable item) {
        if (item instanceof BuyableClothing) {
            clothesForSale.add((BuyableClothing) item);
        } else if (item instanceof BuyableFood) {
            foodForSale.add((BuyableFood) item);
        } else if (item instanceof BuyableGame) {
            foodForSale.remove((BuyableGame) item);
        }
    }

    // Methods to populate the inventory
    private void populateClothesInventory() {
        // Master list of all clothes held in the store on opening

        // Hoodies
        BuyableClothing smallHoodie = new BuyableClothing(59.99, "Hoodie", "small");
        clothesForSale.add(smallHoodie);
        BuyableClothing mediumHoodie = new BuyableClothing(59.99, "Hoodie", "medium");
        clothesForSale.add(mediumHoodie);
        BuyableClothing largeHoodie = new BuyableClothing(59.99, "Hoodie", "lage");
        clothesForSale.add(largeHoodie);

        // Shoes
        BuyableClothing dressShoes = new BuyableClothing(99.99, "Dress Shoes", "8");
        clothesForSale.add(dressShoes);
        BuyableClothing sandals = new BuyableClothing(9.99, "Sandals", "5");
        clothesForSale.add(sandals);

        // Gloves
        BuyableClothing gloves = new BuyableClothing(13.49, "Gloves", "Medium");
        addMultiple(gloves, 3);
    }

    private void populateFoodInventory() {
        // Master list of all food held in the store on opening

        // Perishables
        BuyableFood pizza = new BuyableFood(12.99, "Pizza", 400);
        foodForSale.add(pizza);
        BuyableFood lasagna = new BuyableFood(24.00, "Lasagna", 1000);
        foodForSale.add(lasagna);
        BuyableFood spinach = new BuyableFood(3.99, "Spinach", 250);
        foodForSale.add(spinach);

        // Non-perishables
        BuyableFood beans = new BuyableFood(1.49, "Beans", 300);
        foodForSale.add(beans);
        BuyableFood noodles = new BuyableFood(0.99, "Noodles", 125);
        foodForSale.add(noodles);
        BuyableFood rice = new BuyableFood(7.99, "Rice", 2000);
        addMultiple(rice, 5);

    }

    private void populateGamesInventory() {
        // Master list of all games held in the store on opening

        // Board games
        BuyableGame monopoly = new BuyableGame(19.99, "Monopoly", 4, "Board Game");
        gamesForSale.add(monopoly);
        BuyableGame scrabble = new BuyableGame(24.99, "Scrabble", 2, "Board Game");
        gamesForSale.add(scrabble);

        // Computer games
        BuyableGame breathOfTheWild = new BuyableGame(79.99, "Breath of the Wild", 1, "Video Game");
        gamesForSale.add(breathOfTheWild);
        BuyableGame forza = new BuyableGame(59.99, "Forza", 2, "Video Game");
        gamesForSale.add(forza);
    }

    private void populateEletronicsInventory() {
        //Apliances

        BuyableElectronics fridge = new BuyableElectronics(2199.99, "Fridge", "Samsung");
        electronicsForSale.add(fridge);
        BuyableElectronics fridgeLG = new BuyableElectronics(2199.99, "Fridge", "LG");
        electronicsForSale.add(fridgeLG);
        BuyableElectronics dishWasherLG = new BuyableElectronics(699.99, "Dish washer", "LG");
        electronicsForSale.add(dishWasherLG);
        BuyableElectronics dishWasher = new BuyableElectronics(799.99, "Dish washer", "Samsung");
        electronicsForSale.add(dishWasher);

        // Comupters
        BuyableElectronics laptop = new BuyableElectronics(1399.99, "Laptop", "Asus");
        electronicsForSale.add(laptop);
        BuyableElectronics laptopApple = new BuyableElectronics(2099.99, "Laptop", "Apple");
        electronicsForSale.add(laptopApple);
        BuyableElectronics desktop = new BuyableElectronics(1249.99, "Desktop", "Lenovo");
        electronicsForSale.add(desktop);
        BuyableElectronics desktopROG = new BuyableElectronics(2499.99, "Desktop", "ROG");
        electronicsForSale.add(desktopROG);

        //Phones
        BuyableElectronics galaxyPhone = new BuyableElectronics(399.99, "Phone", "Samsung");
        electronicsForSale.add(galaxyPhone);
        BuyableElectronics applePhone = new BuyableElectronics(1599.99, "Phone", "Apple");
        electronicsForSale.add(applePhone);
    }

    // Helper method to add multiple copies of the same item to the inventory at once
    private void addMultiple(Buyable item, int numberToAdd) {
        if (item instanceof BuyableClothing) {
            for (int i = 0; i < numberToAdd; i++) {

                clothesForSale.add((BuyableClothing) item);
            }
        } else if (item instanceof BuyableFood) {
            for (int i = 0; i < numberToAdd; i++) {
                foodForSale.add((BuyableFood) item);
            }
        } else if (item instanceof BuyableGame) {
            for (int i = 0; i < numberToAdd; i++) {
                gamesForSale.add((BuyableGame) item);
            }
        }
    }
}