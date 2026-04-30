/*
 * Problem Statement:
 * Given an unsorted integer array, return the length of the longest sequence of consecutive integers.
 * Sequence elements need not be adjacent in the array.
 *
 * Intuition:
 * Use a set for O(1)-average membership tests. Only start counting at sequence starts (x-1 absent),
 * so each sequence is expanded once.
 *
 * Approach:
 * - Insert all numbers into HashSet.
 * - For each value x in set:
 *   - If x-1 exists, skip (not a start).
 *   - Else grow forward x, x+1, x+2... while present, track length.
 * - Keep global maximum.
 *
 * Time Complexity (with concrete justification):
 * O(n) average: each number is inserted once and visited in expansion at most once across all starts.
 * (HashSet operations are average O(1)).
 *
 * Space Complexity (with concrete justification):
 * O(n) for storing unique elements in HashSet.
 *
 * Edge Cases handled:
 * - Empty array -> longest remains 0.
 * - Duplicates collapse in set and do not inflate sequence length.
 * - Negative numbers handled naturally.
 *
 * Dry Run (concrete example with state):
 * arr=[100,4,200,1,3,2]
 * set={100,4,200,1,3,2}
 * x=1 is start (0 absent): expand 1->2->3->4, currentLength=4, longest=4
 * x=2,3,4 skipped (have predecessors)
 * x=100 start length=1, x=200 start length=1
 * return 4
 *
 * LeetCode matching/assumption:
 * Matches LeetCode 128. Assumes standard integer arithmetic within problem bounds.
 *
 * Correctness Check:
 * Start-detection (`!set.contains(x-1)`) is the key invariant preventing repeated work and double-counting.
 * Comment "O(n^2)" in older template was inaccurate for this implementation; this code is average O(n).
 */

import java.util.HashSet;

class LongestConsecutiveSequence128{
    // HashSet-based linear-time pattern: expand only from valid sequence starts.
    public int longestConsecutive(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        // Build constant-time membership structure from all input numbers.
        for(int i: arr){
            set.add(i);
        }
        int longest = 0;
        // Iterate unique values; duplicates were removed by set insertion.
        for(int x: set){
            // Only start growing when x is the first value of its consecutive block.
            if(!set.contains(x-1)){
                int current = x;
                int currentLength = 1;
                // Extend the current run while the next integer exists.
                while(set.contains(current+1)){
                    current++;
                    currentLength++;
                }
                longest = Math.max(longest, currentLength);
            }
        }
        return longest;
    }
}
