/*
 * Problem Statement: Find the minimum length of a subarray containing at least one pair of equal card values.
 * Intuition: For each card value, only its most recent index matters for the shortest duplicate window ending now.
 * Approach: Track last seen index of each card in a HashMap; update minimum distance whenever a duplicate is found.
 * Time Complexity: O(n), one pass through cards.
 * Space Complexity: O(n) in the worst case for distinct card values.
 * Edge Cases handled: No duplicates (return -1); duplicate appears immediately; multiple duplicates of same value.
 * Dry Run: [3,4,2,3,4,7] -> duplicate 3 gives length 4, duplicate 4 gives length 4 -> result 4.
 * LeetCode matching/assumption: Matches LeetCode 2260 (Minimum Consecutive Cards to Pick Up).
 * Correctness Check: Since map stores latest index, each computed window is the shortest duplicate window ending at current index.
 */

import java.util.HashMap;

public class MinimumConsecutiveCardstoPickUp2260 {
    public static int minimumCardPickup(int[] cards) {
        int min = Integer.MAX_VALUE, n = cards.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        // Scan cards left-to-right while tracking latest position of each value.
        for (int i = 0; i < n; i++) {
            // Duplicate found: compute consecutive pickup length from previous same card to current.
            if (map.containsKey(cards[i])) {
                min = Math.min(i - map.get(cards[i]) + 1, min);
            }
            // Always refresh last seen index for this card value.
            map.put(cards[i], i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        int[] cards = { 3, 4, 2, 3, 4, 7 };
        System.out.println(minimumCardPickup(cards));
    }
}
