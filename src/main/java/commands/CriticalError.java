package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class CriticalError implements Command {
    private static final Logger logger = LogManager.getLogger(CriticalError.class);
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    @Override
    public void execute() {
        try {
            throw new RuntimeException("This is a test critical error for email notification.");
        } catch (RuntimeException e) {
            logger.error(ERROR_MARKER,"Critical error occurred: {}", e.getMessage(), e);
        }
    }

    @Override
    public String printInfo() {
        return "Trigger critical error (for email notifications)";
    }
}
