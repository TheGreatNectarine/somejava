import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Term {

	private String name;
	private int frequency;
	// docID list in the form of integers with values being frequency of the word in the document
	private Map<Integer, Integer> docList;
	// docID list in the form of Documents (needed for coordinates index dictionary)
	private List<Document> docListD;
	
	// last added document to the doc ID list
	private Document lastAddedDocID;
	
	// if in document form, then docListD will be used. Otherwise - docList
	private boolean documentForm;
	
	Term(String name){
		this.name = name;
		lastAddedDocID = null;
		
		documentForm = false;
	}
	
	public Term(String name, boolean documentForm){
		this.name = name;
		lastAddedDocID = null;
        this.documentForm = documentForm;
	}
	
	public void addDocument(int docID){
		// if docID list is in the document form
		if(documentForm){
			Document previouslyAddedDocID = lastAddedDocID;
			lastAddedDocID = new Document(docID, name);
			
			if(docListD == null){
				docListD = new LinkedList<Document>();
				docListD.add(lastAddedDocID);
				frequency = 1;
			} else {
				// if the document ID was previously added to the list, return
				for(Document d: docListD){
					if(d.getDocumentID() == docID){
						// if no doc was added, return to the previously added doc
						lastAddedDocID = previouslyAddedDocID;
						return;
					}
				}
				// else add it and increment frequency
				docListD.add(lastAddedDocID);
				frequency++;
			}
			
		} else { // if not in document form
			// if it is the first occurance of the term, create new list of document IDs,
			// add doc ID into it and set frequency as 1
			if(docList == null){
				docList = new TreeMap<Integer, Integer>();
				docList.put(docID, 1);
				frequency = 1;
			} else {
				// if the document ID was previously added to the list, return
				for(Integer i: docList.keySet()){
					if(i == docID){
						// increment the frequency of the term in the document
						docList.put(i, docList.get(i) + 1);
						return;
					}
				}
				// else add it and increment frequency
				docList.put(docID, 1);
				frequency++;
			}
		}
		
	}

	public String getName() {
		return name;
	}

	public int getFrequency() {
		return frequency;
	}

	public Map<Integer, Integer> getDocList() {
		return docList;
	}
	
	public List<Document> getDocListD(){
		return docListD;
	}
	
	public Document getLastAddedDocID() {
		return lastAddedDocID;
	}

	public String toString(){
		
		StringBuilder res = new StringBuilder(name+" "+frequency+" -> ");
		
		if(!documentForm){
			for(Integer i: docList.keySet()){
				res.append(i).append(" ");
			}
		}
		else{
			for(Document d: docListD){
				res.append(d).append(" ");
			}
		}
		return res.toString();
	}
	
	public Document getDocumentByID(int id){
		for(Document d: docListD){
			if(d.getDocumentID() == id) return d;
		}
		return null;
	}
}
