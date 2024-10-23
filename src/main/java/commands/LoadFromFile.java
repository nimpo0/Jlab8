package commands;

import composition.ComposCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadFromFile implements Command {
    private static final Logger logger = LogManager.getLogger(LoadFromFile.class);
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");

    private ComposCollection collection;

    public LoadFromFile(ComposCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() {
        String filename = "C:\\Users\\Admin\\Desktop\\disk.ser";

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            ComposCollection loadedCollection = (ComposCollection) in.readObject();
            collection.getCompositions().addAll(loadedCollection.getCompositions());
            System.out.println("Collection successfully loaded from file \"" + filename + "\".");
            logger.info("Collection successfully loaded from file '{}'.", filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            errorLogger.error("File not found: {}", filename, e);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading the file.");
            errorLogger.error("Error while loading the file '{}'.", filename, e);
        }
    }

    @Override
    public String printInfo() {
        return "Load the collection from disk.";
    }
}
