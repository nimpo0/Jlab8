package commands;

import composition.ComposCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayAllCompos implements Command {
    private static final Logger logger = LogManager.getLogger(DisplayAllCompos.class);

    private ComposCollection allCompositions;

    public DisplayAllCompos(ComposCollection allCompositions) {
        this.allCompositions = allCompositions;
    }

    @Override
    public void execute() {
        if (allCompositions.isAllEmpty()) {
            System.out.println("There are no compositions.");
            logger.warn("No compositions available to display.");
        } else {
            String header = String.format("| %-20s | %-15s | %-15s | %-10s | %-30s |%n",
                    "Title", "Style", "Author", "Duration", "Lyrics");
            String separator = "+----------------------+-----------------+-----------------+------------+--------------------------------+";
            System.out.println(separator);
            System.out.print(header);
            System.out.println(separator);
            allCompositions.displayAllCompositions();
            logger.info("Displayed all compositions.");
        }
    }

    @Override
    public String printInfo() {
        return "Show all compositions.";
    }
}
