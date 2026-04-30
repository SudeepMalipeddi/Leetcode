/*
 * Problem: 128. Longest Consecutive Sequence
 *
 * Problem Statement:
 * Given an unsorted integer array, return the length of the longest sequence of consecutive integers.
 * The algorithm must run in O(n) time complexity.
 *
 * Intuition:
 * To achieve O(n) time, we must avoid sorting (O(n log n)). By using a HashSet, we can check for the 
 * existence of neighbors in O(1) time. The key insight is to only begin building a sequence when 
 * we encounter the "start" of that sequence (i.e., the number x where x-1 is not in the set).
 *
 * Approach:
 * 1. Populate a HashSet with all numbers from the array to allow O(1) lookups and remove duplicates.
 * 2. Iterate through each unique number in the set.
 * 3. For each number 'x', check if 'x-1' exists in the set.
 * 4. If 'x-1' does not exist, 'x' is the start of a potential sequence.
 * 5. From 'x', keep checking for 'x+1', 'x+2', etc., until the sequence breaks, counting the length.
 * 6. Update the global maximum length found.
 *
 * Time Complexity: O(n) average. Although there is a nested while loop, each element is visited 
 * at most twice: once in the outer loop and once in the inner while loop across the entire 
 * execution of the program. HashSet operations are O(1) on average.
 * Space Complexity: O(n) to store the unique elements of the array in the HashSet.
 *
 * Edge Cases:
 * - Empty array: The loop won't execute, returning the initial 'longest' value of 0.
 * - Single element: The loop runs once, 'x-1' is not found, while loop doesn't run, returns 1.
 * - Duplicates: HashSet naturally handles duplicates, ensuring they don't inflate the count.
 *
 * Dry Run:
 * arr = [100, 4, 200, 1, 3, 2]
 * set = {100, 4, 200, 1, 3, 2}
 * 1. x = 100: 99 not in set? Yes. 101 in set? No. currentLength = 1. longest = 1.
 * 2. x = 4: 3 in set? Yes. Skip (4 is not the start of this sequence).
 * 3. x = 200: 199 not in set? Yes. 201 in set? No. currentLength = 1. longest = 1.
 * 4. x = 1: 0 not in set? Yes. 2, 3, 4 are in set. currentLength = 4. longest = 4.
 * 5. x = 3: 2 in set? Yes. Skip.
 * 6. x = 2: 1 in set? Yes. Skip.
 * Result: 4
 *
 * Correctness Check:
 * The solution is correct. The logic correctly identifies the start of sequences to prevent 
 * O(n^2) behavior and correctly handles the length calculation.
 */

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
        // We use a HashSet to achieve O(1) average time complexity for lookups, 
        // which is essential for meeting the O(n) requirement.
        HashSet<Integer> set = new HashSet<>();
        // Build constant-time membership structure from all input numbers.
        for(int i: arr){
            set.add(i);
        }
        int longest = 0;
        // Iterate unique values; duplicates were removed by set insertion.
        for(int x: set){
            // Only start growing when x is the first value of its consecutive block.
            // If x-1 exists, then x is part of a sequence but not the start, 
            // so we skip it to ensure each sequence is only processed once.
            if(!set.contains(x-1)){
                int current = x;
                int currentLength = 1;
                // Extend the current run while the next integer exists in the set.
                // This inner loop only runs for the start of a sequence.
                while(set.contains(current+1)){
                    current++;
                    currentLength++;
                }
                // Update the global maximum with the length of the sequence just found.
                longest = Math.max(longest, currentLength);
            }
        }
        return longest;
    }
}
