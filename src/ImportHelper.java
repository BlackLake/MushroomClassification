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
                System.out.println(dis.readLine());

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
}
