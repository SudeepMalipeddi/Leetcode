/*
 * Problem: 881. Boats to Save People
 *
 * Problem Statement:
 * You are given an array of people's weights and a weight limit for a boat. Each boat 
 * can carry at most two people at a time, provided their combined weight is within 
 * the limit. Find the minimum number of boats required to carry everyone.
 *
 * Intuition:
 * This is a greedy problem. To minimize the total number of boats, we want to maximize 
 * the number of boats that carry two people. The most efficient way to do this is 
 * to try and pair the heaviest person currently available with the lightest person 
 * currently available. If they can't fit together, the heavy person must take a 
 * boat alone, as pairing them with anyone else would also exceed the limit.
 *
 * Approach:
 * 1. Sort the people by weight so we can easily identify the lightest and heaviest.
 * 2. Use two pointers: 'i' starting at the lightest person and 'j' at the heaviest.
 * 3. In each step, check if the lightest (people[i]) and heaviest (people[j]) can 
 *    share a boat.
 * 4. If they can, move the 'i' pointer forward (lightest person is saved).
 * 5. Regardless of whether they shared, the heaviest person (people[j]) is saved 
 *    in this boat, so move the 'j' pointer backward.
 * 6. Increment the boat count for every iteration.
 *
 * Time Complexity: O(n log n) due to the sorting step. The two-pointer traversal 
 * takes O(n) time.
 * Space Complexity: O(log n) or O(n) depending on the internal implementation of 
 * Arrays.sort() in Java (Dual-Pivot Quicksort uses O(log n) stack space).
 *
 * Edge Cases:
 * - Only one person: Loop runs once, returns 1 boat.
 * - All people are heavier than half the limit: Every person gets their own boat.
 * - Two people exactly equal the limit: They are paired correctly.
 *
 * Dry Run:
 * people = [3, 2, 2, 1], limit = 3
 * 1. Sort: [1, 2, 2, 3]
 * 2. i=0 (1), j=3 (3): 1+3=4 > 3. Boat 1 carries [3]. j becomes 2.
 * 3. i=0 (1), j=2 (2): 1+2=3 <= 3. Boat 2 carries [1, 2]. i becomes 1, j becomes 1.
 * 4. i=1 (2), j=1 (2): i <= j is true. Boat 3 carries [2]. j becomes 0.
 * 5. i=1, j=0: Loop ends. Result = 3.
 *
 * Correctness Check:
 * The solution is correct. The greedy choice of pairing the heaviest with the 
 * lightest is optimal because if the lightest person cannot fit with the 
 * heaviest, no one else can fit with that heavy person either.
 */
import java.util.*;

public class BoatstoSavePeople881 {
    public int numRescueBoats(int[] people, int limit) {
        // Sorting allows us to use a two-pointer greedy approach to pair 
        // the lightest and heaviest individuals.
        Arrays.sort(people); 
        
        int i = 0; // Pointer to the lightest person
        int j = people.length - 1; // Pointer to the heaviest person
        int boats = 0;
        
        // Continue until every person has been assigned to a boat
        while (i <= j) {
            // If the lightest and heaviest can fit together, increment i 
            // to "load" the lightest person into the current boat.
            if (people[i] + people[j] <= limit) {
                i++; 
            }
            
            // The heaviest person (at index j) will always be processed in 
            // this iteration. If they couldn't fit with the lightest person, 
            // they must take the boat alone.
            j--; 
            
            // Every iteration of the loop represents one boat being dispatched.
            boats++; 
        }
        
        return boats;
    }
}
