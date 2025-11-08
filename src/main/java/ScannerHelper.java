
import java.util.Scanner;

/**
 *
 * WL: This class is to keep asking for a valid user input until it is valid Use
 * Helpers.xxx to use each function
 */
public class ScannerHelper {

    public static int getValidInteger(Scanner scanner) {
        int value;

        while (true) {
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.err.println("Not an integer, try again");
            }
        }

        return value;
    }

    public static double getValidDouble(Scanner scanner) {
        double value;

        while (true) {
            try {
                value = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.err.println("Not a double value, try again");
            }
        }

        return value;
    }
}
