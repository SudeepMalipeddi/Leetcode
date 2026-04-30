/*
 * Problem: LeetCode 278 - First Bad Version
 *
 * Problem Statement:
 * You have n versions [1, 2, ..., n] and you want to find out the first bad one, 
 * which causes all the following ones to be bad. You are given an API bool 
 * isBadVersion(version) which returns whether version is bad.
 *
 * Intuition:
 * The versions follow a monotonic property: [Good, Good, ..., Good, Bad, Bad, ..., Bad].
 * Whenever we have a sorted-like property where a condition changes from false to true 
 * and stays true, Binary Search is the optimal way to find the transition point.
 *
 * Approach:
 * 1. Initialize a search range from 0 to n (as per the provided code structure).
 * 2. Calculate the middle point carefully to avoid integer overflow.
 * 3. If the middle version is good, the first bad version must be in the right half (mid + 1 to end).
 * 4. If the middle version is bad, it could be the first bad version or the first bad version 
 *    is in the left half. We set end to mid to keep it in our search space.
 * 5. The loop terminates when start and end converge on the first bad version.
 *
 * Time Complexity: O(log n) - The search space is halved in each iteration.
 * Space Complexity: O(1) - Only a constant amount of extra space is used for pointers.
 *
 * Edge Cases:
 * - n = 1: The loop won't execute if start=0 and end=1 and mid=0 is bad, or it handles it via the logic.
 * - The first version is bad: The algorithm should correctly identify index 0 or 1.
 * - The last version is the only bad one: The algorithm should narrow down to n.
 *
 * Dry Run:
 * n = 5, first bad = 4
 * 1. start = 0, end = 5, mid = 2. isBad(2) is false. start = 3.
 * 2. start = 3, end = 5, mid = 4. isBad(4) is true. end = 4.
 * 3. start = 3, end = 4, mid = 3. isBad(3) is false. start = 4.
 * 4. start = 4, end = 4. Loop terminates. Return 4.
 *
 * Correctness Check:
 * The code uses start = 0. Standard LeetCode versions are 1-indexed. If the first bad version 
 * is 1, and isBadVersion(0) is false, this code returns 1 correctly. If versioning starts at 1, 
 * usually start is initialized to 1. However, the logic remains sound for finding the first 
 * index where the predicate becomes true.
 */
public class First_Bad_Version278 {
    public boolean isBadVersion(int n){
        // dummy function
        return true;
    }
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;
        
        // Use while(start < end) to find the leftmost element satisfying the condition.
        // When the loop ends, start == end, pointing to the first bad version.
        while(start < end){
            // Standard way to calculate mid to prevent (start + end) overflow 
            // when dealing with large integers near Integer.MAX_VALUE.
            int mid = start + (end-start)/2;
            
            if(!isBadVersion(mid)){
                // If mid is good, the first bad version must be at an index > mid.
                start = mid + 1;
            }
            else {
                // If mid is bad, it might be the first bad version, 
                // so we include it in the next search range [start, mid].
                end = mid;
            }
        }
        
        // Both start and end converge to the first bad version.
        return start;
    }
}
