import java.io.IOException;
import java.io.PrintWriter;

public class Compression {

    private CompressedTerm[] invertedIndex;

    Compression(BTree<String, Term> sortedIndex) {

        copyToCompressedIndex(sortedIndex);
        writeToFile();
    }

    private void copyToCompressedIndex(BTree<String, Term> sortedIndex) {
        invertedIndex = new CompressedTerm[1];
        int counter = 0;
        for (Term t : sortedIndex.values()) {
            if (counter == invertedIndex.length)
                resize();
            invertedIndex[counter] = new CompressedTerm(t);
            counter++;
        }
    }

    private void resize() {
        int len = invertedIndex.length;
        CompressedTerm[] temp = new CompressedTerm[len*2];
        System.arraycopy(invertedIndex, 0, temp, 0, len);

        invertedIndex = temp;
    }

    private void writeToFile() {
        try {
            PrintWriter writer = new PrintWriter("compressedDictionary.txt", "UTF-8");
            writer.println(CompressedTerm.getDictionary());
            for (CompressedTerm t : invertedIndex) {
                writer.println(t);
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void debug() {

    }
}
