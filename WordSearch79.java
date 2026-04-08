public class WordSearch79 {
    static boolean res = false;

    public static void dfs(int i, int j, int index, char[][] board, boolean[][] visited, String target) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || board[i][j] != target.charAt(index) || res) {
            return;
        }

        if (index == target.length() - 1) {
            res = true;
            return;
        }

        visited[i][j] = true;

        dfs(i + 1, j, index + 1, board, visited, target);
        dfs(i - 1, j, index + 1, board, visited, target);
        dfs(i, j + 1, index + 1, board, visited, target);
        dfs(i, j - 1, index + 1, board, visited, target);
        visited[i][j] = false;
    }

    public static boolean exist(char[][] board, String word) {
        res = false;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    dfs(i, j, 0, board, new boolean[m][n], word);
                    if (res == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}