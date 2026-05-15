/*
 * Problem: 1861. Rotating the Box
 *
 * Problem Statement:
 * You are given an m x n matrix of characters representing a side-view of a box containing
 * stones ('#'), stationary obstacles ('*'), and empty spaces ('.').
 * The box is rotated 90 degrees clockwise, causing stones to fall due to gravity until they
 * hit an obstacle, another stone, or the bottom. Return the n x m matrix after rotation.
 *
 * Intuition:
 * Falling "down" after a 90-degree clockwise rotation is exactly the same as falling
 * "right" in the original, unrotated box! Instead of rotating the box first and trying to
 * simulate gravity downwards (which can be messy with column manipulation), it is much
 * easier to simulate gravity horizontally (pushing stones to the right) in the original
 * box, and THEN rotate the entire box 90 degrees.
 *
 * Approach:
 * 1. Initialize an n x m (col x row) result matrix filled with empty spaces (though we
 *    will completely overwrite it).
 * 2. Simulate Gravity (Row by Row):
 *    - For each row, start checking from the rightmost side (index `col - 1`) to the left.
 *    - Maintain an `empty` pointer representing the right-most available empty slot.
 *    - If we encounter a stone ('#'), we swap it with the `empty` slot and decrement `empty`.
 *    - If we encounter an obstacle ('*'), stones cannot pass it. We update `empty` to be
 *      the cell immediately to the left of the obstacle (`j - 1`).
 * 3. Rotate the Box:
 *    - Traverse the original matrix and map each cell to its new 90-degree rotated position.
 *    - The cell at `box[i][j]` moves to `res[j][row - 1 - i]`.
 *
 * Time Complexity: O(M * N) where M is rows and N is columns. We traverse the grid twice
 * (once for gravity, once for rotation).
 * Space Complexity: O(M * N) to store the rotated result matrix. The gravity simulation
 * itself is done in-place.
 *
 * Edge Cases:
 * - Rows with no obstacles: Stones fall all the way to the right.
 * - Rows full of obstacles: Nothing moves.
 * - Stones already at the far right or directly against an obstacle: The `swap` safely swaps
 *   the stone with itself.
 *
 * Dry Run:
 * Grid: [["#", ".", "*", "."]] (1 row, 4 cols)
 * 1. Initial: empty = 3.
 * 2. j = 3 ('.'): continue. empty is still 3.
 * 3. j = 2 ('*'): obstacle found. empty becomes j - 1 = 1.
 * 4. j = 1 ('.'): continue. empty is still 1.
 * 5. j = 0 ('#'): stone found. Swap box[0][0] with box[0][empty] (box[0][1]).
 *    Row becomes: [".", "#", "*", "."].
 * 6. Rotation: Map original 1x4 array to a 4x1 array.
 *    Result: [[".", "#", "*", "."]] becomes a vertical column.
 *
 * Correctness Check:
 * The two-pointer approach correctly moves stones as far right as possible. The standard
 * 90-degree matrix rotation formula `res[col][n - 1 - row]` correctly positions the elements.
 */

#include <iostream>
#include <vector>
using namespace std;

class Solution
{
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>> &box)
    {
        int row = box.size(), col = box[0].size();

        // The new matrix dimensions will be swapped (col x row)
        vector<vector<char>> res(col, vector<char>(row));

        // Step 1: Simulate gravity pushing stones to the right
        for (int i = 0; i < row; i++)
        {
            // 'empty' tracks the rightmost available position for a stone to fall into
            int empty = col - 1;

            // Traverse the row from right to left
            for (int j = col - 1; j >= 0; j--)
            {
                if (box[i][j] == '#')
                {
                    // Stone found: move it to the 'empty' position
                    // Using swap handles the case where j == empty elegantly
                    swap(box[i][j], box[i][empty]);
                    empty--; // The next available spot moves one step to the left
                }
                else if (box[i][j] == '*')
                {
                    // Obstacle found: stones cannot fall past this
                    // Next available spot is immediately to the left of the obstacle
                    empty = j - 1;
                }
            }
        }

        // Step 2: Rotate the box 90 degrees clockwise
        // Iterate backwards through the original rows to map to columns
        for (int i = row - 1; i >= 0; i--)
        {
            for (int j = 0; j < col; j++)
            {
                // Formula for 90-degree clockwise rotation
                // Old row 'i' becomes new column 'row - 1 - i'
                // Old column 'j' becomes new row 'j'
                res[j][row - 1 - i] = box[i][j];
            }
        }

        return res;
    }
};

void printBox(const vector<vector<char>> &box)
{
    for (const auto &row : box)
    {
        for (char c : row)
        {
            cout << c << " ";
        }
        cout << "\n";
    }
    cout << "----------------\n";
}

int main()
{
    // Test case setup can go here
    Solution sol;
    vector<vector<char>> box1 = {{'#', '.', '#'}};
    cout << "Test Case 1 Original:\n";
    printBox(box1);
    cout << "Test Case 1 Rotated:\n";
    printBox(sol.rotateTheBox(box1));

    // Test Case 2: Multiple rows with obstacles
    vector<vector<char>> box2 = {
        {'#', '.', '*', '.'},
        {'#', '#', '*', '.'}};
    cout << "Test Case 2 Original:\n";
    printBox(box2);
    cout << "Test Case 2 Rotated:\n";
    printBox(sol.rotateTheBox(box2));

    // Test Case 3: Larger box with complex falling
    vector<vector<char>> box3 = {
        {'#', '#', '*', '.', '*', '.'},
        {'#', '#', '#', '*', '.', '.'},
        {'#', '#', '#', '.', '#', '.'}};
    cout << "Test Case 3 Original:\n";
    printBox(box3);
    cout << "Test Case 3 Rotated:\n";
    printBox(sol.rotateTheBox(box3));

    return 0;
}