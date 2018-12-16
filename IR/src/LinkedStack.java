import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> {

	private Node first = null;
	private int length = 0;
	
	public int length() {
		return length;
	}

	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void push(Item item){
		
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		length++;
	}
	
	public Item pop(){
		
		Item item = first.item;
		first = first.next;
		length--;
		return item;
	}
	
	public Item peek(){
		
		return first.item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{

		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {

			if(!hasNext()) throw new NoSuchElementException();
			
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
}
