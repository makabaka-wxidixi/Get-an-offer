package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-22 10:18
 * <p>
 * 寻找矩阵中的路径
 * <p>
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个
 * 单元格内的字母不允许被重复使用。
 * <p>
 * 从矩形中寻找路径，很容易联想到图的深度优先(DFS)和广度优先(BFS)算法
 */
public class PathInRectangle {

    /**
     * 方法一：DFS
     * 最差复杂度：O(MN3^k)——当二维数组最后一个数是起点，并且找到word字符串时，最差是需要遍历二维数组（M*N）+word长度k，每个节点都有3个方向
     * 那么就是3^k
     * 最好复杂度：O(3^k)——第二维数组中第一个数就是起点，并且找到word字符串。
     * 空间复杂度：O(k)——递归深度不会超过k，因为当剪枝的时候会将栈帧归还给内存，只不过会反复调用罢了。当然当k==MN时，那么就是O(MN)
     *
     * @param board 二维数组
     * @param word  目标字符串
     * @return 判断字符串是否存在
     */
    public boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //对二维数组中的每一个数都尝试深度遍历，因为每一个都有可能是起始节点
                if (DFS(board, wordArr, i, j, 0)) return true;
            }
        }
        //都不是那就返回false
        return false;
    }

    private boolean DFS(char[][] board, char[] word, int row, int col, int k) {
        //判断当前节点是否越界，或者当前值是否等于字符串中的值
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0
                || board[row][col] != word[k])
            return false;//剪枝
        if (k == word.length - 1)
            return true;//最后一个数
        //能到这里就说明是word字符串中的内容，但是不是最后一个，需要进行对周尾结点进行判断
        board[row][col] = '\0';
        boolean res = DFS(board, word, row - 1, col, k + 1) || DFS(board, word, row, col + 1, k + 1)
                || DFS(board, word, row + 1, col, k + 1) || DFS(board, word, row, col - 1, k + 1);
        //无论是否结果怎样，都要将当前数恢复
        board[row][col] = word[k];
        return res;
    }
}
