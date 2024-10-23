package commands;

import composition.ComposCollection;
import composition.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Comparator;
import java.util.Scanner;

public class SortingByStyle implements Command {
    private static final Logger logger = LogManager.getLogger(SortingByStyle.class);
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");

    private ComposCollection collection;
    private Scanner scanner;

    public SortingByStyle(ComposCollection collection, Scanner scanner) {
        this.collection = collection;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (collection.isEmpty()) {
            System.out.println("The collection is empty.");
            logger.warn("Attempted to sort an empty collection.");
            return;
        }

        int choice = getUserChoice();

        if (choice == 1) {
            collection.getCompositions().sort(Comparator.comparing(Composition::getStyle));
            System.out.println("Compositions sorted alphabetically.");
            logger.info("Compositions sorted alphabetically by style.");
        } else if (choice == 2) {
            collection.getCompositions().sort(Comparator.comparing(Composition::getStyle).reversed());
            System.out.println("Compositions sorted in reverse alphabetical order.");
            logger.info("Compositions sorted in reverse alphabetical order by style.");
        } else {
            System.out.println("Invalid choice, please try again.");
            logger.warn("Invalid choice made by the user for sorting order.");
            return;
        }

        System.out.println("+----------------------+-----------------+-----------------+------------+--------------------------------+");
        collection.displayCompositions();
    }

    @Override
    public String printInfo() {
        return "Sort compositions by style in collection.";
    }

    private int getUserChoice() {
        int choice = -1;
        while (choice != 1 && choice != 2) {
            System.out.println("Choose sorting order:");
            System.out.println("1. Alphabetically");
            System.out.println("2. In reverse alphabetical order");

            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid input, please enter 1 or 2.");
                    logger.warn("Invalid input by user: {}", input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a whole number.");
                errorLogger.error("Invalid input for sorting order: {}", input, e);
            }
        }
        return choice;
    }
}
