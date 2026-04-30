/*
 * Problem: 169. Majority Element
 *
 * Problem Statement:
 * Given an array of size n, find the element that appears more than n/2 times.
 * The problem assumes that a majority element always exists in the array.
 *
 * Intuition:
 * This solution uses the Boyer-Moore Voting Algorithm. The core insight is that if we 
 * pair up different elements and cancel them out, the majority element (which 
 * occupies more than half the array) will be the only one that cannot be fully 
 * canceled out by all other elements combined.
 *
 * Approach:
 * 1. Maintain a 'candidate' variable to store the potential majority element and a 'count' to track its net "votes."
 * 2. Iterate through the array:
 *    a. If count is 0, we no longer have a viable candidate, so we pick the current element as the new candidate.
 *    b. If the current element matches the candidate, increment the count.
 *    c. If it differs, decrement the count (simulating a cancellation).
 * 3. The candidate remaining at the end is the majority element.
 *
 * Time Complexity: O(n) because we perform a single linear pass through the array.
 * Space Complexity: O(1) because we only use two integer variables (count and candidate).
 *
 * Edge Cases:
 * - Single element array: The loop runs once, sets the candidate, and returns it.
 * - Array with all identical elements: The count will simply increment to n.
 *
 * Dry Run:
 * Input: [2, 2, 1, 1, 1, 2, 2]
 * 1. num=2, count=0 -> candidate=2, count=1
 * 2. num=2, count=1 -> count=2 (matches candidate)
 * 3. num=1, count=2 -> count=1 (different, cancel one)
 * 4. num=1, count=1 -> count=0 (different, cancel one)
 * 5. num=1, count=0 -> candidate=1, count=1 (new candidate)
 * 6. num=2, count=1 -> count=0 (different, cancel one)
 * 7. num=2, count=0 -> candidate=2, count=1 (new candidate)
 * Result: 2
 *
 * Correctness Check:
 * The solution is correct. Since the majority element appears > n/2 times, its 
 * total "votes" will always outweigh the combined "votes" of all other elements.
 */

/*
 * Problem Statement: Find the element that appears more than n/2 times in the array.
 * Intuition: Pairwise-cancel different values; the true majority cannot be fully canceled out.
 * Approach: Boyer-Moore voting keeps a candidate and counter, resetting candidate when count reaches zero.
 * Time Complexity: O(n) single pass.
 * Space Complexity: O(1).
 * Edge Cases handled: Single-element arrays and mixed values where majority still exists.
 * Dry Run: [2,2,1,1,1,2,2] -> candidate changes/cancels, final candidate becomes 2.
 * LeetCode matching/assumption: Assumes LeetCode 169 guarantee that a majority element always exists.
 * Correctness Check: Under majority-exists guarantee, final candidate is correct without needing a second validation pass.
 */

public class Majorityelement {
    public int majorityElement(int[] nums){
        // count represents the current "surplus" of the candidate element.
        int count = 0;
        // candidate stores the element we are currently tracking as the potential majority.
        int candidate = 0;
        // Scan each value and adjust vote balance for current candidate.
        for(int num:nums){
            // When balance is zero, choose current number as new candidate.
            // This happens when the previous candidate has been completely "canceled out" 
            // by an equal number of different elements.
            if(count==0){
                candidate = num;
            }
            // Same value strengthens candidate; different value cancels one vote.
            // If the current number matches our candidate, we increase its lead.
            if(num==candidate){
                count++;
            }
            // If the current number is different, it "votes against" the candidate,
            // reducing the count.
            else{
                count--;
            }
        }
        // Because the problem guarantees a majority element exists, the candidate
        // remaining after the final cancellation is guaranteed to be the majority.
        return candidate;
        // This is Github co pilot's code
        

    }
}
