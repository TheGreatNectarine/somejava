import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BooleanSearchNew {
	
	static int debugCounter = 0;
	
	private static final int WAITING_NOT_OTHERWISE_WORD = 0;
	private static final int WAITING_OPERATOR = 1;
	private static final int WAITING_WORD = 2;

	public static List<Integer> search(Dictionary dictionary, String input){
		
		LinkedStack<Object> stack = new LinkedStack<Object>();

		int wordCount = 0;
		String toAdd = "";
		
		// iterate through input, and add the non-whitespace characters
		// to the next-to-add word
		for(int i = 0; i < input.length(); i++){
			char ch = input.charAt(i);
			
			// if we get non-whitespace char, add it to the word
			if(Character.isAlphabetic(ch) || Character.isDigit(ch)){
				toAdd += ch;
			}
			else if(ch == '('){
				// first push the token to be added 
				if(!toAdd.equals("")){
					stack.push(toAdd);
					wordCount++;
					toAdd = "";
				}
				// add "("
				stack.push("(");
				wordCount++;
			}
			else if(ch == ')'){
				// first push the token to be added 
				if(!toAdd.equals("")){
					stack.push(toAdd);
					wordCount++;
					toAdd = "";
				}
				
				// start algorithm SVORACHIVANIYA
				// create another linked stack for discrete boolean search
				LinkedStack<Object> workingList = new LinkedStack<Object>();
				// we will pop objects from the stack until we meet "("
				// or the end of the stack
				Object lastPopped = null;
				if(!stack.isEmpty()) lastPopped = stack.pop();
				while(!stack.isEmpty() && !lastPopped.equals("(")){
					workingList.push(lastPopped);
					
					lastPopped = stack.pop();
				}
				
				// do all boolean operations to the working list and
				// then write the List<Integer> result to the original stack
				stack.push(calculateExpression(dictionary, workingList));
				
			}
			// if we get a whitespace char and our word is not empty,
			// add the word to the boolean search request array,
			// increment the request word counter
			else if(Character.isWhitespace(ch) && !toAdd.equals("")){
				stack.push(toAdd);
				wordCount++;
				toAdd = "";
			}
			// if we get whitespace char, but our word it still empty, continue
			// if we get a char that matches non of the above, continue
		}
		
		// add the last word in the input, if we have such
		if(!toAdd.equals("")){
			stack.push(toAdd);
			wordCount++;
		}
		
		// (?) provide a different solution
		LinkedStack<Object> workingList = new LinkedStack<Object>();
		for(Object obj: stack) workingList.push(obj);
		// then start algorithm SVORACHIVANIYA
		return calculateExpression(dictionary, workingList);
	}
	
	private static List<Integer> calculateExpression(Dictionary dictionary, LinkedStack<Object> workingList) throws InputMismatchException{
		// if working list if empty, return empty list
		if(workingList.isEmpty()){
			return new LinkedList<Integer>();
		}
		else if(workingList.length() == 1){
			Object workingObj = workingList.pop();
			// if the token is an operator, we throw an exception
			if(workingObj instanceof String && isOperator((String) workingObj)){
				throw new InputMismatchException("Incorrect input. Try again");
			}
			// we return the result, if token is a word
			return convertToList(dictionary, workingObj);
		}
		else{
			// create list where we will be adding our single boolean operations
			List<Object> opList = new LinkedList<Object>();
			int status = WAITING_NOT_OTHERWISE_WORD;
			Object toAdd;
			
			// work on the incoming stack, until there is one object left there
			while(workingList.length() != 0){
				
				// pop the last word or operator from the stack
				toAdd = workingList.pop();
				
				// if it was the last word popped
				if(workingList.length() == 0){
					// if the last token is an operator, we throw an exception
					if(toAdd instanceof String && isOperator((String) toAdd)){
						throw new InputMismatchException("Incorrect input. Try again");
					}
					// we return the result, if all the operations have been complete
					if(opList.isEmpty()){
						return convertToList(dictionary, toAdd);
					}
					
					//else continue to the operations block
				}
				// if we expect for NOT operator or word
				if(status == WAITING_NOT_OTHERWISE_WORD){
					// if NOT operator comes, we add it to the boolean operation list
					// and wait for word to come next
					if(toAdd instanceof String && isNOT((String) toAdd)){
						opList.add(toAdd);
						status = WAITING_WORD;
						// if we get ADD or OR, throw an incorrect input exception
					} else if(toAdd instanceof String && isOperator((String) toAdd)){
						throw new InputMismatchException("Incorrect input. Try again");
					} else{
						// if we get a word or doc ID list, add to the list and
						// wait for operator to come next
						opList.add(toAdd);
						status = WAITING_OPERATOR;
						
						// if the word added is not the first in the list
						// for cases (word AND/OR word) perform boolean operation
						if(opList.size() > 1){
							workingList.push(performOperation(dictionary, opList));
							opList = new LinkedList<Object>();
							status = WAITING_NOT_OTHERWISE_WORD;
						}
					}
				}
				// if we expect only a word
				else if(status == WAITING_WORD){
					if(toAdd instanceof String && isOperator((String) toAdd)){
						// throw and exception if any operator comes
						throw new InputMismatchException("Incorrect input. Try again");
					}
					else{
						// whenever a word comes after the operator, a boolean operation is performed
						opList.add(toAdd);
						workingList.push(performOperation(dictionary, opList));
						opList = new LinkedList<Object>();
						status = WAITING_NOT_OTHERWISE_WORD;
					}
				}
				// if we expect an operator
				else{
					if(toAdd instanceof String && isNOT((String) toAdd)){
						// if we get NOT operator, than only word can come next
						opList.add(toAdd);
						status = WAITING_WORD;
						// if we get AND or OR, than word or NOT can come next
					} else if(toAdd instanceof String && isOperator((String) toAdd)){
						opList.add(toAdd);
						status = WAITING_NOT_OTHERWISE_WORD;
					} else{
						// if we get a word or doc ID list, throw an exception
						throw new InputMismatchException("Incorrect input. Try again");
					}
				}
			}
			
		}
		
		// return empty list
		return new LinkedList<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	private static List<Integer> performOperation(Dictionary dictionary, List<Object> opList){
		// cases (NOT word)
		if(opList.size() == 2){
			// get the word
			// if it is in string format, get the list from the dictionary
			List<Integer> opObj = convertToList(dictionary, opList.get(1));
			return operatorNOT(dictionary.getDocs(), opObj);
		}
		// cases (word AND/OR/NOT word)
		else if(opList.size() == 3){
			List<Integer> opObj1 = convertToList(dictionary, opList.get(0));
			List<Integer> opObj2 = convertToList(dictionary, opList.get(2));
			String operator = (String) opList.get(1);
			
			if(isNOT(operator)){
				return operatorNOT(opObj1, opObj2);
			}
			else if(isOR(operator)){
				return operatorOR(opObj1, opObj2);
			}
			
			return operatorAND(opObj1, opObj2);
		}
		// cases (word AND/OR NOT word)
		else if(opList.size() == 4){
			List<Integer> opObj1 = convertToList(dictionary, opList.get(0));
			List<Integer> opObj2 = convertToList(dictionary, opList.get(3));
			String operator = (String) opList.get(1);
			
			if(isOR(operator)){
				// if we get (word NOT word), recursively do the operation (NOT word)
				List<Object> smallerOpList = new LinkedList<Object>();
				smallerOpList.add("NOT");
				smallerOpList.add(opObj2);
				opObj2 = performOperation(dictionary, smallerOpList);
				
				return operatorOR(opObj1, opObj2);
			}
			return operatorNOT(opObj1, opObj2);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Integer> convertToList(Dictionary dictionary, Object opObj){
		
		if(opObj instanceof String){
			// if the word wasn't found, return an empty list
			if(!dictionary.getDictionary().containsKey((String)opObj))
				return new LinkedList<Integer>();
			
			opObj = dictionary.getDictionary().get((String)opObj).getDocList();
		}
		return (List<Integer>) opObj;
	}
	
	public static List<Integer> operatorAND(List<Integer> docList1, List<Integer> docList2){
		Iterator<Integer> it1 = docList1.iterator();
		Iterator<Integer> it2 = docList2.iterator();
		
		List<Integer> result = new LinkedList<Integer>();
		
		int curDoc1 = 0;
		int curDoc2 = 0;
		
		while(it1.hasNext()){
			curDoc1 = it1.next();
			
			while(it2.hasNext() && curDoc2 < curDoc1){
				curDoc2 = it2.next();
			}
			
			if(curDoc1 == curDoc2) result.add(curDoc1);
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	private static List<Integer> operatorOR(List<Integer> docList1, List<Integer> docList2){
		Iterator<Integer> it1 = docList1.iterator();
		Iterator<Integer> it2 = docList2.iterator();
		
		List<Integer> result = new LinkedList<Integer>();
		
		int curDoc1 = 0;
		int curDoc2 = 0;
		
		while(it1.hasNext()){
			curDoc1 = it1.next();
			if(curDoc1 != curDoc2) result.add(curDoc1);
			
			while(it2.hasNext() && curDoc2 < curDoc1){
				curDoc2 = it2.next();
				if(curDoc1 != curDoc2) result.add(curDoc2);
			}
			
		}
		
		// this is needed just for OR operator, we need to finish iterating both lists
		// to get the full list of documents
		while(it2.hasNext()){
			curDoc2 = it2.next();
			if(curDoc1 != curDoc2) result.add(curDoc2);
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	private static List<Integer> operatorNOT(List<Integer> docList, List<Integer> excludedDocList){
		Iterator<Integer> it1 = docList.iterator();
		Iterator<Integer> it2 = excludedDocList.iterator();
		
		List<Integer> result = new LinkedList<Integer>();
		result.addAll(docList);
		
		int curDoc1 = 0;
		int curDoc2 = 0;
		
		while(it1.hasNext()){
			curDoc1 = it1.next();
			
			while(it2.hasNext() && curDoc2 < curDoc1){
				curDoc2 = it2.next();
			}
			
			if(curDoc1 == curDoc2) result.remove(Integer.valueOf(curDoc1));
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	private static boolean isOperator(String input){
		return (isAND(input) || isOR(input) || isNOT(input));
	}
	
	private static boolean isAND(String input){
		return (input != null && input.equals("AND"));
	}
	
	private static boolean isOR(String input){
		return (input != null && input.equals("OR"));
	}

	private static boolean isNOT(String input){
		return (input != null && input.equals("NOT"));
	}
	
	//TODO if null is met in the original stack, return null at once to show the input was incorrect/nothing was found
}
