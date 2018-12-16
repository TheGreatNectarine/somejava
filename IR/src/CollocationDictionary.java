import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollocationDictionary {
	
	// finished dictionary (HASH MAP BASED)
	private HashMap<String, Term> dictionary;

	// files to be read
	private File[] files;
	// how many files to be read
	private int filesAmount;
	// existing docIDs list
	private List<Integer> docs;
	
	// dictionary size
	private int size;
	// collection size
	private int collectionSize;
	// collection space used
	private long collectionSpace;
	// dictionary space used
	private long space;
	
	public CollocationDictionary(File[] files){
		this.files = files;
		filesAmount = files.length;
		
		buildDictionary();
	}
	
	private void buildDictionary(){
		collectionSize = 0;
		dictionary = new HashMap<String, Term>();
		
		readFromFiles();
		
		saveDictionary();
		
		//debug();
	}
	
	private void readFromFiles(){
		BufferedReader br;
		int currentDocID = 0;
		
		docs = new ArrayList<Integer>();
		
		collectionSpace = 0;
		size = 0;
		
		try{
			// read all the documents 1 by 1
			for(File f: files){
				// each document will have its own ID
				currentDocID++;
				docs.add(currentDocID);
				
				br = new BufferedReader(new FileReader(f));
				String nextWord = "";
				String prevWord = "";
				
				// tokenize the file
				String str = br.readLine();
				while(str != null){
					
					// iterate through input, and add the alphanumeric characters
					// to the next word to be added
					for(int i = 0; i < str.length(); i++){
						char ch = str.charAt(i);
						
						// if char is alphanumeric, convert it to lower case and add
						if(Character.isAlphabetic(ch) || Character.isDigit(ch)){
							nextWord += Character.toLowerCase(ch);
						}
						// if we get non-alphanumeric char and our word is not empty,
						// add the word and its document ID to the corresponding arrays,
						else if(!nextWord.equals("")){
							
							if(!prevWord.equals("")){
								
								// collocation to be added
								String toAdd = prevWord + " " + nextWord;
								
								// add the new term to dictionary if there is no such yet
								if(!dictionary.containsKey(toAdd)){
									dictionary.put(toAdd, new Term(toAdd));
									size++;
								}
								// add the document to the docID list of the term
								dictionary.get(toAdd).addDocument(currentDocID);
								// increment the total size of collection
								collectionSize++;
							}
							
							// set the previous word to be the current word and
							// reset the next word to be added
							prevWord = nextWord;
							nextWord = "";
						}
						
					}
					
					// add the last word in the line, if we have such
					if(!nextWord.equals("")){
						
						if(!prevWord.equals("")){
							
							String toAdd = prevWord + " " + nextWord;
							
							if(!dictionary.containsKey(toAdd)){
								dictionary.put(toAdd, new Term(toAdd));
								size++;
							}
							dictionary.get(toAdd).addDocument(currentDocID);
							collectionSize++;
						}
						
						prevWord = nextWord;
						nextWord = "";
					}
					
					// read next line
					str = br.readLine();
				}
					
				// add the size of current file to the total size of the collection
				collectionSpace += f.length();
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveDictionary(){
		try{
			// create a new text file and write dictionary to it
		    PrintWriter writer = new PrintWriter("collocation-dictionary.txt", "UTF-8");
			for(Term t: dictionary.values()){
				writer.println(t);
			}
		    writer.close();
		    
		    // get the space used by dictionary
		    File dictionary = new File("dictionary.txt");
		    space = dictionary.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getDocs() {
		return docs;
	}

	public HashMap<String, Term> getDictionary() {
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

	public double getSpace() {
		return space;
	}
	
	private void debug(){
		
		System.out.println("Total words in collection: " + collectionSize);
		System.out.println("Collection size: " + collectionSpace + " bytes");
		System.out.println("Total words in dictionary: " + size);
		System.out.println("Dictionary size: " + space + " bytes");
	}
	
	
	public Map<Integer, Integer> collocationSearch(String input){
		
		if(dictionary.containsKey(input))
			return dictionary.get(input).getDocList();
		else{
			System.out.println("No matches in the dictionary =(");
			return null;
		}
	}

}
