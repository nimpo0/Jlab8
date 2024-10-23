package mainPackage;

import commands.*;
import composition.ComposCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Menu {
    private static final Logger logger = LogManager.getLogger(Menu.class);
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");

    private ComposCollection collection;
    private ComposCollection allCompositions;
    private Scanner scanner;
    private Map<Integer, Command> commandMap;

    public Menu() {
        this.collection = new ComposCollection();
        this.allCompositions = new ComposCollection();
        this.scanner = new Scanner(System.in);
        this.commandMap = new HashMap<>();
        initializeCommands();
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            if (choice == 0) {
                System.out.println("Exiting the program. Goodbye!");
                logger.info("Exiting the program.");
                break;
            }

            Command command = commandMap.get(choice);
            if (command != null) {
                try {
                    command.execute();
                    System.out.println("Command executed successfully.");
                } catch (Exception e) {
                    System.out.println("Error with executing command.");
                    errorLogger.error("Error with executing command: {}", e.getMessage());
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                errorLogger.error("Invalid choice");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n\t\t\t\t  === MENU ===");
        System.out.println("==================================================");

        for (int key : commandMap.keySet()) {
            Command command = commandMap.get(key);
            System.out.println("\t" + key + ". " + command.printInfo());
        }

        System.out.println("\t0. Exit");
        System.out.println("==================================================");
        System.out.print("Choose an option (1-11): ");
    }

    private void initializeCommands() {
        commandMap.put(1, new AddCompos(allCompositions, scanner));
        commandMap.put(2, new DisplayAllCompos(allCompositions));
        commandMap.put(3, new AddToCollection(collection, allCompositions, scanner));
        commandMap.put(4, new DeleteFromCollection(collection, scanner));
        commandMap.put(5, new DisplayCollection(collection));
        commandMap.put(6, new CalculateDuration(collection));
        commandMap.put(7, new SortingByStyle(collection, scanner));
        commandMap.put(8, new FindCompositions(allCompositions, scanner));
        commandMap.put(9, new SaveToFile(collection));
        commandMap.put(10, new LoadFromFile(collection));
        commandMap.put(11, new CriticalError());
    }

    private int getUserChoice() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 0 || commandMap.containsKey(choice)) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. The choice must be between 0 and 11: ");
                    logger.warn("");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please enter a number: ");
                errorLogger.error("Invalid input, the choice must be a number: {}", e.getMessage());
            }
        }
    }
}
