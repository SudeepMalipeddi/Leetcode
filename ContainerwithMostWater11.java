/*
 * Problem: LeetCode 11 - Container With Most Water
 *
 * Problem Statement:
 * Given an integer array 'height' of length n, find two lines that, together with the x-axis, 
 * form a container such that the container contains the most water. Return the maximum 
 * amount of water a container can store.
 *
 * Intuition:
 * The area of the container is determined by two factors: the width (distance between indices) 
 * and the height (the minimum of the two vertical lines). To maximize area, we start with 
 * the maximum possible width by placing pointers at the extreme ends. As we move pointers 
 * inward, the width decreases. To compensate for this loss in width, we must find a 
 * significantly taller line. Since the shorter line is the "bottleneck" for the height, 
 * moving the taller line inward would never increase the area (width decreases, and height 
 * is still limited by the same shorter line or an even shorter one). Therefore, the only 
 * logical move is to shift the pointer pointing to the shorter line.
 *
 * Approach:
 * 1. Initialize two pointers: 'left' at index 0 and 'right' at the last index.
 * 2. Calculate the area using the current pointers: width * min(height[left], height[right]).
 * 3. Update the global maximum area if the current area is larger.
 * 4. Move the pointer pointing to the shorter line inward.
 * 5. Repeat until the pointers meet.
 *
 * Time Complexity: O(n) because we traverse the array once with two pointers.
 * Space Complexity: O(1) as we only use a few integer variables regardless of input size.
 *
 * Edge Cases:
 * - Array with only 2 elements (minimum size).
 * - Array where all heights are the same.
 * - Array with heights in strictly increasing or decreasing order.
 *
 * Dry Run:
 * height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
 * 1. L=0, R=8: w=8, h=min(1,7)=1, area=8, max=8. height[L] < height[R], so L++.
 * 2. L=1, R=8: w=7, h=min(8,7)=7, area=49, max=49. height[L] > height[R], so R--.
 * 3. L=1, R=7: w=6, h=min(8,3)=3, area=18, max=49. height[L] > height[R], so R--.
 * ... continues until L and R meet. Final max is 49.
 *
 * Correctness Check:
 * The solution is correct. The greedy two-pointer approach effectively prunes the search 
 * space by discarding configurations that are guaranteed to have a smaller area than 
 * the current maximum.
 */
public class ContainerwithMostWater11 {
    public int maxArea(int[] height){
        // Initialize pointers at the widest possible interval
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        // Process until the two pointers meet in the middle
        while(left < right){
            // Calculate the horizontal distance between the two lines
            int w = right - left; 
            
            // The water level is limited by the shorter of the two lines (the bottleneck)
            int h = Math.min(height[left], height[right]); 
            
            // Calculate current volume and update the global maximum if this is the best seen so far
            int area = h * w;
            max = Math.max(max, area); 
            
            // Logic for moving pointers:
            // We move the pointer pointing to the shorter line because keeping it 
            // would never result in a larger area as the width decreases.
            if(height[left] < height[right]){
                left++; // Move left pointer inward to look for a taller line
            }
            else if (height[left] > height[right]){
                right--; // Move right pointer inward to look for a taller line
            }
            else{
                // If heights are equal, moving either one (or both) is fine because 
                // the current height will remain the bottleneck for any inner width 
                // unless both pointers find taller lines.
                left++; 
                right--;
            }
        }
        return max;
    }
}
