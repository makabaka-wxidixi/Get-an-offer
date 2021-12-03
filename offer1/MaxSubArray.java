package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-12-01 17:41
 * 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 */
public class MaxSubArray {

    /**
     * 方法一：动态规划
     * 时间复杂度：On——遍历数组On*每次循环是O1复杂度
     * 空间复杂度：On——使用一个dp数组来存储每个位置上的最大子序和
     *
     * @param nums 传入的数组
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];//用于保存出现过的最大值
        for (int i = 1; i < len; i++) {
            /*
            前面的最大子序和对当前数没有贡献，那么当前位置的最大子序和就是当前的数
            对于一个数，后面的情况是未知的，能控制的只有当前位置，我们保证当前位置的数是最大的就好了
             */
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
            //每次都要和历史最大值作比较
            res = Math.max(dp[i], res);
        }
        return res;
    }
}