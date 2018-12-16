package wordnet;

import java.util.Arrays;

public class Synset {
    private final int      id;
    private final String   synset;
    private final String[] synonyms;
    private final String   description;

    public Synset(int id, String synset, String description) {
        this.id = id;
        this.synset = synset;
        this.description = description;
        synonyms = synset.split("\\s");
    }

    public int getId() {
        return id;
    }

    public String getSynset() {
        return synset;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Synset)) return false;

        Synset synset1 = (Synset) o;

        if (id != synset1.id) return false;
        if (synset != null ? !synset.equals(synset1.synset) : synset1.synset != null) return false;
        if (!Arrays.deepEquals(synonyms, synset1.synonyms)) return false;
        return description != null ? description.equals(synset1.description) : synset1.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31*result+(synset != null ? synset.hashCode() : 0);
        result = 31*result+Arrays.deepHashCode(synonyms);
        result = 31*result+(description != null ? description.hashCode() : 0);
        return result;
    }
}
