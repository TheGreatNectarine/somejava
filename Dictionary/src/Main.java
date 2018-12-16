import java.io.*;

import static java.lang.System.nanoTime;

public class Main {
    public static void main(String[] args) {
        try {
            SearchDictionary t = new SearchDictionary();
            long time =
                    fill_dict(t, new File[]{
                            new File("/Users/nickmarhal/IdeaProjects/Dictionary/ROTEBAL.txt"),
                            new File("/Users/nickmarhal/IdeaProjects/Dictionary/words_alpha.txt")
                    });
            System.out.println("all words added to dictionary in "+(time)+" micro s");
            BufferedReader in    = new BufferedReader(new InputStreamReader(System.in));
            String         joker = "*";
            boolean        print = true;
            label:
            while (true) {
                System.out.print(">>> ");
                String query = in.readLine();
                time = nanoTime();
                switch (query) {
                    case "exit();":
                        break label;
                    case "joker();":
                        System.out.print("Joker: "+joker+"\nEnter a symbol of new joker.\n>>> ");
                        query = in.readLine();
                        joker = query;
                        continue;
                    case "delete();":
                        System.out.print(">>> ");
                        query = in.readLine();
                        t.delete(query);
                        System.out.println("success");
                        continue;
                    case "add();":
                        System.out.print(">>> ");
                        query = in.readLine();
                        t.addWord(query);
                        System.out.println("success");
                        continue;
                    case "count();":
                        System.out.println(t.count());
                        continue;
                    case "print();":
                        print = true;
                        System.out.println("print mode on");
                        continue;
                    case "no_print();":
                        print = false;
                        System.out.println("print mode off");
                        continue;
                }
                if (query.contains(joker)) {
                    try {
                        if (print) {
                            t.query(query, joker).forEach(System.out::println);
                        } else {
                            t.query(query, joker);
                        }
                    } catch (NullPointerException e) {
                        System.out.println(String.format("can't find words with prefix %s", query));
                    }
                } else {
                    System.out.println(t.search(query) ? "Dictionary has such word" : "No such "+
                            "word");
                }
                System.out.println("time of last search: "+(nanoTime()-time)/1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static long fill_dict(SearchDictionary dict, File[] files) throws IOException {
        long time = nanoTime();
        for (File f : files) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                dict.addWord(line);
            }
        }
        return nanoTime()-time;
    }
}
