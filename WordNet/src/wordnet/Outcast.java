package wordnet;


public class Outcast {
    private WordNet net;

    public Outcast(WordNet wordnet) {
        net = wordnet;
    }

    public String outcast(String[] nouns) {
        int outcast = -1;
        int max_dst = -1;

        for (int i = 0; i < nouns.length; i++) {
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
//                if (j != i) {
                    dist += net.distance(nouns[i], nouns[j]);
//                }
            }
            if (dist > max_dst) {
                max_dst = dist;
                outcast = i;
            }
        }

        return nouns[outcast];
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet("/Users/nickmarhal/Downloads/Telegram Desktop/WordNet/src/synsets.txt", "/Users/nickmarhal/IdeaProjects/WordNet/src/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        System.out.println(outcast.outcast(new String[]{"linguistic_communication","zebra","dog"
        }));
    }
}
