import java.util.ArrayList;
import java.util.List;

public class Document {

	private int documentID;
	private List<Integer> coordinates;
	
	private String relatedWord;
	
	public Document(int docID, String relatedWord){
		this.relatedWord = relatedWord;
		documentID = docID;
		
		coordinates = new ArrayList<>();
	}
	
	public void addCoordinate(int coordinate){
		coordinates.add(coordinate);
	}

	public int getDocumentID() {
		return documentID;
	}

	public List<Integer> getCoordinates() {
		return coordinates;
	}

	public String getRelatedWord() {
		return relatedWord;
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder(documentID+": (");
		for(Integer i: coordinates){
			res.append(i).append(" ");
		}
		
		//remove the last whitespace from the result and add DUZHKA
		res = new StringBuilder(res.substring(0, res.length()-1)+")");
		return res.toString();
	}

	public int getCoordinate(Integer key) {
        int lo = 0;
        int hi = coordinates.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key.compareTo(coordinates.get(mid)) < 0) hi = mid - 1;
            else if (key.compareTo(coordinates.get(mid)) > 0) lo = mid + 1;
            else return mid;
        }
        // if coordinate does not exist, return -1
        return -1;
    }
}
