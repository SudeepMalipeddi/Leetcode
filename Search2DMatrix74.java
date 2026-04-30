/*
 * Problem: 74. Search a 2D Matrix
 *
 * Problem Statement:
 * Given an m x n integer matrix where each row is sorted in non-decreasing order and the 
 * first integer of each row is greater than the last integer of the previous row, 
 * determine if a target value exists in the matrix.
 *
 * Intuition:
 * The matrix has a special property: it is essentially a single sorted list laid out in 
 * rows. This means we can narrow down our search by identifying which row the target 
 * *should* be in based on the row's start and end values, then searching only that row.
 *
 * Approach:
 * 1. Iterate through each row of the matrix.
 * 2. For each row, identify the first (l) and last (r) elements.
 * 3. If the target is between l and r (inclusive), the target must be in this row if it exists at all.
 * 4. Perform a linear scan of that specific row to find the target.
 * 5. If the target is found, return true; otherwise, return false after checking all rows.
 *
 * Time Complexity: O(m + n), where m is the number of rows and n is the number of columns. 
 * We iterate through m rows, and in the worst case, we scan n elements of one row.
 * Space Complexity: O(1) as we only use a constant amount of extra space for pointers.
 *
 * Edge Cases:
 * - Target is smaller than the first element of the first row.
 * - Target is larger than the last element of the last row.
 * - Matrix has only one row or one column.
 *
 * Dry Run:
 * matrix = [[1, 3, 5], [10, 11, 16]], target = 3
 * 1. Row 0: l=1, r=5. 1 <= 3 <= 5 is true.
 * 2. Scan Row 0: index 0 is 1, index 1 is 3. 3 == 3. Return true.
 *
 * Correctness Check:
 * The solution is correct. However, it is not the most optimal. Since the entire 
 * matrix is strictly sorted, a true binary search treating the 2D matrix as a 1D 
 * array would achieve O(log(m * n)) time complexity.
 */
public class Search2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the dimensions of the matrix
        int row = matrix.length;
        int col = matrix[0].length;

        // Almost Binary search: Narrow down the search to a specific row
        for (int i = 0; i < row; i++) {
            // Identify the boundaries of the current row
            int l = matrix[i][0];
            int r = matrix[i][col - 1];
            
            // Check if the target falls within the sorted range of this row
            if (l <= target && r >= target) {
                // If the target is in range, search this row specifically
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == target)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        // Linear search line by line: A brute force approach
        int row = matrix.length;
        int col = matrix[0].length;
        
        // Iterate through every single cell in the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // If any element matches the target, return true immediately
                if (matrix[i][j] == target)
                    return true;
            }
        }
        // If the loops finish, the target is not present
        return false;
    }
}
