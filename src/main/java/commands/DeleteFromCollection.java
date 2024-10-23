package commands;

import composition.ComposCollection;
import composition.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DeleteFromCollection implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteFromCollection.class);
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");

    private ComposCollection collection;
    private Scanner scanner;

    public DeleteFromCollection(ComposCollection collection, Scanner scanner) {
        this.collection = collection;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (collection.isEmpty()) {
            System.out.println("The collection is empty.");
            logger.warn("Attempted to delete a composition, but the collection is empty.");
            return;
        }

        System.out.println("Enter the name of the composition you want to delete:");
        String name = scanner.nextLine();

        Composition delete = collection.findCompositionByName(name);

        if (delete != null) {
            collection.deleteComposition(delete);
            System.out.println("Composition \"" + name + "\" successfully deleted from the collection.");
            logger.info("Composition '{}' successfully deleted from the collection.", name);
        } else {
            System.out.println("Composition \"" + name + "\" not found in the collection.");
            errorLogger.error( "Failed to delete composition: '{}' not found in the collection.", name);
        }
    }

    @Override
    public String printInfo() {
        return "Delete a composition from collection.";
    }
}
