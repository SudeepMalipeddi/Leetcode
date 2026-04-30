/*
 * Problem: 1275. Find Winner on a Tic Tac Toe Game
 *
 * Problem Statement:
 * Tic-tac-toe is played by two players A and B on a 3x3 grid. Given an array of moves, 
 * determine if the game has ended in a win for A, a win for B, a draw, or if it is still pending.
 *
 * Intuition:
 * The game state can be represented by a 2D grid. By assigning unique values to each player 
 * (e.g., 1 for A and -1 for B), we can easily identify winning patterns. A player wins if 
 * they occupy all three cells in any row, column, or either of the two diagonals.
 *
 * Approach:
 * 1. Initialize a 3x3 integer array to represent the board.
 * 2. Iterate through the moves: assign 1 to the grid for player A (even indices) and -1 for player B (odd indices).
 * 3. Iterate through all 3 rows and 3 columns to check if any player has three in a row/column.
 * 4. Check the two diagonals (main diagonal and anti-diagonal).
 * 5. If no winner is found, check the total number of moves: if it's less than 9, the game is "Pending"; 
 *    otherwise, it's a "Draw".
 *
 * Time Complexity: O(1) 
 * The board size is fixed at 3x3 and the maximum number of moves is 9. All loops run for a constant number of iterations.
 *
 * Space Complexity: O(1)
 * We use a fixed-size 3x3 array regardless of the input size.
 *
 * Edge Cases:
 * - Minimum moves for a win (5 moves).
 * - Game ending in a draw on the last move.
 * - Game still pending with empty spaces remaining.
 *
 * Dry Run:
 * moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * 1. Board after moves:
 *    Row 0: [ 1,  1, -1]
 *    Row 1: [ 1, -1,  0]
 *    Row 2: [-1,  0,  0]
 * 2. Check rows/cols: No matches.
 * 3. Check main diagonal: [1, -1, 0] -> No match.
 * 4. Check anti-diagonal: [-1, -1, -1] -> Match! Value is -1, return "B".
 *
 * Correctness Check:
 * The solution correctly identifies winners by checking equality across lines. It avoids 
 * false positives on empty cells (0) by explicitly checking if the value is 1 or -1.
 */
public class Tic_Tac_Toe_Game {
    class Solution {
        public String tictactoe(int[][] moves) {
            int i,arr[][]=new int[3][3];
            
            // Populate the 3x3 grid based on the sequence of moves.
            // Player A moves on even indices (0, 2, 4...), Player B on odd indices (1, 3, 5...).
            for(i=0;i<moves.length;i++)
                if(i%2==0)
                    arr[moves[i][0]][moves[i][1]]=1; // Player A represented by 1
                else
                    arr[moves[i][0]][moves[i][1]]=-1; // Player B represented by -1

            // Iterate through each index to check horizontal (rows) and vertical (columns) lines.
            for(i=0;i<3;i++) {
                // Check Row 'i': if all three elements are equal and non-zero.
                if(arr[i][0]==arr[i][1]&&arr[i][1]==arr[i][2])
                    if(arr[i][0]==1)
                        return "A";
                    else if(arr[i][0]==-1)
                        return "B";
                
                // Check Column 'i': if all three elements are equal and non-zero.
                if(arr[0][i]==arr[1][i]&&arr[1][i]==arr[2][i])
                    if(arr[0][i]==1)
                        return "A";
                    else if(arr[0][i]==-1)
                        return "B";
            }
            
            // Check the main diagonal (top-left to bottom-right).
            if(arr[0][0]==arr[1][1]&&arr[1][1]==arr[2][2])
                if(arr[0][0]==1)
                    return "A";
                else if(arr[0][0]==-1)
                    return "B";
            
            // Check the anti-diagonal (bottom-left to top-right).
            if(arr[2][0]==arr[1][1]&&arr[1][1]==arr[0][2])
                if(arr[2][0]==1)
                    return "A";
                else if(arr[2][0]==-1)
                    return "B";
            
            // If no winner is found and the board is not full (less than 9 moves), the game continues.
            if(moves.length<9)
                return "Pending";
            
            // If the board is full and no winner was detected, it is a draw.
            return "Draw";
        }
    }
}
