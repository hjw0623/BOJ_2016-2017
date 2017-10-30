package basic.TRIE;

import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=AXjmTQ8LEoI
//Thanks to Tushar Roy
public class Trie2 {
	private class TrieNode{
		Map<Character, TrieNode> children;
		boolean endOfWord;
		public TrieNode(){
			children = new HashMap<>();
			endOfWord = false;
		}
	}
	private final TrieNode root;
	
	public Trie2(){
		root = new TrieNode();
	}
	
	/**
	 * Iterative Implementation of insert into trie
	 */
	public void insert(String word){
		TrieNode current = root;
		for(int i=0; i<word.length(); i++){
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node ==null){ //현재 없다면 추가 
				node = new TrieNode();
				current.children.put(ch,  node);
			}
			current = node;	//이하의 문자에 대해 트라 노드 탐색을 위한 노드 갱신  
		}
		//문자열의 마지막 노드의 endOfWord = true
		current.endOfWord = true;
	}
	//recursive way to insert String 
	public void insertRecursive(String word){
		insertRecursive(root, word, 0);
	}
	public void insertRecursive(TrieNode current, String word, int index){
		if(index==word.length()){ //문자열의 마지막에 도착하였다면 트라이노드 eOW = tru 처리 
			current.endOfWord = true; 
			return;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		
		//if node doesn't exists in map then create one 
		if(node ==null){
			node = new TrieNode();
			current.children.put(ch, node);
		}
		//recursive
		insertRecursive(node, word, index+1);
	}
	
	public boolean search(String word){
		TrieNode current = root;
		
		for(int i=0; i<word.length(); i++){
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node ==null){
				return false;
			}
			current = node;
		}
		//current의 endOfWord가 true면 true반환, 아니면 false반환
		//즉, false인 경우 ex ) abc를 찾을떄 abcd만 Trie에 있는 경우. 
		return current.endOfWord;
	}
	
	public boolean searchRecursive(String word){
		return searchRecursive(root, word, 0);
	}
	public boolean searchRecursive(TrieNode current, String word, int index){
		if(index==word.length()){
			return current.endOfWord;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if(node ==null){
			return false;
			
		}
		return searchRecursive(node, word, index+1);
	}
	
	public void delete(String word){
		 delete(root, word, 0);
	}
	
	public boolean delete(TrieNode current, String word, int index){
		if(index ==word.length()){
			//단어의 끝에 도달했을 때,
			//current TrieNode의 endOfWord가 true인 경우에만 삭제가 가능하다.
			//만약 false인 상황이면 해당 단어가 트리에 존재하지 않으므로 삭제 불가. 
			if(!current.endOfWord){
				return false;
			}
			//otherwise 
			current.endOfWord=false;
			//만약 current노드가 맵에 아무것도 없다면 return true
			return current.children.size()==0;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if(node ==null){ 	//맵에 존재하지 않는 다면 삭제 불가 
			return false ; 
		}
		//재귀적으로 반복하여 문자열 끝까지 탐색 
		boolean shouldDeleteCurrent = delete(node, word, index+1);
		// true라면 character, trienode 매핑을 맵에서 지운다. 
		if(shouldDeleteCurrent){
			current.children.remove(ch);
			return current.children.size() ==0; //size 0인 경우 계속 true를 리턴하여 이전index단계의 맵에서 맵핑요소를 지울 수 있다. 
		}
		return false;
	}
}
