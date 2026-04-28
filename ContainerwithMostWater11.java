/*
 * Problem: LeetCode 11 - Container With Most Water
 * Problem Statement: Given n lines, find two lines that together with the x-axis
 *   form a container holding the most water.
 * Intuition: The area is limited by the shorter line; moving the taller line
 *   cannot increase area while width shrinks, so move the shorter line.
 * Approach:
 *   1) Initialize two pointers at both ends.
 *   2) Compute area and update maximum.
 *   3) Move the pointer at the shorter height inward.
 * Time Complexity: O(n).
 * Space Complexity: O(1).
 * Edge Cases: Two elements, all equal heights, strictly increasing/decreasing.
 * Dry Run: height=[1,8,6,2,5,4,8,3,7] -> max area is 49 between 8 and 7.
 * Correctness Check: The two-pointer strategy never discards a potentially
 *   optimal pair because the shorter line limits area.
 */
public class ContainerwithMostWater11 {
    public int maxArea(int[] height){
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left < right){
            int w = right - left; // width between lines
            int h = Math.min(height[left], height[right]); // height limited by shorter line
            int area = h * w;
            max = Math.max(max, area); // track best area so far
            if(height[left] < height[right]){
                left++; // move shorter line inward
            }
            else if (height[left] > height[right]){
                right--; // move shorter line inward
            }
            else{
                left++; // equal heights: move both to explore new widths
                right--;
            }
        }
        return max;
    }
}
