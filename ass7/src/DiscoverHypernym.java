import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Ori Angel
 * ID: 314617739
 * DiscoverHypernym class
 * DiscoverHypernym implement part 2.
 */
public class DiscoverHypernym {
    /**
     * Main.
     * DiscoverHypernym.
     *
     * @param args with address and lemma.
     * @throws IOException if not find the file.
     */
    public static void main(String[] args) throws IOException {
        String fromFile = args[0];
        String lemma = args[1];
        Regexes regexes = new Regexes();
        Map<String, Map<String, Hyponym>> all = regexes.buildMap(fromFile);
        printHypernymList(all, lemma);
    }
    /**
     * printHypernymList.
     *
     * @param all with address and lemma.
     * @param  lemma if not find the file.
     */
    private static void printHypernymList(Map<String, Map<String, Hyponym>> all, String lemma) {
        List<Hyponym> hypernymArr = new ArrayList<>();
        for (Map.Entry entry : all.entrySet()) {
            Map<String, Hyponym> oneSemantic = all.get(entry.getKey());
            String hypernym = (String) entry.getKey();
            for (Map.Entry word : oneSemantic.entrySet()) {
                Hyponym h = (Hyponym) word.getValue();
                int number = h.getNumber();
                if (h.getName().equals(lemma)) {
                    hypernymArr.add(new Hyponym(hypernym, h.getNumber()));
                }
            }
        }
        //descending order sort
        for (int i = 0; i < hypernymArr.size(); i++) {
            for (int k = 0; k < hypernymArr.size(); k++) {
                if (hypernymArr.get(i).getNumber() > hypernymArr.get(k).getNumber()) {
                    // Swap
                    Hyponym temp = hypernymArr.get(k);
                    hypernymArr.set(k, hypernymArr.get(i));
                    hypernymArr.set(i, temp);
                }
            }
        }
        for (int j = 0; j < hypernymArr.size(); j++) {
            System.out.print(hypernymArr.get(j).getName() + ": " + "(" + hypernymArr.get(j).getNumber() + ")");
            System.out.println();
        }
    }
}
