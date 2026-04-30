/*
 * Problem: 118. Pascal's Triangle
 *
 * Problem Statement:
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Intuition:
 * Instead of creating a brand new list for every row, we can modify a single list
 * by prepending a '1' and then updating the subsequent elements. By adding a '1'
 * at the start, the previous row's elements shift right, allowing us to compute
 * the new row's values by summing adjacent elements in the shifted list.
 *
 * Approach:
 * 1. Initialize a list of lists 'allrows' to store the final triangle.
 * 2. Use a single 'row' list to build each row iteratively.
 * 3. For each row from 0 to numRows-1:
 *    a. Prepend 1 to the 'row' list. This shifts existing elements to the right.
 *    b. Iterate through the interior elements (index 1 to size-2).
 *    c. Update each interior element: row[j] = row[j] + row[j+1].
 *    d. Add a copy of the current 'row' to 'allrows'.
 *
 * Time Complexity: O(numRows^2)
 * Each row 'i' involves an O(i) insertion at the front and an O(i) traversal to update values.
 * Summing 1 to N gives O(N^2).
 *
 * Space Complexity: O(numRows^2)
 * This is the space required to store the output. The auxiliary space for the 'row' list is O(numRows).
 *
 * Edge Cases:
 * - numRows = 0: Returns an empty list.
 * - numRows = 1: Returns [[1]].
 *
 * Dry Run (numRows = 3):
 * i=0: row=[1]. allrows=[[1]]
 * i=1: row.add(0,1) -> [1,1]. Inner loop skipped. allrows=[[1], [1,1]]
 * i=2: row.add(0,1) -> [1,1,1]. j=1: row.set(1, 1+1) -> [1,2,1]. allrows=[[1], [1,1], [1,2,1]]
 *
 * Correctness Check:
 * The solution correctly implements the row-by-row generation. The use of row.add(0, 1)
 * is O(N) per row, which is acceptable within the O(N^2) total complexity.
 */
import java.util.*;

public class Pascals_Triangle {
    public List<List<Integer>> generate(int numRows){
	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
	ArrayList<Integer> row = new ArrayList<Integer>();
	
	for(int i=0;i<numRows;i++)
	{
		// Prepend 1 to the list. In an ArrayList, this is an O(row.size()) operation 
		// as it shifts all existing elements to the right.
		row.add(0, 1);
		
		// Update interior cells using previous row values retained in the same list.
		// After prepending 1, the old values of the previous row are now at indices 1 to size-1.
		// We sum adjacent elements to get the Pascal property: current = above_left + above_right.
		for(int j=1;j<row.size()-1;j++)
			row.set(j, row.get(j)+row.get(j+1));
		
		// We must create a new ArrayList copy because 'row' is a mutable object.
		// Adding 'row' directly would result in 'allrows' containing multiple references to the same final list.
		allrows.add(new ArrayList<Integer>(row));
	}
	return allrows;
    }
}
