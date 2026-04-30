/*
 * Problem: 2260. Minimum Consecutive Cards to Pick Up
 *
 * Problem Statement:
 * Given an integer array 'cards', find the minimum number of consecutive cards you must pick up
 * to have at least one pair of matching cards in your hand. If it is impossible to find a pair, return -1.
 *
 * Intuition:
 * The shortest distance between two identical cards will always be between two adjacent occurrences 
 * of that value. By iterating through the array and keeping track of the last seen index of every 
 * card, we can calculate the distance whenever we encounter a duplicate and keep the minimum.
 *
 * Approach:
 * 1. Initialize 'min' to Integer.MAX_VALUE to track the smallest window found.
 * 2. Use a HashMap to store the most recent index of each card value encountered.
 * 3. Iterate through the array:
 *    a. If the current card exists in the map, calculate the distance (current_index - last_index + 1).
 *    b. Update 'min' if this distance is smaller than the current 'min'.
 *    c. Update the map with the current index for the card value (this ensures we always compare with the closest previous occurrence).
 * 4. Return 'min' if it was updated, otherwise return -1.
 *
 * Time Complexity: O(n) where n is the number of cards, as we perform a single pass and HashMap operations are O(1) on average.
 * Space Complexity: O(n) in the worst case to store the last seen index of every unique card in the map.
 *
 * Edge Cases:
 * - No duplicates: The 'min' variable will never be updated, returning -1.
 * - All cards are the same: The window will be 2 (e.g., [7, 7]).
 * - Multiple pairs of different values: The logic correctly finds the minimum across all pairs.
 *
 * Dry Run:
 * cards = [3, 4, 2, 3, 4]
 * i=0: map={3:0}
 * i=1: map={3:0, 4:1}
 * i=2: map={3:0, 4:1, 2:2}
 * i=3: cards[3]=3 is in map. dist = 3 - 0 + 1 = 4. min = 4. map={3:3, 4:1, 2:2}
 * i=4: cards[4]=4 is in map. dist = 4 - 1 + 1 = 4. min = 4. map={3:3, 4:4, 2:2}
 * Result: 4
 *
 * Correctness Check:
 * The solution is correct. Updating the map with the current index 'i' even when a duplicate is 
 * found is crucial because it ensures that for any future occurrence of that card, we are 
 * measuring the smallest possible gap between adjacent duplicates.
 */

import java.util.HashMap;

public class MinimumConsecutiveCardstoPickUp2260 {
    public static int minimumCardPickup(int[] cards) {
        // Initialize min with a sentinel value to track the smallest window found.
        int min = Integer.MAX_VALUE, n = cards.length;
        // Map to store <CardValue, LastSeenIndex> to calculate distances.
        HashMap<Integer, Integer> map = new HashMap<>();

        // Scan cards left-to-right while tracking latest position of each value.
        for (int i = 0; i < n; i++) {
            // Duplicate found: compute consecutive pickup length from previous same card to current.
            if (map.containsKey(cards[i])) {
                // The number of cards picked up is the difference in indices plus one (inclusive).
                min = Math.min(i - map.get(cards[i]) + 1, min);
            }
            // Always refresh last seen index for this card value to ensure we find the shortest gap.
            map.put(cards[i], i);
        }
        // If min remains MAX_VALUE, no pairs were found in the entire array.
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        int[] cards = { 3, 4, 2, 3, 4, 7 };
        System.out.println(minimumCardPickup(cards));
    }
}
