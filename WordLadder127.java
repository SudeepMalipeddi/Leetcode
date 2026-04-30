/*
 * Problem: 127. Word Ladder
 *
 * Problem Statement:
 * Given two words, beginWord and endWord, and a dictionary wordList, return the length of the shortest 
 * transformation sequence from beginWord to endWord. Each transformation must change exactly one 
 * character, and every intermediate word must exist in the wordList.
 *
 * Intuition:
 * This problem can be modeled as an unweighted graph where each word is a node and an edge exists 
 * between two words if they differ by exactly one character. In an unweighted graph, the shortest 
 * path between two nodes is guaranteed to be found using Breadth-First Search (BFS).
 *
 * Approach:
 * 1. Store all words from wordList in a HashSet for O(1) lookup and to track unvisited nodes.
 * 2. Initialize a Queue for BFS, storing pairs of (currentWord, currentLevel).
 * 3. While the queue is not empty, poll a word and check if it matches endWord.
 * 4. For the current word, generate all possible one-letter mutations by iterating through 
 *    each character position and replacing it with 'a' through 'z'.
 * 5. If a mutation exists in the set, it's a valid neighbor. Add it to the queue with level + 1 
 *    and remove it from the set to mark it as visited.
 * 6. If the queue becomes empty without finding endWord, return 0.
 *
 * Time Complexity: O(N * L * 26) 
 * Where N is the number of words in the wordList and L is the length of each word. 
 * For each word, we iterate through L positions and 26 possible characters.
 *
 * Space Complexity: O(N * L)
 * To store the HashSet of words and the BFS queue.
 *
 * Edge Cases:
 * - endWord is not in wordList: The BFS will exhaust all possibilities and return 0.
 * - beginWord is already the endWord: The code returns 1 (the sequence length).
 *
 * Dry Run:
 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 1. Queue: [("hit", 1)], Set: {hot, dot, dog, lot, log, cog}
 * 2. Pop ("hit", 1). Neighbors: "ait"..."zit", "hat"..."hzt", "hia"..."hiz". 
 *    "hot" is in Set. Queue: [("hot", 2)], Set: {dot, dog, lot, log, cog}
 * 3. Pop ("hot", 2). Neighbors: "dot", "lot" found.
 *    Queue: [("dot", 3), ("lot", 3)], Set: {dog, log, cog}
 * 4. Pop ("dot", 3). Neighbor "dog" found. Queue: [("lot", 3), ("dog", 4)], Set: {log, cog}
 * 5. ... eventually "cog" is reached at level 5.
 *
 * Correctness Check:
 * The solution is correct. It uses BFS to ensure the shortest path is found first. 
 * Removing words from the set upon discovery acts as a "visited" array, preventing cycles.
 */

import java.util.*;

public class WordLadder127 {
    // Helper class to store the word and its corresponding distance from the start
    class Pair {
        String first;
        int second;

        Pair(String first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Standard BFS queue
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        
        // Use a Set for O(1) lookup. Removing words from the set prevents re-processing (visited)
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);

        while (!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.poll().second;
            
            // If we reach the target word, the current step count is the shortest path length
            if (word.equals(endWord)) {
                return steps;
            }

            // Try changing every character of the current word to find neighbors
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replaced[] = word.toCharArray();
                    replaced[i] = ch;
                    String newWord = new String(replaced);

                    // If the mutated word exists in our dictionary (and hasn't been visited)
                    if (set.contains(newWord)) {
                        // Remove from set immediately to mark as visited
                        set.remove(newWord);
                        // Add to queue to explore its neighbors in the next BFS level
                        q.offer(new Pair(newWord, steps + 1));
                    }
                }
            }
        }
        
        // If the queue is empty and we haven't returned, no path exists
        return 0;
    }
}
