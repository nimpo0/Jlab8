package commands;

import composition.ComposCollection;
import composition.Composition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Scanner;

public class AddCompos implements Command {
    private static final Logger logger = LogManager.getLogger(AddCompos.class);
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");


    private ComposCollection allCompos;
    private Scanner scanner;

    public AddCompos(ComposCollection allCollection, Scanner scanner) {
        this.allCompos = allCollection;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter the composition name:");
            String name = scanner.nextLine();

            System.out.println("Enter the composition style:");
            String style = scanner.nextLine();

            System.out.println("Enter the author's name:");
            String author = scanner.nextLine();

            int duration = getDurationNum();

            System.out.println("Enter the lyrics:");
            String lyrics = scanner.nextLine();

            Composition newComposition = new Composition(name, style, author, duration, lyrics);
            allCompos.addToAllCompositions(newComposition);

            // Log informational message about successful addition
            logger.info("Composition '{}' has been successfully added.", name);
            System.out.println("Composition successfully added.");
        } catch (Exception e) {
            logger.error(ERROR_MARKER,"An error occurred while adding the composition: {}", e.getMessage(), e);
            System.out.println("An error occurred while adding the composition. Please check the logs for more details.");
        }
    }

    @Override
    public String printInfo() {
        return "Add a new composition.";
    }

    private int getDurationNum() {
        int dur = -1;
        while (dur <= 0) {
            System.out.println("Enter the composition duration (in seconds):");
            try {
                String input = scanner.nextLine();
                dur = Integer.parseInt(input);
                if (dur <= 0) {
                    System.out.println("Duration must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a whole number.");
                logger.error(ERROR_MARKER,"Invalid input for duration: '{}'", e.getMessage());
            }
        }
        return dur;
    }
}
