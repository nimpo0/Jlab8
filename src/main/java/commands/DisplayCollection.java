package commands;

import composition.ComposCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayCollection implements Command {
    private static final Logger logger = LogManager.getLogger(DisplayCollection.class);

    private ComposCollection collection;

    public DisplayCollection(ComposCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() {
        if (collection.isEmpty()) {
            System.out.println("The collection is empty.");
            logger.warn("Attempted to display the collection, but it is empty.");
        } else {
            String header = String.format("| %-20s | %-15s | %-15s | %-10s | %-30s |%n",
                    "Title", "Style", "Author", "Duration", "Lyrics");
            String separator = "+----------------------+-----------------+-----------------+------------+--------------------------------+";
            System.out.println(separator);
            System.out.print(header);
            System.out.println(separator);
            collection.displayCompositions();
            logger.info("Displayed all compositions in the collection.");
        }
    }

    @Override
    public String printInfo() {
        return "Show all compositions from the collection.";
    }
}
