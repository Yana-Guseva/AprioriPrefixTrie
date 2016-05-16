package org.eltech.ddm.associtionrules.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	private TrieNode root;
	class TrieNode {
        Map<String, TrieNode> next = new HashMap<>();
        int count;
        boolean leaf;
    }
	
	public Trie() {
		root = new TrieNode();
	}

    public void put(List<String> key) {
        TrieNode currentNode = root;
//        System.out.println(Thread.currentThread().getName() + " key " + key);
        for (String str : key) {
            if (!currentNode.next.containsKey(str)) {
                currentNode.next.put(str, new TrieNode());
            }
            currentNode = currentNode.next.get(str);
            currentNode.count++;
//            System.out.println("str " + str + " " + currentNode.count);
        }
        currentNode.leaf = true;
    }

    public int get(List<String> key) {
        TrieNode currentNode = root;
        for (String str : key) {
            if (!currentNode.next.containsKey(str)) {
                return 0;
            }
            currentNode = currentNode.next.get(str);
//            System.out.println("get " + str + " " + currentNode.count);
        }
        return currentNode.count;
    }
}
