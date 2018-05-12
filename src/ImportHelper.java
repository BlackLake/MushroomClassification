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

                convertedLine = convertedLine + parseCapShape(elements[1]);
                convertedLine = convertedLine + parseCapSurface(elements[2]);
                convertedLine = convertedLine + parseCapColor(elements[3]);
                convertedLine = convertedLine + parseBruises(elements[4]);
                convertedLine = convertedLine + parseOdor(elements[5]);
                convertedLine = convertedLine + parseGillAtachement(elements[6]);
                convertedLine = convertedLine + parseGillSpacing(elements[7]);
                convertedLine = convertedLine + parseGillSize(elements[8]);
                convertedLine = convertedLine + parseGillColor(elements[9]);
                convertedLine = convertedLine + parseStalkShape(elements[10]);
                convertedLine = convertedLine + parseStalkRoot(elements[11]);
                convertedLine = convertedLine + parseStalkSurface(elements[12]);
                convertedLine = convertedLine + parseStalkSurface(elements[13]);
                convertedLine = convertedLine + parseStalkColor(elements[14]);
                convertedLine = convertedLine + parseStalkColor(elements[15]);
                convertedLine = convertedLine + parseVeilType(elements[16]);
                convertedLine = convertedLine + parseVeilColor(elements[17]);
                convertedLine = convertedLine + parseRingNumber(elements[18]);
                convertedLine = convertedLine + parseRingType(elements[19]);
                convertedLine = convertedLine + parseSporePrintColor(elements[20]);
                convertedLine = convertedLine + parsePopulation(elements[21]);
                convertedLine = convertedLine + parseHabitat(elements[22]);

                printWriter.println(convertedLine);
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

    private static String parseCapShape(String attribute) {
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

    private static String parseCapSurface(String attribute) {
        switch (attribute) {
            case "f": return "1,0,0,0,";
            case "g": return "0,1,0,0,";
            case "y": return "0,0,1,0,";
            case "s": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }

    private static String parseCapColor(String attribute) {
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

    private static String parseHabitat(String attribute) {
        switch (attribute) {
            case "g": return "1,0,0,0,0,0,0";
            case "l": return "0,1,0,0,0,0,0";
            case "m": return "0,0,1,0,0,0,0";
            case "p": return "0,0,0,1,0,0,0";
            case "u": return "0,0,0,0,1,0,0";
            case "w": return "0,0,0,0,0,1,0";
            case "d": return "0,0,0,0,0,0,1";
            default: return "0,0,0,0,0,0,0";
        }
    }
    private static String parsePopulation(String attribute) {
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
    private static String parseSporePrintColor(String attribute) {
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
    private static String parseRingType(String attribute) {
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
    private static String parseRingNumber(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,";
            case "o": return "0,1,0,";
            case "t": return "0,0,1,";
            default: return "0,0,0,";
        }
    }
    private static String parseVeilColor(String attribute) {
        switch (attribute) {
            case "n": return "1,0,0,0,";
            case "o": return "0,1,0,0,";
            case "w": return "0,0,1,0,";
            case "y": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }
    private static String parseVeilType(String attribute) {
        switch (attribute) {
            case "p": return "1,0,";
            case "u": return "0,1,";
            default: return "0,0,";
        }
    }
    private static String parseStalkColor(String attribute) {
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

    private static String parseBruises(String attribute) {
        switch (attribute) {
            case "t": return "1,";
            case "f": return "0,";
            default: return "0,";
        }
    }

    private static String parseOdor(String attribute) {
        switch (attribute) {
            case "a": return "1,0,0,0,0,0,0,0,0,";
            case "l": return "0,1,0,0,0,0,0,0,0,";
            case "c": return "0,0,1,0,0,0,0,0,0,";
            case "y": return "0,0,0,1,0,0,0,0,0,";
            case "f": return "0,0,0,0,1,0,0,0,0,";
            case "m": return "0,0,0,0,0,1,0,0,0,";
            case "n": return "0,0,0,0,0,0,1,0,0,";
            case "p": return "0,0,0,0,0,0,0,1,0,";
            case "s": return "0,0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,0,";
        }
    }

    private static String parseGillAtachement(String attribute) {
        switch (attribute) {
            case "a": return "1,0,0,0,";
            case "d": return "0,1,0,0,";
            case "f": return "0,0,1,0,";
            case "n": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }

    private static String parseGillSpacing(String attribute) {
        switch (attribute) {
            case "c": return "1,0,0,";
            case "w": return "0,1,0,";
            case "d": return "0,0,1,";
            default: return "0,0,0,";
        }
    }

    private static String parseGillSize(String attribute) {
        switch (attribute) {
            case "b": return "1,";
            case "n": return "0,";
            default: return "0,";
        }
    }

    private static String parseGillColor(String attribute) {
        switch (attribute) {
            case "k": return "1,0,0,0,0,0,0,0,0,0,0,0,";
            case "n": return "0,1,0,0,0,0,0,0,0,0,0,0,";
            case "b": return "0,0,1,0,0,0,0,0,0,0,0,0,";
            case "h": return "0,0,0,1,0,0,0,0,0,0,0,0,";
            case "g": return "0,0,0,0,1,0,0,0,0,0,0,0,";
            case "r": return "0,0,0,0,0,1,0,0,0,0,0,0,";
            case "o": return "0,0,0,0,0,0,1,0,0,0,0,0,";
            case "p": return "0,0,0,0,0,0,0,1,0,0,0,0,";
            case "u": return "0,0,0,0,0,0,0,0,1,0,0,0,";
            case "e": return "0,0,0,0,0,0,0,0,0,1,0,0,";
            case "w": return "0,0,0,0,0,0,0,0,0,0,1,0,";
            case "y": return "0,0,0,0,0,0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,0,0,0,0,0,";
        }
    }

    private static String parseStalkShape(String attribute) {
        switch (attribute) {
            case "e": return "1,";
            case "t": return "0,";
            default: return "0,";
        }
    }

    private static String parseStalkRoot(String attribute) {
        switch (attribute) {
            case "b": return "1,0,0,0,0,0,0,";
            case "c": return "0,1,0,0,0,0,0,";
            case "u": return "0,0,1,0,0,0,0,";
            case "e": return "0,0,0,1,0,0,0,";
            case "z": return "0,0,0,0,1,0,0,";
            case "r": return "0,0,0,0,0,1,0,";
            case "?": return "0,0,0,0,0,0,1,";
            default: return "0,0,0,0,0,0,0,";
        }
    }

    private static String parseStalkSurface(String attribute) {
        switch (attribute) {
            case "f": return "1,0,0,0,";
            case "y": return "0,1,0,0,";
            case "k": return "0,0,1,0,";
            case "s": return "0,0,0,1,";
            default: return "0,0,0,0,";
        }
    }

}
