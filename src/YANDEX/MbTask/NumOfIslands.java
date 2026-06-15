package YANDEX.MbTask;

import java.util.LinkedList;
import java.util.Queue;

// https://neetcode.io/problems/count-number-of-islands/question
public class NumOfIslands {
    private static final int[][] directions = {{1, 0}, {-1, 0},
            {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int rows = grid.length;
        int colums = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < colums; c++) {
                if (grid[r][c] == '1') {
                    bfs(grid, r, c);
                    islands++;
                }
            }
        }
        return islands;
    }

    public void bfs(char[][] grid, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        grid[r][c] = '0';
        q.add(new int[] {r,c});
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];

            for (int[] dir : directions) {
                int dr = row + dir[0];
                int dc = col + dir[1];
                if (dr >= 0 &&
                        dc >=0 &&
                        dr < grid.length &&
                        dc < grid[0].length &&
                        grid[dr][dc] == '1'
                ) {
                    q.add(new int[] {dr, dc});
                    grid[dr][dc] = '0';
                }
            }
        }
    }
}
