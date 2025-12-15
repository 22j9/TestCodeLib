class Solution {
    public int shortestPath(String[][] grid, int fuelCapacity) {
        int[] start = null;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j].equals("S")) {
                    start = new int[]{i, j};
                    break;
                }
            }
            if (start != null) {
                break;
            }
        }
        int[][] maxFuelAt = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maxFuelAt[i][j] = -1;
            }
        }
        maxFuelAt[start[0]][start[1]] = fuelCapacity;
        // [i, j, steps, currentFuel]
        Queue<int[]> bfsGridBuffer = new LinkedList<>();
        bfsGridBuffer.offer(new int[]{start[0], start[1], 0, fuelCapacity});
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while (!bfsGridBuffer.isEmpty()) {
            int[] current = bfsGridBuffer.poll();
            int currentX = current[0];
            int currentY = current[1];
            int currentStep = current[2];
            int currentFuel = current[3];
            for (int[] dir : dirs) {
                int nextX = currentX + dir[0];
                int nextY = currentY + dir[1];
                if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col
                    || grid[nextX][nextY].equals("#")) {
                    continue;
                }
                if (currentFuel < 1) {
                    continue; // cannot reach next cell
                }
                if (grid[nextX][nextY].equals("D")) {
                    return currentStep + 1;
                }
                int newFuelNext =
                    grid[nextX][nextY].equals("G") ? fuelCapacity : currentFuel - 1;
                // if we reach a cell (even visited) with more fuel, it could be a better path
                if (newFuelNext > maxFuelAt[nextX][nextY]) {
                    maxFuelAt[nextX][nextY] = newFuelNext;
                    bfsGridBuffer.offer(new int[]{nextX, nextY, currentStep + 1, newFuelNext});
                }
            }
        }
        return -1;
    }
}
