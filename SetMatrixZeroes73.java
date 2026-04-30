/*
 * Problem: 73. Set Matrix Zeroes
 *
 * Problem Statement:
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's. 
 * The modification must be done in-place.
 *
 * Intuition:
 * The challenge is to avoid "cascading" zeros—where a zero we just created causes 
 * other rows/columns to be zeroed out incorrectly. This approach uses a sentinel 
 * value (-99) to mark cells that should eventually become zero without confusing 
 * them with original zeros during the initial scan.
 *
 * Approach:
 * 1. Traverse the matrix cell by cell.
 * 2. When an original 0 is found, iterate through its entire row and column.
 * 3. Mark any non-zero element in that row/column with a placeholder value (-99).
 * 4. Perform a second pass over the matrix to convert all placeholder values (-99) to 0.
 *
 * Time Complexity: O(M * N * (M + N)) where M is rows and N is columns. 
 * For every cell (M*N), we potentially traverse a row (N) and a column (M).
 * Space Complexity: O(1) as we modify the input matrix directly without extra storage.
 *
 * Edge Cases:
 * - Matrix with no zeros: No changes made.
 * - Matrix with all zeros: All elements remain zero.
 * - Matrix containing the sentinel value (-99): This is a logic risk (see Correctness Check).
 *
 * Dry Run:
 * Input: [[1, 0], [1, 1]]
 * 1. Scan (0,0): 1 (no action)
 * 2. Scan (0,1): 0 found. 
 *    - Mark row 0: matrix[0][0] becomes -99 (matrix[0][1] is 0, skip).
 *    - Mark col 1: matrix[1][1] becomes -99 (matrix[0][1] is 0, skip).
 *    Matrix is now: [[-99, 0], [1, -99]]
 * 3. Final pass: Replace -99 with 0.
 * Output: [[0, 0], [0, 0]]
 *
 * Correctness Check:
 * The solution is logically functional for basic cases but has a significant bug: 
 * if the input matrix contains the integer -99 as a valid data point, it will 
 * be incorrectly changed to 0. A more robust O(M*N) approach would use the 
 * first row and first column as storage markers.
 */
public class SetMatrixZeroes73 {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // First pass: Identify original zeros and mark their impact zones
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // marking the first element of the row and column as 0
                    // if the element is 0
                    
                    // Mark all non-zero elements in the current row with a sentinel
                    for (int k = 0; k < cols; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = -99;
                        }
                    }
                    // Mark all non-zero elements in the current column with a sentinel
                    for (int k = 0; k < rows; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = -99;
                        }
                    }
                }
            }
        }
        
        // Second pass: Finalize the transformation
        // replacing all the -99 with 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Only convert the marked sentinels to 0 to preserve original 0s
                if (matrix[i][j] == -99) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
