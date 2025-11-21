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
        setupStore();
        login();
    }

    private void login() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("1. Customer login");
            System.out.println("2. Admin login");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    setupAccounts();
                    presentShoppingMenu();
                    isValidInput = true;
                    break;
                case "2":
                    new AdminPortal(scan, storeInventory);
                    isValidInput = true;
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
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
        int depositAmount = ScannerHelper.getValidInteger(scan);
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
            System.out.println("5. View recently purchased items");
            System.out.println("6. View the status of your financials");
            System.out.println("7. Return an item");
            System.out.println("8. Exit program");

            int input;

            try {
                input = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                System.err.println("Invalid input: try again");
                scan.nextLine();
                // It skips the rest and loops again
                continue;
            }

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
                    viewRecentPurchases();
                    break;
                case 6:
                    reviewFinancials();
                    break;
                case 7:
                    returnItem();
                    break;
                case 8:
                    System.out.println("Thanks for shopping! Now exiting program ... ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect input. Choose again!");
                    break;
            }

            System.out.print("\n**Press ENTER to go back to MAIN MENU**");
            scan.nextLine();
        }
    }

    private void viewCatalog() {
        System.out.println("1. View all by name");
        System.out.println("2. View by category");
        System.out.println("3. Type item name for more details");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                viewAllCatalog();
                break;
            case "2":
                viewCatalogByCategory();
                break;
            case "3":
                System.out.println("Type in item name");

                String userInput = scan.nextLine();
                boolean itemFound = false;

                for (Buyable item : storeInventory.getFullInventoryList()) {
                    if (item.getItemName().equalsIgnoreCase(userInput)) {
                        printItemDetails(item);
                        itemFound = true;
                    }
                }

                if (!itemFound) {
                    System.out.println("Item doesn't exist");
                }
                break;
            default:
                System.out.println("Invalid choice: choose again!");
                viewCatalog();
                break;
                
        }

        // TODO 2a: provide the user another menu to choose which specific item they
        // want to view more details about individually.
        // Refer to reviewMyInventory()
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

    private void returnItem() {
        ArrayList<Buyable> recentPurchases = storeInventory.getRecentPurchases();

        if (recentPurchases.isEmpty()) {
            System.out.println("You have nothing to return");
            // Stop this function right away
            return;
        }

        System.out.println("Here are items you can return");
        for (Buyable item : recentPurchases) {
            System.out.println(item.getItemName());
        }

        System.out.println();
        System.out.println("Type in the name of item you want to return:");
        String input = scan.nextLine();

        Buyable itemToReturn = null;
        for (Buyable item : recentPurchases) {
            if (item.getItemName().equalsIgnoreCase(input)) {
                itemToReturn = item;
            }
        }

        if (itemToReturn != null) {
            returnItemToStore(itemToReturn);
        } else {
            System.out.println("Item doesn't exist in your recent purchases");
        }
    }

    private void reviewMyInventory() {
        System.out.println("1. View all items by name");
        System.out.println("2. View by category");
        System.out.println("3. Type in item name for more details");

        String choice = scan.nextLine();

        switch (choice) {
            case "1":
                for (Buyable item : myInventory) {
                    System.out.println(item.getItemName());
                }
                break;
            case "2":
                System.out.println("Select from the following: ");
                System.out.println("Food");
                System.out.println("Clothing");
                System.out.println("Game");
                System.out.println("Electronics");

                String category = scan.nextLine();

                for (Buyable item : myInventory) {
                    if (item.getItemCategory().equalsIgnoreCase(category)) {
                        System.out.println(item.getItemName());
                    }
                }

                break;
            case "3":
                System.out.println("Type in item name");

                String userInput = scan.nextLine();
                boolean itemFound = false;

                for (Buyable item : myInventory) {
                    if (item.getItemName().equalsIgnoreCase(userInput)) {
                        printItemDetails(item);
                        itemFound = true;
                    }
                }

                if (!itemFound) {
                    System.out.println("Item doesn't exist in your inventory");
                }
                break;
            default:
                System.out.println("Invalid choice: choose again!");
                reviewMyInventory();

                break;
        }
    }

    private void viewRecentPurchases() {
        System.out.println("You recently purchased: ");
        for (Buyable item : storeInventory.getRecentPurchases()) {
            printItemDetails(item);
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


    private void returnItemToStore(Buyable item) {
        myBankAccount.depositMoney(item.getPrice());
        System.out.println("Return complete!");
        myInventory.remove(item);
        storeInventory.returnPurchasedItem(item);
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

    private void viewAllCatalog() {
        System.out.println("Here are the items by name: ");
        for (Buyable item : storeInventory.getFullInventoryList()) {
            System.out.println(item.getItemName());
        }
    }

    private void viewCatalogByCategory() {
        System.out.println("Select from the following: ");
        System.out.println("Food");
        System.out.println("Clothing");
        System.out.println("Game");
        System.out.println("Electronics");

        String category = scan.nextLine();

        for (Buyable item : storeInventory.getFullInventoryList()) {
            if (item.getItemCategory().equalsIgnoreCase(category)) {
                System.out.println(item.getItemName());
            }
        }
    }

    private void printItemDetails(Buyable item) {
        String name = item.getItemName();
        double price = item.getPrice();
        String category = item.getItemCategory();
        System.out.println(name + ": $" + price + " (" + category + ")");
    }
}