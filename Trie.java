package SearchEngine;
import java.util.*;
// Trie class that stores words from webpages
class TrieNode {
	TrieNode[] arr;
	boolean isEnd;
	public  ArrayList<Integer> OccurenceIndex = new ArrayList<Integer>() ;
	// Initialize your data structure here.
	public TrieNode() {
		this.arr = new TrieNode[26];
	}

}
public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}
	// Inserts a word into the trie.
	public void insert(String word,int Occurenceindex) {
		boolean find=search(word);
		TrieNode p = root;
		if(!find) {

			for(int i=0; i<word.length(); i++){
				char c = word.charAt(i);
				int index = c-'a';
				if(p.arr[index]==null){
					TrieNode temp = new TrieNode();
					p.arr[index]=temp;
					p = temp;
				}
				else if(index>=0){
					p=p.arr[index];
				}
			}
			p.isEnd=true;
			p.OccurenceIndex.add(Occurenceindex);
		}
		else if(find)
		{
			p=searchNode(word);
			if(!p.OccurenceIndex.contains(Occurenceindex)){
				p.OccurenceIndex.add(Occurenceindex);
			}
		}
	} 
	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode p = searchNode(word);
		if(p==null){
			return false;
		}else{
			if(p.isEnd)
				return true;
		}

		return false;
	}

	//Returns the webpages with keywords entered by the user  
	public ArrayList<Integer> ListWebpages (String[] args)
	{
		ArrayList<Integer> occurences = new ArrayList<Integer>();
		String word;
		int len = args.length;
		TrieNode p=null;
		int i=0;
		boolean isFound=false;
		while(i < len)
		{
			word = args[i];
			p = searchNode(word);
			if(p==null){
				System.out.println("String not found");
				return null;
			}else{
				if(p.isEnd){
					isFound=true;
					if(isFound){
						occurences=p.OccurenceIndex;
						isFound=false;
					}
					if(len>1){

						occurences.retainAll(p.OccurenceIndex);
					}
				}  


			}  
			i++;    
		} 

		return occurences;	
	}
	//Returns the string if found else returns null
	public TrieNode searchNode(String s){
		TrieNode p = root;
		for(int i=0; i<s.length(); i++){
			char c= s.charAt(i);
			int index = c-'a';
			if(p.arr[index]!=null){
				p = p.arr[index];
			}else{
				return null;
			}
		}

		if(p==root)
			return null;

		return p;
	}
}

