import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ori Angel
 * ID: 314617739
 * Regexes class
 * Regexes have all the Regexes to scan the files.
 */
public class Regexes {
    private String[] arrRegex;

    /**
     * Constructor to create regex arr.
     */
    public Regexes() {
        String part1 = "<np>[a-zA-Z0-9 ]*<\\/np>(?: ,)? such as <np>[a-zA-Z0-9 ]*<\\/np>(?:((?: ,)? <np>[a-zA-Z0-9 ";
        String part2 = "]*<\\/np>){1,})?(?:( and | or )<np>[a-zA-Z0-9 ]*<\\/np>)?";
        String regex1 = part1 + part2;

        part1 = "such <np>[a-zA-Z0-9 ]*<\\/np> as <np>[a-zA-Z0-9 ]*<\\/np>(?:((?: ,)?";
        part2 = " <np>[a-zA-Z0-9 ]*<\\/np>)" + "{1,})?(?:((?: ,)? and |(?: ,)? or )<np>[a-zA-Z0-9 ]*<\\/np>)?";
        String regex2 = part1 + part2;

        part1 = "<np>[a-zA-Z0-9 ]*<\\/np>(?: ,)? including <np>[a-zA-Z0-9 ]*<\\/np>";
        part2 = "(?:((?: ,)? <np>[a-zA-Z0-9 ]*<\\/np>){1,})?(?:((?: ,)? and |(?: ,)? or )<np>[a-zA-Z0-9 ]*<\\/np>)?";
        String regex3 = part1 + part2;

        part1 = "<np>[a-zA-Z0-9 ]*<\\/np>(?: ,)? especially <np>[a-zA-Z0-9 ]*<\\/np>(?:((?: ,)?";
        part2 = " <np>[a-zA-Z0-9 " + "]*<\\/np>){1,})?(?:((?: ,)? and |(?: ,)? or )<np>[a-zA-Z0-9 ]*<\\/np>)?";
        String regex4 = part1 + part2;

        part1 = "<np>[a-zA-Z0-9 ]*<\\/np>(?: ,)? which is ";
        part2 = "(?:(?:an example |a kind |a class )?of )?<np>[a-zA-Z0-9 ]*<\\/np>";
        String regex5 = part1 + part2;
        arrRegex = new String[]{regex1, regex2, regex3, regex4, regex5};
    }

    /**
     * getArrRegex.
     *
     * @return the regex arr.
     */
    public String[] getArrRegex() {
        return arrRegex;
    }

    /**
     * buildMap.
     * build the map for print.
     *
     * @param file the file to scan.
     * @return the map.
     * @throws IOException if not find the file.
     */
    public Map<String, Map<String, Hyponym>> buildMap(String file) throws IOException {
        String fromFile = file;
//        String toFile = args[1];
        BufferedReader reader = null;
        Map<String, Map<String, Hyponym>> all = new TreeMap<>();
        try {
            File folder = new File(file);
            File[] listOfFiles = folder.listFiles();
            for (File f : listOfFiles) {
                for (int j = 0; j <= 4; j++) {
                    reader = new BufferedReader(new FileReader(f.getPath()));
                    String s;
                    Pattern pattern1 = Pattern.compile(this.getArrRegex()[j]);
                    while ((s = reader.readLine()) != null) {
                        Matcher matcher = pattern1.matcher(s);

                        while (matcher.find()) {
                            String sentence = matcher.group(0);
                            String importantPattern = "<np>([a-zA-Z0-9 ]*)<\\/np>";
                            Pattern importantPattern1 = Pattern.compile(importantPattern);
                            Matcher importantMatcher = importantPattern1.matcher(sentence);
                            boolean hypernym = true;
                            String hypernymSemanticWord = null;
                            while (importantMatcher.find()) {
                                if (hypernym) {
                                    hypernymSemanticWord = importantMatcher.group(1);
                                    hypernym = false;
                                } else {

                                    String hyponym = importantMatcher.group(1);
                                    //show in map
                                    if (all.containsKey(hypernymSemanticWord)) {
                                        //find hyponym
                                        Map<String, Hyponym> oneSemantic = all.get(hypernymSemanticWord);
                                        if (oneSemantic.containsKey(hyponym)) {
                                            Hyponym hy = oneSemantic.get(hyponym);
                                            hy.addCounter();
                                        } else {
                                            oneSemantic.put(hyponym, new Hyponym(hyponym, 1));
                                        }

                                    } else {
                                        Map<String, Hyponym> oneSemantic = new TreeMap<>();
                                        oneSemantic.put(hyponym, new Hyponym(hyponym, 1));
                                        all.put(hypernymSemanticWord, oneSemantic);
                                    }
//                            System.out.println(importantMatcher.group(1));

                                }
                            }
//                    System.out.println(matcher.group(0));
                        }
//                System.out.println(s);
                    }
                }
            }
//            for (int i = 0; i <= 83; i++) {
//                for (int j = 0; j <= 4; j++) {
//                    String fileName = fromFile + "\\" + "mbta.com_mtu.pages_" + i + ".possf2";
//                    reader = new BufferedReader(new FileReader(fileName));
//                    String s;
//                    Pattern pattern1 = Pattern.compile(this.getArrRegex()[j]);
//                    while ((s = reader.readLine()) != null) {
//                        Matcher matcher = pattern1.matcher(s);
//
//                        while (matcher.find()) {
//                            String sentence = matcher.group(0);
//                            String importantPattern = "<np>([a-zA-Z0-9 ]*)<\\/np>";
//                            Pattern importantPattern1 = Pattern.compile(importantPattern);
//                            Matcher importantMatcher = importantPattern1.matcher(sentence);
//                            boolean hypernym = true;
//                            String hypernymSemanticWord = null;
//                            while (importantMatcher.find()) {
//                                if (hypernym) {
//                                    hypernymSemanticWord = importantMatcher.group(1);
//                                    hypernym = false;
//                                } else {
//
//                                    String hyponym = importantMatcher.group(1);
//                                    //show in map
//                                    if (all.containsKey(hypernymSemanticWord)) {
//                                        //find hyponym
//                                        Map<String, Hyponym> oneSemantic = all.get(hypernymSemanticWord);
//                                        if (oneSemantic.containsKey(hyponym)) {
//                                            Hyponym hy = oneSemantic.get(hyponym);
//                                            hy.addCounter();
//                                        } else {
//                                            oneSemantic.put(hyponym, new Hyponym(hyponym, 1));
//                                        }
//
//                                    } else {
//                                        Map<String, Hyponym> oneSemantic = new TreeMap<>();
//                                        oneSemantic.put(hyponym, new Hyponym(hyponym, 1));
//                                        all.put(hypernymSemanticWord, oneSemantic);
//                                    }
////                            System.out.println(importantMatcher.group(1));
//
//                                }
//                            }
////                    System.out.println(matcher.group(0));
//                        }
////                System.out.println(s);
//                    }
//                }
//            }
//            return all;
//            printMap(all);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return all;
    }


}
