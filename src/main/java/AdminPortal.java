
import java.util.Scanner;

/**
 *
 * @author willi
 */
public class AdminPortal {
    
    private static final String ADMIN_PASSWORD = "password";
    private final Scanner scan;

    public AdminPortal(Scanner scan) {
        this.scan = scan;
        adminLogin();
    }

    private void adminLogin() {
        System.out.println("Welcome to the Admin Portal");
        System.out.println("Please enter a password");
        String password = scan.nextLine();
        if (!password.equals(ADMIN_PASSWORD)) {
            System.out.println("Passwords do not match ... ");
            adminLogin();
            // TODO 6a: Require the user to login as admin from the start of the program 
            // and only show that menu option if that is the account they log in under.
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

            // TODO 6: 
            // Access the store’s database as an ‘Administrator’ and add custom items to its
            // inventory
            // equire the user to login as admin from the start of the program and only show
            // that menu option if that is the account they log in under.
            // Example:
            // Please enter admin password:
            // If password is correct, give a another set of menu
            // 1. Review all items in inventory
            // 2. Add items to inventory
            // 3. Remove items from 
            System.out.println("Which of these categories do you want to add items to?");
            System.out.println("1. Food");
            System.out.println("2. Clothes");
            System.out.println("3. Games");
            System.out.println("4. Electronics");

            int input = scan.nextInt();

            switch (input) {
                case 1:
//                addItemsToFood();
                    break;
                case 2:
//                addItemsToGames();
                    break;

                case 3:
//                addItemsToClothing();
                    break;
                case 4:
//                addItemsToElectronics();
                    break;
                default:
                    System.out.println("Incorrect choice");
                    break;
            }
        }
    }
    
}
