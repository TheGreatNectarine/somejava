package wordnet;

import utils.DepthFirstPaths;
import utils.Digraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WordNet {
    private HashMap<Integer, Synset> synsets;
    private HashMap<Synset, Integer> synsets_reversed;
    private SAP                      sap_hypernyms;
    private int                      size;

    public WordNet(String f_synsets, String f_hypernyms) {
        parse_synsets(f_synsets);
        parse_hypernyms(f_hypernyms);

    }

    public Iterable<String> nouns() {
        Set<String> s = new LinkedHashSet<>();
        synsets.forEach((id, synset) -> s.add(synset.getSynset()));
        return s;
    }


    public boolean isNoun(String word) {
        return synsets.containsValue(getSynsetByWord(word));
    }

    public int distance(String nounA, String nounB) {
        checkNouns(nounA, nounB);
        int indA = synsets_reversed.get(getSynsetByWord(nounA));
        int indB = synsets_reversed.get(getSynsetByWord(nounB));

        return sap_hypernyms.length(indA, indB);
    }


    public String sap(String nounA, String nounB) {
        checkNouns(nounA, nounB);
        int indA = synsets_reversed.get(getSynsetByWord(nounA));
        int indB = synsets_reversed.get(getSynsetByWord(nounB));

        return synsets.get(sap_hypernyms.ancestor(indA, indB)).getSynset();
    }

    private void checkNouns(String... toBeChecked) {
        checkNouns(Arrays.asList(toBeChecked));
    }

    private void checkNouns(Iterable<String> toBeChecked) {
        for (String checkMe : toBeChecked) {
            if (!isNoun(checkMe)) {
                throw new IllegalArgumentException(String.format(
                        "Input parameter '%s' not a noun", checkMe));
            }
        }
    }

    private Synset getSynsetByWord(String noun) {
        for (Synset s : synsets.values()) {
            if (Arrays.stream(s.getSynonyms()).anyMatch(word -> word.equals(noun))) {
//                System.out.println(noun+" --- "+Arrays.toString(s.getSynonyms()));
                return s;
            }
        }
        return null;
    }

    private void parse_synsets(String f_synsets) {
        synsets = new HashMap<>();
        synsets_reversed = new HashMap<>();
        File file_synsets = new File(f_synsets);
        try (BufferedReader br = new BufferedReader(new FileReader(file_synsets))) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                size += 1;
                String[] line_sp = line.split(",");
                Synset   synset  = new Synset(Integer.parseInt(line_sp[0]), line_sp[1], line_sp[2]);
//                if (!synsets.containsValue(synset)) {
                synsets.put(Integer.parseInt(line_sp[0]), synset);
                synsets_reversed.put(synset, Integer.parseInt(line_sp[0]));
//                }
//                System.out.println(size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse_hypernyms(String f_hypernyms) {
        Digraph g         = new Digraph(size);
        File    hypernyms = new File(f_hypernyms);
        int     root_cnt  = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(hypernyms))) {
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                int[] IDs = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                if (IDs.length == 0) {
                    root_cnt += 1;
                }
                for (int i = 1; i < IDs.length; i++) {
                    g.addEdge(IDs[0], IDs[i]);
                }
                if (root_cnt > 1) {
                    throw new IllegalArgumentException("graph has more than one root");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sap_hypernyms = new SAP(g);
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet("/Users/nickmarhal/IdeaProjects/WordNet/src/synsets.txt", "/Users/nickmarhal/IdeaProjects/WordNet/src/hypernyms.txt");
        System.out.println("distance(Black_Plague, black_marlin): \t\t"
                +wordnet.distance("white", "red"));
        System.out.println("distance(American_water_spaniel, American_water_spaniel): \t"
                +wordnet.distance("American_water_spaniel", "American_water_spaniel"));
        System.out.println("distance(ASL, linguistic_communication): \t\t"
                +wordnet.distance("ASL", "linguistic_communication"));
        System.out.println("distance(dog, cat): \t\t"
                +wordnet.distance("dog", "cat"));

    }
}
