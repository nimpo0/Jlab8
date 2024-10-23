package commands;

import composition.ComposCollection;
import composition.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculateDuration implements Command {
    private static final Logger logger = LogManager.getLogger(CalculateDuration.class);

    private ComposCollection collection;

    public CalculateDuration(ComposCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() {
        if (collection.isEmpty()) {
            System.out.println("The collection is empty.");
            logger.warn("Attempted to calculate duration, but the collection is empty.");
            return;
        }

        int totalDuration = 0;
        for (Composition comp : collection.getCompositions()) {
            totalDuration += comp.getComposDuration();
        }

        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;

        System.out.println("Total duration  in the collection: " + minutes + " min " + seconds + " sec.");
        logger.info("Total duration in the collection calculated: {} min {} sec.", minutes, seconds);
    }

    @Override
    public String printInfo() {
        return "Calculate the total duration of the collection.";
    }
}
