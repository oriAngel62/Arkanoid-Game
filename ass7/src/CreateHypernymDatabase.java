import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * @author Ori Angel
 * ID: 314617739
 * CreateHypernymDatabase class
 * CreateHypernymDatabase scan the files and create file.
 */
public class CreateHypernymDatabase {
    /**
     * Main.
     * CreateHypernymDatabase.
     *
     * @param args with address and file to print the answers.
     * @throws IOException if not find the file.
     */

    public static void main(String[] args) throws IOException {
        String fromFile = args[0];
        String toFile = args[1];
        Regexes regexes = new Regexes();
        Map<String, Map<String, Hyponym>> all = regexes.buildMap(fromFile);
        createFile(all, toFile);
        printMap(all);
    }

    /**
     * printMap.
     * print the map.
     *
     * @param all is the map to print.
     */
    public static void printMap(Map<String, Map<String, Hyponym>> all) {
        for (Map.Entry entry : all.entrySet()) {
            Map<String, Hyponym> oneSemantic = all.get(entry.getKey());
            if (oneSemantic.size() >= 3) {
                System.out.print(entry.getKey() + ": ");
                Hyponym[] hyponyArr = new Hyponym[oneSemantic.size()];
                int i = 0;
                for (Map.Entry word : oneSemantic.entrySet()) {
                    Hyponym h = (Hyponym) word.getValue();
                    int number = h.getNumber();
                    hyponyArr[i] = h;
                    i++;
                }
                //descending order sort
                for (i = 0; i < hyponyArr.length; i++) {
                    for (int k = 0; k < hyponyArr.length; k++) {
                        if (hyponyArr[i].getNumber() > hyponyArr[k].getNumber()) {
                            // Swap
                            Hyponym temp = hyponyArr[k];
                            hyponyArr[k] = hyponyArr[i];
                            hyponyArr[i] = temp;
                        }
                    }
                }
                for (int j = 0; j < hyponyArr.length; j++) {
                    System.out.print(hyponyArr[j].getName() + " " + hyponyArr[j].getNumber() + ", ");
                }
                System.out.println();
            }
        }
    }

    /**
     * CreateFile.
     * create the file to write the results.
     *
     * @param all    is the map to print.
     * @param toFile is the file to write.
     */
    private static void createFile(Map<String, Map<String, Hyponym>> all, String toFile) {
        try {
            File myObj = new File(toFile);
            myObj.createNewFile();
            writeToFile(all, myObj.getName());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * WriteToFile.
     * Write to the file the results.
     *
     * @param all      is the map to print.
     * @param fileName is the file to write.
     */
    private static void writeToFile(Map<String, Map<String, Hyponym>> all, String fileName) {
        try {
            PrintStream fileStream = new PrintStream(new File(fileName));
            for (Map.Entry entry : all.entrySet()) {
                Map<String, Hyponym> oneSemantic = all.get(entry.getKey());
                if (oneSemantic.size() >= 3) {
                    System.out.print(entry.getKey() + ": ");
                    fileStream.print(entry.getKey() + ": ");
                    Hyponym[] hyponyArr = new Hyponym[oneSemantic.size()];
                    int i = 0;
                    for (Map.Entry word : oneSemantic.entrySet()) {
                        Hyponym h = (Hyponym) word.getValue();
                        int number = h.getNumber();
                        hyponyArr[i] = h;
                        i++;
                    }
                    //descending order sort
                    for (i = 0; i < hyponyArr.length; i++) {
                        for (int k = 0; k < hyponyArr.length; k++) {
                            if (hyponyArr[i].getNumber() > hyponyArr[k].getNumber()) {
                                // Swap
                                Hyponym temp = hyponyArr[k];
                                hyponyArr[k] = hyponyArr[i];
                                hyponyArr[i] = temp;
                            }
                        }
                    }
                    for (int j = 0; j < hyponyArr.length - 1; j++) {
                        System.out.print(hyponyArr[j].getName() + " " + hyponyArr[j].getNumber() + ", ");
                        fileStream.print(hyponyArr[j].getName() + " " + hyponyArr[j].getNumber() + ", ");
                    }
                    System.out.print(hyponyArr[hyponyArr.length - 1].getName()
                            + " " + hyponyArr[hyponyArr.length - 1].getNumber());
                    fileStream.print(hyponyArr[hyponyArr.length - 1].getName()
                            + " " + hyponyArr[hyponyArr.length - 1].getNumber());
                    System.out.println();
                    fileStream.println();
                }
            }
            fileStream.close();
//            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

