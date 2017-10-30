package basic.TRIE;

//http://www.geeksforgeeks.org/trie-insert-and-search/
//Map 대신 배열로 관리하는 경우 
public class Trie {
	static final int ALPHABET_SIZE = 26;

	// 트라이 노드
	static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		// 문자열의 마지막끝인지 판별
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++) {
				children[i] = null;
			}
		}
	};

	static TrieNode root;

	//
	static void insert(String key) {
		int level;
		int length = key.length();
		int index;

		TrieNode pCrawl = root;
		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null) { // 비어있다면 새로 만듦
				pCrawl.children[index] = new TrieNode();
			}
			pCrawl = pCrawl.children[index];
		}
		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	static boolean search(String key) {
		int level;
		int index;
		TrieNode pCrawl = root;
		for (level = 0; level < key.length(); level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null) { // 트라이 트리에 존재하지 않음
				return false;
			}
			pCrawl = pCrawl.children[index];
		}
		return (pCrawl != null && pCrawl.isEndOfWord);
	}

	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		String output[] = { "Not present in trie", "Present in trie" };

		root = new TrieNode();

		// Construct trie
		int i;
		for (i = 0; i < keys.length; i++)
			insert(keys[i]);

		// Search for different keys
		if (search("the") == true)
			System.out.println("the --- " + output[1]);
		else
			System.out.println("the --- " + output[0]);

		if (search("these") == true)
			System.out.println("these --- " + output[1]);
		else
			System.out.println("these --- " + output[0]);

		if (search("their") == true)
			System.out.println("their --- " + output[1]);
		else
			System.out.println("their --- " + output[0]);

		if (search("thaw") == true)
			System.out.println("thaw --- " + output[1]);
		else
			System.out.println("thaw --- " + output[0]);

	}

}
