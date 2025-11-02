
import buyable.Buyable;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    Scanner scan = new Scanner(System.in);

    // User data variables
    private BankAccount myBankAccount;
    private ArrayList<Buyable> myInventory;
    private ArrayList<Buyable> myShoppingCart;

    // Store data variables
    private StoreInventory storeInventory;

    public Store() {
        System.out.println("Welcome to my storefont!");
        setupAccounts();
        setupStore();
        presentShoppingMenu();
    }

    private void setupAccounts() {
        setupBankAccount();
        myInventory = new ArrayList<Buyable>();
        myShoppingCart = new ArrayList<Buyable>();
    }

    private void setupStore() {
        storeInventory = new StoreInventory();
    }

    private void setupBankAccount() {
        System.out.println("To begin, please set up a bank account.");
        System.out.println("How much money should your account contain?");
        int depositAmount = scan.nextInt();
        myBankAccount = new BankAccount(depositAmount);
    }

    private void presentShoppingMenu() {
        boolean stillShopping = true;
        while (stillShopping) {
            System.out.println("\n****************************************************** ");
            System.out.println("Please choose from one of the following menu options: ");
            System.out.println("1. View catalog of items to buy");
            System.out.println("2. Buy an item");
            System.out.println("3. View your cart of held items");
            System.out.println("4. Review the items you already own");
            System.out.println("5.View recently purchased items");
            System.out.println("6. View the status of your financials");
            System.out.println("7. YOUR CUSTOM IDEA HERE??");
            System.out.println("8. Exit program");

            int input = scan.nextInt();
            scan.nextLine(); // buffer clear

            switch (input) {
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    buyItem();
                    break;
                case 3:
                    reviewMyShoppingCart();
                    break;
                case 4:
                    reviewMyInventory();
                    break;
                case 5:
                    reviewFinancials();
                    break;
                case 6:
                    viewRecentPurchases();
                    // report the 3 (or fewer) most recent items 
                    break;
                case 7:
                    // TODO 5: returnItems();
                    // Give the list of items that can be returned (your inventory)
                    // Type the name of item to return
                    // User - remove item from inventory and give back money
                    // Inventory - add the item back to the inventory
                    // TODO 5a: You can only return the most recent 3 purchases (depends on TODO 4a)
                    break;
                case 8:
                    // TODO 6: adminPortal();
                    // Access the store’s database as an ‘Administrator’ and add custom items to its inventory
                    // equire the user to login as admin from the start of the program and only show that menu option if that is the account they log in under.
                    // Example:
                    // Please enter admin password:
                    // If password is correct, give a another set of menu
                    // 1. Review all items in inventory
                    // 2. Add items to inventory
                    // 3. Remove items from inventory

                    break;
                case 9:
                    // TODO 7: custom idea
                    System.out.println("YOUR CONTENT HERE! :) :)");
                    break;
                case 10:
                    System.out.println("Thanks for shopping! Now exiting program ... ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect input. Choose again!");
                    break;
            }

        }
    }

    private void viewCatalog() {
        //System.out.println("Here is a list of all the items currently for sale!");
        
            System.out.println("1.View by name");
            System.out.println("2.View by type");
            System.out.println("3.View by price");
        
        int input = scan.nextInt();

        switch (input) {
            case 1:
                viewByName();
                break;
            case 2:
                viewByType();
                break;
            case 3:
                viewByPrice();
                break;
            default:
                System.out.println("Incorrect input. Choose again!");
                break;
        }

        // TODO 2a: provide the user another menu to choose which specific item they want to view more details about individually.
        // Retrieve the master list for example:
        // 3. Enter the name of the item for more details
        // Retrieve the master list from the store inventory and examine each entry individually
        
//        for (Buyable item : storeInventory.getFullInventoryList()) {
//            System.out.println("" + item.getItemName());
//        }
//        System.out.println("" + myInventory.get(i).getItemName());
//        System.out.println("" + myInventory.get(i).getPrice());
//        System.out.println("" + myInventory.get(i).getItemCategory());
    }

    private void buyItem() {
        System.out.println("Please type in the name of the item you wish to buy!");

        // Get user input
        String itemName = scan.nextLine();

        // Holding variable for the desired item, if found
        Buyable itemToBuy = null;

        // Look through the full inventory to see if the item is present
        // Convert both item name and user input to lower case to prevent case issues!
        for (Buyable item : storeInventory.getFullInventoryList()) {
            if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                itemToBuy = item;
                break;
            }
        }

        // If a suitable item was found, give them the option to buy it!
        if (itemToBuy != null) {
            System.out.println("We have " + itemToBuy.getItemName() + " in stock!");
            System.out.println("Type 1 to BUY NOW or 2 to PLACE IN YOUR SHOPPING CART.");

            int input = scan.nextInt();
            scan.nextLine(); // buffer clear

            if (input == 1) {
                makePurchaseFromStore(itemToBuy);
            } else if (input == 2) {
                System.out.println("We'll hold onto this item for you.");
                moveItemToShoppingCart(itemToBuy);
            } else {
                System.out.println("Incorrect input. Purchase cancelled.");
            }

        } else // No suitable item found
        {
            System.out.println("The item you are looking for is sold out or does not exist, sorry!");
        }

    }

    private void reviewMyInventory() {
        // TODO 3: gives the user more choice and information when reviewing their stuff
        // Example: price, custom property, purchase time (optional)

        // TODO 3a: create the menuing needed for the user to view any detail of every item they own. 
        // Hint: Use methods to create sub-menus for categories of things.
        // 1. View all items
        // 2. View by type
        //  // 1. Clothing
        // 2. Food
        // 3. Type in the item for more details
        System.out.println("Here is a list of the items you now own: ");
        for (int i = 0; i < myInventory.size(); i++) {
            System.out.println("" + myInventory.get(i).getItemName());
            System.out.println("" + myInventory.get(i).getPrice());
            System.out.println("" + myInventory.get(i).getItemCategory());
        }
    }

    private void viewRecentPurchases() {
        System.out.println("You recently purchased: ");
        for (int i = 0; i <= 3; i++) {
            System.out.println("" + myInventory.get(i).getItemName());
        }
    }

    private void reviewFinancials() {
        myBankAccount.balanceReport();
    }

    // SHOPPING CART METHODS
    private void reviewMyShoppingCart() {
        if (!myShoppingCart.isEmpty()) {
            System.out.println("Here are all of the items being held in your shopping cart: ");
            for (Buyable item : myShoppingCart) {
                System.out.println("" + item.getItemName());
            }

            System.out.println("Would you like to purchase any held items now? 1 for YES or any other key for NO");

            String userInput = scan.nextLine();

            if (userInput.equals("1")) {
                buyItemInShoppingCart();
            } else {
                System.out.println("Leaving shopping cart as is and returning to the storefront ... ");
            }
        } else {
            System.out.println("Your shopping cart is empty! Nothing to see here ... ");
        }

    }

    private void buyItemInShoppingCart() {
        System.out.println("Type in the name of the item you want to buy from the shopping cart: ");
        String userChoice = scan.nextLine();

        for (Buyable itemInCart : myShoppingCart) {
            if (itemInCart.getItemName().toLowerCase().equals(userChoice.toLowerCase())) {
                makePurchaseFromShoppingCart(itemInCart);
                break;
            } else {
                System.out.println("Item could not be found in shopping cart.");
            }
        }

    }

    private void removeItemFromShoppingCart(Buyable item) {
        System.out.println("Which item would you like to remove from your shopping cart?");

        String userChoice = scan.nextLine();

        for (Buyable cartItem : myShoppingCart) {
            if (cartItem.getItemName().toLowerCase().equals(userChoice.toLowerCase())) {
                System.out.println("You have removed " + cartItem.getItemName() + " from your shopping cart.");
                moveItemFromShoppingCartToInventory(item);
            } else {
                System.out.println("Item could not be found in your shopping cart.");
            }
        }
    }

    // Move item from inventory to shopping cart
    private void moveItemToShoppingCart(Buyable item) {
        myShoppingCart.add(item);
        storeInventory.removeItemFromInventory(item, false);
    }

    private void moveItemFromShoppingCartToInventory(Buyable item) {
        storeInventory.restockItemToInventory(item);
        myShoppingCart.remove(item);
    }

    private void makePurchaseFromStore(Buyable item) {
        // If you can afford the item, buy it and remove it from the store
        if (myBankAccount.canAfford(item.getPrice())) {
            myBankAccount.makePurchase(item.getPrice());
            System.out.println("Purchase complete! You now own " + item.getItemName());
            myInventory.add(item);
            storeInventory.removeItemFromInventory(item, true);
        } else {
            System.out.println("You can't afford that item ... ");
        }
    }

    private void makePurchaseFromShoppingCart(Buyable item) {
        // If you can afford the item, buy it and remove it from the store
        if (myBankAccount.canAfford(item.getPrice())) {
            myBankAccount.makePurchase(item.getPrice());
            System.out.println("Purchase complete! You now own " + item.getItemName());
            myInventory.add(item);
            myShoppingCart.remove(item);
        } else {
            System.out.println("You can't afford that item ... ");
        }
    }

    private void viewByName() {
        System.out.println("Here are the items by name: ");
    }

    private void viewByType() {
        System.out.println("Here are the items by type: ");
    }

    private void viewByPrice() {
        System.out.println(" Here are items by price: ");
    }
}