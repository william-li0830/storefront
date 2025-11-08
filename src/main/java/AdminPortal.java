
import buyable.Buyable;
import buyable.BuyableFood;
import java.util.Scanner;

/**
 *
 * @author willi
 */
public class AdminPortal {

    private static final String ADMIN_PASSWORD = "password";
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

    // TODO: print more details, refer to view catalog
    private void viewInventory() {
        for (Buyable item : storeInventory.getFullInventoryList()) {
            System.out.println(item.getItemName());
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
//                addClothingToInventory();
                break;
            case "game":
//                addGameToInventory();
                break;
            case "electronics":
//                addElectronicsToInventory();
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
        int price = ScannerHelper.getValidInteger(scan);

        System.out.println("Enter weight in grams:");
        double weight = ScannerHelper.getValidDouble(scan);

        BuyableFood food = new BuyableFood(price, name, weight);

        storeInventory.restockItemToInventory(food);

        System.out.print(name + ": $" + price + " (" + weight + "g" + ")");
        System.out.println(" added to inventory");
    }
}
