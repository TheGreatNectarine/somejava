import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class SearchDictionary {
    private class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean end;

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }
    }

    private TrieNode root;
    private int      count;

    public SearchDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            //set leaf node
            if (i == word.length()-1)
                t.end = true;
        }
        count += 1;
    }

    public boolean search(String word) {
        TrieNode t = searchNode(word);

        if (t != null && t.end)
            return true;
        else
            return false;
    }

    public boolean startsWith(String prefix) {
        if (searchNode(prefix) == null)
            return false;
        else
            return true;
    }

    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode                 t        = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }

        return t;
    }

    public Iterable<String> query(String query, String joker) {
        if (query.contains(joker))
            query = query.substring(0, query.indexOf(joker));
        List<String> dict = new LinkedList<>();
        TrieNode     pref;
        if (query.equals(""))
            pref = root;
        else
            pref = searchNode(query);
        add(pref, query, dict);
        return dict;
    }

    private void add(TrieNode curr, String word, List<String> s) {
        for (Character c : curr.children.keySet()) {
            if (curr.children.get(c).end) {
                s.add(word+c);
            }
            add(curr.children.get(c), word+c, s);
        }
    }

    public void delete(String word) {
        delete(root, word, 0);
        count -= 1;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.end) {
                return false;
            }
            current.end = false;
            return current.children.size() == 0;
        }
        char     ch   = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean delete = delete(node, word, index+1);

        if (delete) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }
        return false;
    }

    public Iterable<String> iterator() {
        return this.query("", "*");
    }

    public int count() {
        return count;
    }
}