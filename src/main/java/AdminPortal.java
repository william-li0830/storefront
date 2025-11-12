
import buyable.Buyable;
import buyable.BuyableClothing;
import buyable.BuyableFood;
import buyable.BuyableGame;
import buyable.BuyableElectronics;
import java.util.Scanner;

/**
 *
 * @author willi
 */
public class AdminPortal {

    private static final String ADMIN_PASSWORD = "helloWorld";
    private final Scanner scan;
    private final StoreInventory storeInventory;

    public AdminPortal(Scanner scan, StoreInventory storeInventory) {
        this.scan = scan;
        this.storeInventory = storeInventory;
        adminLogin();
    }

    private void adminLogin() {
        System.out.println("Welcome to the Admin Portal");
        System.out.println("Please enter a password");
        String password = scan.nextLine();
        if (!password.equals(ADMIN_PASSWORD)) {
            System.out.println("Passwords do not match ... ");
            adminLogin();
        } else {
            presentAdminMenu();
        }
    }

    private void presentAdminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n****************************************************** ");
            System.out.println("Please choose from one of the following menu options: ");
            System.out.println("1. Review inventory");
            System.out.println("2. Add items to inventory");

            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    viewInventory();
                    break;
                case "2":
                    addItemToInventory();
                    break;
                default:
                    System.out.println("Invalid choice: try again");
                    break;
            }

            System.out.print("\nPress ENTER to go back to MAIN MENU");
            scan.nextLine();
        }
    }

    private void viewInventory() {
        System.out.println("1. View all by name");
        System.out.println("2. View by category");
        System.out.println("3. Type in  item name for more detail");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                viewInventoryByName();
                break;
            case "2":
                viewInventoryByCategory();
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
                viewInventory();
                break;
        }
    }

    private void addItemToInventory() {
        System.out.println("Which of these categories do you want to add items to?");
        System.out.println("Food");
        System.out.println("Clothing");
        System.out.println("Game");
        System.out.println("Electronics");

        String input = scan.nextLine().toLowerCase();

        switch (input) {
            case "food":
                addFoodToInventory();
                break;
            case "clothing":
                addClothingToInventory();
                break;
            case "game":
                addGameToInventory();
                break;
            case "electronics":
                addElectronicsToInventory();
                break;
            default:
                System.out.println("Incorrect choice, try again");
                addItemToInventory();
                break;
        }
    }

    private void addFoodToInventory() {
        System.out.println("Enter name:");
        String name = scan.nextLine();

        System.out.println("Enter price:");
        double price = ScannerHelper.getValidDouble(scan);

        System.out.println("Enter weight in grams:");
        double weight = ScannerHelper.getValidDouble(scan);

        BuyableFood food = new BuyableFood(price, name, weight);

        storeInventory.restockItemToInventory(food);

        System.out.print(name + ": $" + price + " (" + weight + "g" + ")");
        System.out.println(" added to inventory");
    }

    private void addClothingToInventory() {
        System.out.println("Enter name:");
        String name = scan.nextLine();

        System.out.println("Enter price:");
        double price = ScannerHelper.getValidDouble(scan);

        System.out.println("Enter size:");
        String size = scan.nextLine();

        BuyableClothing clothing = new BuyableClothing(price, name, size);
        storeInventory.restockItemToInventory(clothing);

        System.out.print(name + ": $" + price + " (" + size + ")");
        System.out.println(" added to inventory");
    }

    private void addGameToInventory() {
        System.out.println("Enter name:");
        String name = scan.nextLine();

        System.out.println("Enter price:");
        double price = ScannerHelper.getValidDouble(scan);

        System.out.println("Enter number of players:");
        int numPlayers = ScannerHelper.getValidInteger(scan);

        System.out.println("Enter genre:");
        String genre = scan.nextLine();

        BuyableGame game = new BuyableGame(price, name, numPlayers, genre);
        storeInventory.restockItemToInventory(game);

        System.out.print(name + ": $" + price + " (players: " + numPlayers + " genre: " + genre + ")");
        System.out.println(" added to inventory");
    }

    private void addElectronicsToInventory() {
        System.out.println("Enter name:");
        String name = scan.nextLine();

        System.out.println("Enter price:");
        double price = ScannerHelper.getValidDouble(scan);

        System.out.println("Enter brand:");
        String brand = scan.nextLine();

        BuyableElectronics electronics = new BuyableElectronics(price, name, brand);
        storeInventory.restockItemToInventory(electronics);

        System.out.print(name + ": $" + price + " (" + brand + ")");
        System.out.println(" added to inventory");
    }

    private void viewInventoryByName() {
        System.out.println("Here are the items by name: ");
        for (Buyable item : storeInventory.getFullInventoryList()) {
            System.out.println(item.getItemName());
        }
    }

    private void viewInventoryByCategory() {
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