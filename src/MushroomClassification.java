import org.neuroph.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public class MushroomClassification {

    public static void main(String[] args) {

        readFromURLAndWriteToFile("https://archive.ics.uci.edu/ml/machine-learning-databases/mushroom/agaricus-lepiota.data");

        ImportHelper.convertDataFiles();


    }
    
    static void readFromURLAndWriteToFile(String urlString)
    {
        try {
            // create the url
            URL url = new URL(urlString);
            // open the url stream, wrap it an a few "readers"
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            // open writer to write into file
            PrintWriter writer = new PrintWriter("input.data", "UTF-8");
            // write the output to file
            String line;
            while ((line = reader.readLine()) != null)
            {
                writer.println(line);
            }
            //close our reader
            writer.close();
            // close our reader
            reader.close();
        }
        catch(IOException ex) {
            // there was some connection problem, or the file did not exist on the server,
            // or your URL was not in the right format.
            // think about what to do now, and put it here.
            ex.printStackTrace(); // for now, simply output it.
        }
    }

}
