import java.io.File;
import java.util.*;

public class Clustering {

    private BTree<String, Term> dictionary;
    private List<File> files;
    // b1 most relevant leaders of a follower
    private static final int B1 = 3;
    // b2 most relevant documents (leader + its followers) at the query stage
    private static final int B2 = 5;
    private int docsNum;
    private int leadersNum;
    private int followersNum;
    private Map<Integer, ArrayList<Integer>> leaders;
    private int[] followers;

    public Clustering(BTree<String, Term> dictionary, List<Integer> docs, File[] files) {
        this.dictionary = dictionary;
        docsNum = docs.size();
        leadersNum = (int) Math.sqrt(docsNum);
        followersNum = docsNum-leadersNum;
        leaders = new TreeMap<>();

        // rewrite doc IDs to helper list
        List<Integer> docsHelper = new ArrayList<>(docs);

        this.files = new ArrayList<>();
        Collections.addAll(this.files, files);

        assignLeaders(docsHelper);
        assignFollowers();
    }

    private void assignLeaders(List<Integer> docs) {
        Random rand = new Random();

        // randomly get sqrt(docsNum) leaders
        for (int i = 0; i < leadersNum; i++) {
            int next = rand.nextInt(docsNum)+1;

            // if leader has not been taken out yet, get him
            if (docs.contains(next)) {
                leaders.put(next, new ArrayList<>());
                docs.remove(next);
            } else {
                --i;
            }

        }

        followers = new int[followersNum];

        // move the rest to followers array
        int counter = 0;
        for (Integer doc : docs) {
            followers[counter] = doc;
        }
    }

    private void assignFollowers() {
        Scoring scoring = new Scoring();
        for (int i : followers) {
            String query = files.get(i).toString();
            int[] scored = scoring.cosineScoring(query, dictionary, null, null, B1);
            for (int lead : scored) {
                leaders.get(lead).add(i);
            }
        }
    }

    public int[] search(String query) {
        Scoring scoring = new Scoring();
        // get the most relevant leader
        int[] relevantLeader = scoring.cosineScoring(query, dictionary, null, null, 1);

        // get B2 most relevant documents, taking into consideration most relevant leader and its followers
        List<Integer> listToGetFrom = new ArrayList<>();
        listToGetFrom.add(relevantLeader[0]);
        listToGetFrom.addAll(leaders.get(relevantLeader[0]));

        return scoring.cosineScoring(query, dictionary, listToGetFrom, null, B2);
    }

}
