package offer1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-25 14:04
 * <p>
 * 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以
 * 向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
 * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够
 * 到达多少个格子？
 * <p>
 * 题目分析：这是很传统的搜索题目，我们可以用DFS或者BFS来解决
 */
public class movingCount {

    /**
     * 方法一：BFS
     * 时间复杂度：如果k足够大，能全部进入，那么一个格子最多访问两次，那么就是O(m x n)。如果k比较小，无法连通。
     * 那么就和k有关，最大当k==8时，格子最多访问2次，那么就是kxk/2x2也就是k^2。总体看来还是O(mxn)
     * <p>
     * 空间复杂度：需要一个m x n的boolean二维数组来记录是否被访问过，然后队列最大存储数组个数为Max(m,n)。总体上是O(m x n)
     *
     * @param m 行
     * @param n 列
     * @param k 个位之和
     * @return 返回可以到达的格数
     */
    public int movingCount1(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int[] xArr = new int[]{0, 1};//横坐标增量
        int[] yArr = new int[]{1, 0};//纵坐标增量
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        /*
        这里是向左和向右的数组
        根据题意，机器人从左上角开始运动，那么机器人只要向下或者向右即可。
        如果是四个方向，那么数组就是
        int[] xArr = new int[]{0,1,0,-1};
        int[] yArr = new int[]{1,0,-1,0};
        分别对应：下、右、上、左
         */
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        int res = 1;//用于记录可以动的表格数
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();//取出数据
            for (int i = 0; i < 2; i++) {
                int x = arr[0] + xArr[i];
                int y = arr[1] + yArr[i];
                //判断新节点是否满足
                if (x >= m || y >= n || vis[x][y] || get(x) + get(y) > k) {
                    continue;
                }
                vis[x][y] = true;
                res++;
                queue.add(new int[]{x, y});
            }
        }
        return res;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }


    /**
     * 方式二：DFS
     * 时间复杂度：每个节点最多访问两次，O(nm)
     * 空间复杂度：O(mn)的boolean数组 + O(Max(m,n))或者O(k)的栈帧
     */
    public int movingCount2(int m, int n, int k) {
        boolean[][] vis = new boolean[m][n];
        return dfs(0, 0, m, n, k, vis);
    }

    private int dfs(int x, int y, int m, int n, int k, boolean[][] vis) {
        if (x >= m || y >= n || vis[x][y] || get(x) + get(y) > k) {
            return 0;
        }
        vis[x][y] = true;
        return 1 + dfs(x + 1, y, m, n, k, vis) + dfs(x, y + 1, m, n, k, vis);
    }

}
