import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

    private BTree<String, Term> dictionary;
    // existing docIDs list
    private List<Integer> docs;
    // TODO (and to rethink) docs lengths
    private List<Integer> docsLengths;

    // files to be read
    private File[] files;
    // how many files to be read
    private int filesAmount;

    private int size;
    private int collectionSize;
    private long collectionSpace;
    private long dictSpace;

    private Compression compressedDictionary;

    private Dictionary(File[] files) {
        this.files = files;
        filesAmount = files.length;

        buildDictionary();
    }

    private void buildDictionary() {
        collectionSize = 0;
        dictionary = new BTree<>();
        readFromFiles();
        saveDictionary();
        debug();
        compressedDictionary = new Compression(dictionary);

    }

    private void readFromFiles() {
        BufferedReader br;
        int currentDocID = 0;
        docs = new ArrayList<>();
        docsLengths = new ArrayList<>();
        collectionSpace = 0;
        size = 0;
        try {
            for (File f : files) {
                currentDocID++;
                docs.add(currentDocID);
                int docLengthCounter = 0;
                br = new BufferedReader(new FileReader(f));
                String nextWord = "";
                String str;
                while ((str = br.readLine()) != null) {
                    for (int i = 0; i < str.length(); i++) {
                        char ch = str.charAt(i);
                        if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                            nextWord += Character.toLowerCase(ch);
                        } else if (!nextWord.equals("")) {
                            if (!dictionary.containsKey(nextWord)) {
                                dictionary.put(nextWord, new Term(nextWord));
                                size++;
                            }
                            dictionary.get(nextWord).addDocument(currentDocID);
                            collectionSize++;
                            docLengthCounter++;
                            nextWord = "";
                        }

                    }
                }

                // add the size of current file to the total size of the collection
                collectionSpace += f.length();

                // add the new length of the document
                docsLengths.add(docLengthCounter);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDictionary() {
        try {
            PrintWriter writer = new PrintWriter("dictionary.txt", "UTF-8");
            for (Term t : dictionary.values()) {
                writer.println(t);
            }
            writer.close();
            File dictionary = new File("dictionary.txt");
            dictSpace = dictionary.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getDocs() {
        return docs;
    }

    public BTree<String, Term> getDictionary() {
        return dictionary;
    }

    public int getSize() {
        return size;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

    public double getCollectionSpace() {
        return collectionSpace;
    }

    public double getDictSpace() {
        return dictSpace;
    }


    private void scoringSearch(String input) {

        Scoring scr = new Scoring();

        int[] wordList = scr.cosineScoring(input, dictionary, docs, docsLengths, 5);
        StringBuilder res = new StringBuilder();
        for (int i : wordList) {
            res.append(i).append(" ");
        }
        System.out.println("Occurs in such documents (from most to least relevant): "+res);
        //}
    }

    private void debug() {
        System.out.println("Total words in collection: "+collectionSize);
        System.out.println("Collection size: "+collectionSpace+" bytes");
        System.out.println("Total words in dictionary: "+size);
        System.out.println("Dictionary size: "+dictSpace+" bytes");
    }

    public static void main(String[] args) {
        Stopwatch st = new Stopwatch();
        File[] files = new File[10];
        for (int i = 1; i <= files.length; i++) {
            files[i-1] = new File(i+".txt");
        }
        Dictionary dict = new Dictionary(files);
        System.out.println("Time elapsed: "+st.elapsedTime());
        dict.compressedDictionary.debug();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nType your input (type 0, if you want to stop): ");
            String input = sc.nextLine();
            if (input.equals("0")) break;
            dict.scoringSearch(input);
        }
    }
}
