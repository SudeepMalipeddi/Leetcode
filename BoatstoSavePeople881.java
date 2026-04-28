/*
 * Problem: LeetCode 881 - Boats to Save People
 * Problem Statement: Given people weights and a boat limit, return the minimum
 *   number of boats where each boat carries at most two people with total weight
 *   not exceeding the limit.
 * Intuition: Pair the lightest with the heaviest when possible to maximize usage.
 * Approach:
 *   1) Sort weights.
 *   2) Use two pointers: i (lightest), j (heaviest).
 *   3) If people[i] + people[j] <= limit, pair them and increment i.
 *   4) Always decrement j and count one boat.
 * Time Complexity: O(n log n) for sorting; O(n) scan.
 * Space Complexity: O(1) extra (ignoring sort implementation).
 * Edge Cases: Single person, all heavy persons, exact limit pairs.
 * Dry Run: people=[3,2,2,1], limit=3 -> sort [1,2,2,3];
 *   pair 1+3 (no) => boat for 3, then 1+2 (yes), then 2 alone => 3 boats.
 * Correctness Check: The greedy pairing of heaviest with lightest is optimal
 *   because any heavier partner cannot fit if the lightest can't.
 */
import java.util.*;

public class BoatstoSavePeople881 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); // order weights for two-pointer pairing
        int i = 0;
        int j = people.length - 1;
        int boats = 0;
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++; // pair lightest with heaviest when possible
            }
            j--; // heaviest always boards a boat
            boats++; // count one boat per iteration
        }
        return boats;
    }
}
