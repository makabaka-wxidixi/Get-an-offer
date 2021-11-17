package offer1;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-17 20:58
 * 查找数组中的重复数字
 * <p>
 * 题目要求：在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有
 * 几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 */
public class repeatNumOfArray {

    /**
     * 方式一：哈希
     * 时间复杂度：On 从头遍历数组：On。HashSet的插入操作平均是O1
     * 空间复杂度：O1
     *
     * @param nums 传入的数组
     * @return 返回重复的数字
     */
    public int findRepeatNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                return nums[i];
        }
        return -1;
    }


    /**
     * 方式二：原地交换
     * 思路：将所有数字都以arr[i]=i这种形式排列
     * 第一种情况：arr[i]=i，就说明i已经摆放到规定的位置（arr[i]）上了，直接跳过
     * 第二种情况：arr[i]=arr[arr[i]]，说明arr[i]已经在对应位置上了，但是又出来一个，那么就肯定是重复了
     * 第三种情况：上面两种都不满足，那么就将i和arr[i]这两个下标的元素进行交换
     * 时间复杂度：On（遍历数组On，交换和判断操作是O1）
     * 空间复杂度：O1
     *
     * @param nums 传入的数组
     * @return 返回重复的数字
     */
    public int findRepeatNumber2(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (i == nums[i]) {
                i++;
                continue;
            }
            if (nums[i] == nums[nums[i]])
                return nums[i];
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }
}
