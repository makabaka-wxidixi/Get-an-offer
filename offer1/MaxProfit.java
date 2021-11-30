package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-30 11:28
 * 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 */
public class MaxProfit {
    /**
     * 方法一：暴力
     * 时间复杂度：O(n^2)
     * 空间复杂度：O1
     *
     * @return
     */
    public int MaxProfit1(int[] prices) {
        int len = prices.length;
        int maxPro = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int temp = prices[j] - prices[i];
                if (temp > maxPro) {
                    maxPro = temp;
                }
            }
        }
        return maxPro;
    }

    /**
     * 方式二：动态规划
     * 时间复杂度：On——遍历一次数组
     * 空间复杂度：O1——用cost来存储前面的最小价格，用maxPro来存储前面n-1次的最大利润
     * @param prices
     * @return
     */
    public int MaxProfit2(int[] prices) {
        int maxPro = 0;
        int cost = Integer.MAX_VALUE;
        for (int price : prices) {
            cost = Math.min(cost, price);
            maxPro = Math.max(maxPro, price - cost);
        }
        return maxPro;
    }
}
