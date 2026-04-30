/*
 * Problem: 867. Transpose Matrix
 *
 * Problem Statement:
 * Given a 2D integer array matrix, return the transpose of matrix.
 * The transpose of a matrix is the matrix flipped over its main diagonal, 
 * switching the matrix's row and column indices.
 *
 * Intuition:
 * Transposing a matrix means that the element at row 'i' and column 'j' 
 * moves to row 'j' and column 'i'. If the original matrix is M x N, 
 * the transposed matrix will be N x M.
 *
 * Approach:
 * 1. Determine the number of rows and columns in the input matrix.
 * 2. Initialize a new 2D array with swapped dimensions (cols x rows).
 * 3. Use nested loops to iterate through every element of the input.
 * 4. Map matrix[i][j] to result[j][i].
 *
 * Time Complexity: O(R * C) where R is the number of rows and C is the number of columns, 
 * as we must visit every element in the matrix exactly once.
 * Space Complexity: O(R * C) to store the transposed matrix.
 *
 * Edge Cases:
 * - Rectangular matrices (where rows != cols).
 * - Single row or single column matrices.
 * - Square matrices.
 *
 * Dry Run:
 * Input: matrix = [[1, 2], [3, 4], [5, 6]] (3x2)
 * rows = 3, cols = 2. result = new int[2][3].
 * i=0: j=0 -> result[0][0]=1; j=1 -> result[1][0]=2
 * i=1: j=0 -> result[0][1]=3; j=1 -> result[1][1]=4
 * i=2: j=0 -> result[0][2]=5; j=1 -> result[1][2]=6
 * Output: [[1, 3, 5], [2, 4, 6]]
 *
 * Correctness Check:
 * The solution correctly handles both square and rectangular matrices by 
 * explicitly creating a new result array with swapped dimensions.
 */

// Given a 2D integer array matrix, return the transpose of matrix.
// The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

public class TransposeMatrix867 {
    public int[][] transposeMatrix(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Create a new matrix where the number of rows is the original's column count
        // and the number of columns is the original's row count.
        int[][] result = new int[cols][rows];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                // The core logic of transposition: 
                // The element at row i, column j moves to row j, column i.
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
