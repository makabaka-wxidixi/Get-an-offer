package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-18 21:36
 * 二维数组中的查找
 * <p>
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入
 * 这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class findNumberIn2DArrayDemo {

    /**
     * 方式一：线性查找
     * 思路：由于数组是递增。那么左下角是每一列的最大值，每一行的最小值，因此可以拿来做参考值
     *          如果目标值大于参考值：那么目标值肯定不在这一列，那么列数就加1
     *          如果目标值小于参考值：那么目标值肯定不在这一行，那么行数就减一
     *          如果中途找到目标值就返回true，否则行和列都超出范围之后就退出程序
     * 时间复杂度：O(n+m)每次循环判断为O1，做多在右上角退出，最差是O(n+m)，最好是O(n)或者O(m);
     * 空间复杂度：O1
     * @param matrix 目标二维数组
     * @param target 目标值
     * @return 判断是否包含目标值
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1, col = 0, maxCol = matrix[0].length - 1;//和左下角的结点进行比较
        while (true) {
            if (row < 0 || col > maxCol) {
                return false;
            }
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {//目标值小于左下角的值，那么就col++
                row--;
            } else {
                col++;
            }
        }
    }
}
