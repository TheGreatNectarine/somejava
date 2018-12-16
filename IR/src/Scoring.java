import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ranzhuvannya =)

public class Scoring {

    private int K = 5;
    private double[] scores;
    private int[] docIDs;
    private int[] docSizes;

    private int queryLength;

    // will return top K doc indices
    public int[] cosineScoring(String query, BTree<String, Term> dictionary, List<Integer> docs, List<Integer> docLens, int k) {
        K = k;
        int size = docs.size();
        scores = new double[size];
        docIDs = new int[size];
        docSizes = new int[size];
        for (int i = 0; i < size; i++) {
            docIDs[i] = docs.get(i);
            docSizes[i] = docLens.get(i);
        }
        List<Term> tokenizedQuery = tokenizeQuery(query);
        for (Term term : tokenizedQuery) {
            // for each term in query, calculate its weight
            double queryTermWeight = calculateWeight(1, 1, term);
            // get documents, where term is present, and calculate weight for them
            if (dictionary.containsKey(term.getName())) {
                Term dictTerm = dictionary.get(term.getName());
                Map<Integer, Integer> termDocList = dictTerm.getDocList();
                for (Integer id : termDocList.keySet()) {
                    // add the calculated weight to scores array
                    scores[id-1] += queryTermWeight*calculateWeight(size, id, dictTerm);
                }
            }

        }
        // divide current document scores by document lengths
        for (int i = 0; i < size; i++) {
            scores[i] /= docSizes[i];
        }
        Double[] scoresWrapper = new Double[size];
        for (int i = 0; i < size; i++) {
            scoresWrapper[i] = scores[i];
        }
        MergeSort.sort(scoresWrapper, docIDs, size);
        int[] returnValues = new int[K];
        System.arraycopy(docIDs, 0, returnValues, 0, K);
        return returnValues;
    }

    private List<Term> tokenizeQuery(String query) {
        List<Term> tokenizedQuery = new ArrayList<>();
        queryLength = 0;
        StringBuilder nextWord = new StringBuilder();
        for (int i = 0; i < query.length(); i++) {
            char ch = query.charAt(i);
            if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                nextWord.append(Character.toLowerCase(ch));
            } else if (!nextWord.toString().equals("")) {
                Term term = new Term(nextWord.toString());
                if (!tokenizedQuery.contains(term))
                    tokenizedQuery.add(term);
                tokenizedQuery.get(tokenizedQuery.size()-1).addDocument(1);
                queryLength++;
                nextWord = new StringBuilder();
            }
        }
        if (!nextWord.toString().equals("")) {
            Term term = new Term(nextWord.toString());
            if (!tokenizedQuery.contains(term))
                tokenizedQuery.add(term);
            tokenizedQuery.get(tokenizedQuery.size()-1).addDocument(1);
            queryLength++;
        }
        return tokenizedQuery;
    }

    private double calculateWeight(int docsAmount, int docID, Term t) {
        // calculate inverse document frequency
        double idf = Math.log(docsAmount/(t.getFrequency()+1));
        // get term frequency in the document
        int tf = t.getDocList().get(docID);
        // combine TF and IDF and return
        return (idf*tf);
    }
}
