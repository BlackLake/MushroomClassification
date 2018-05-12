import java.io.*;

public class ImportHelper {

    public static void convertDataFiles() {

        File fileInput = new File("input.data");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;


        try {
            FileWriter fileWriter = new FileWriter("input-converted.data");
            PrintWriter printWriter = new PrintWriter(fileWriter);


            // printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);

            fis = new FileInputStream(fileInput);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {

                // this statement reads the line from the file and print it to
                // the console.
                //System.out.println(dis.readLine());

                String row = dis.readLine();
                String[] elements = row.split(",");

                if (elements.length != 23) {
                    throw new IOException("one line without the correct number of attributes");
                }

                String convertedLine = "";

                for (int i = 0; i < elements.length; i++) {

                }



                printWriter.println(dis.readLine());

            }

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String parseCapShape(String attribute) {
        switch (attribute) {
            case "b": return "1,0,0,0,0,0,";
            case "c": return "0,1,0,0,0,0,";
            case "x": return "0,0,1,0,0,0,";
            case "f": return "0,0,0,1,0,0,";
            case "k": return "0,0,0,0,1,0,";
            case "s": return "0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,";
        }
    }

    private String parseCapSurface(String attribute) {
        switch (attribute) {
            case "f": return "1,0,0,0,";
            case "g": return "0,1,0,0,";
            case "y": return "0,0,1,0,";
            case "s": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }

    private String parseCapColor(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,0,0,0,0,0,0,0,";
            case "b": return "0,1,0,0,0,0,0,0,0,0,";
            case "c": return "0,0,1,0,0,0,0,0,0,0,";
            case "g": return "0,0,0,1,0,0,0,0,0,0,";
            case "r": return "0,0,0,0,1,0,0,0,0,0,";
            case "p": return "0,0,0,0,0,1,0,0,0,0,";
            case "u": return "0,0,0,0,0,0,1,0,0,0,";
            case "e": return "0,0,0,0,0,0,0,1,0,0,";
            case "w": return "0,0,0,0,0,0,0,0,1,0,";
            case "y": return "0,0,0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,0,0,";
        }
    }

    private String parseHabitat(String attribute) {
        switch (attribute) {
            case "g": return "1,0,0,0,0,0,0,";
            case "l": return "0,1,0,0,0,0,0,";
            case "m": return "0,0,1,0,0,0,0,";
            case "p": return "0,0,0,1,0,0,0,";
            case "u": return "0,0,0,0,1,0,0,";
            case "w": return "0,0,0,0,0,1,0,";
            case "d": return "0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,";
        }
    }
    private String parsePopulation(String attribute) {
        switch (attribute) {
            case "a": return "1,0,0,0,0,0,";
            case "c": return "0,1,0,0,0,0,";
            case "n": return "0,0,1,0,0,0,";
            case "s": return "0,0,0,1,0,0,";
            case "v": return "0,0,0,0,1,0,";
            case "y": return "0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,";
        }
    }
    private String parseSporePrintColor(String attribute) {
        switch (attribute) {
            case "k": return "1,0,0,0,0,0,0,0,0,";
            case "n": return "0,1,0,0,0,0,0,0,0,";
            case "b": return "0,0,1,0,0,0,0,0,0,";
            case "h": return "0,0,0,1,0,0,0,0,0,";
            case "r": return "0,0,0,0,1,0,0,0,0,";
            case "o": return "0,0,0,0,0,1,0,0,0,";
            case "u": return "0,0,0,0,0,0,1,0,0,";
            case "w": return "0,0,0,0,0,0,0,1,0,";
            case "y": return "0,0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,0,";
        }
    }
    private String parseRingType(String attribute) {
        switch (attribute) {
            case "c": return "1,0,0,0,0,0,0,0,";
            case "e": return "0,1,0,0,0,0,0,0,";
            case "f": return "0,0,1,0,0,0,0,0,";
            case "l": return "0,0,0,1,0,0,0,0,";
            case "n": return "0,0,0,0,1,0,0,0,";
            case "p": return "0,0,0,0,0,1,0,0,";
            case "s": return "0,0,0,0,0,0,1,0,";
            case "z": return "0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,";
        }
    }
    private String parseRingNumber(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,";
            case "o": return "0,1,0,";
            case "t": return "0,0,1,";
            default: return "0,0,0,";
        }
    }
    private String parseVeilColor(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,0,";
            case "o": return "0,1,0,0,";
            case "w": return "0,0,1,0,";
            case "y": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }
    private String parseVeilType(String attribute) {
        switch (attribute) {
            case "p": return "1,0,";
            case "u": return "0,1,";
            default: return "0,0,";
        }
    }
    private String parseStalkColor(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,0,0,0,0,0,0,";
            case "b": return "0,1,0,0,0,0,0,0,0,";
            case "c": return "0,0,1,0,0,0,0,0,0,";
            case "g": return "0,0,0,1,0,0,0,0,0,";
            case "o": return "0,0,0,0,1,0,0,0,0,";
            case "p": return "0,0,0,0,0,1,0,0,0,";
            case "e": return "0,0,0,0,0,0,1,0,0,";
            case "w": return "0,0,0,0,0,0,0,1,0,";
            case "y": return "0,0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,0,";
        }
    }
}
