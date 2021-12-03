package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-12-01 19:34
 * <p>
 * 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右
 * 或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 */
public class MaxValue {
    /**
     * 方式一：动态规划
     * 时间复杂度：O(MN)——因为需要遍历整个二维数组
     * 空间复杂度：O(M+N)——需要额外增加一行一列辅助数据
     * 分析：dp代表每个位置的最大价值。那么dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
     * 但是边界不满足条件，那我们考虑增加一行一列来满足这个条件
     * @param grid 礼物矩阵
     * @return 返回最大价值
     */
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        //多增加一行一列
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
            }
        }
        //返回最后位置的dp数组
        return dp[row][col];
    }
}
