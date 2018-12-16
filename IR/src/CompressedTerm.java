import java.util.Map;

public class CompressedTerm {

    // alphabetically sorted dictionary, stored as a single line of text
    private static String dictionary = "";

    private int frequency;
    private Map<Integer, Integer> docList;
    private int termPosition;

    CompressedTerm(Term term) {
        frequency = term.getFrequency();
        docList = term.getDocList();
        termPosition = dictionary.length();
        dictionary += term.getName();
    }

    public String toString() {
        StringBuilder res = new StringBuilder(termPosition+" "+frequency+" -> ");

        for (Integer i : docList.keySet()) {
            res.append(i).append(" ");
        }
        return res.toString();
    }

    public static String getDictionary() {
        return dictionary;
    }

}
