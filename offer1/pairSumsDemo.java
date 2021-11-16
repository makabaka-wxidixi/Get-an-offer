package offer1;

import java.util.*;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-16 21:22
 * <p>
 * 16.24数对和
 * 条件nums.length<=100000
 */
public class pairSumsDemo {

    /**
     * 方式一：哈希（1）
     * <p>
     * 思路：将目标值target和遍历到的数据ele做减法。如果得到的值在map中存在，就说明配对了。如果没有找到，就将当前遍历到的数据ele
     * 放入map中，整个过程只需要遍历数组一遍，使用到集合。
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param nums   传入的数组
     * @param target 目标值
     * @return 每两个数为一组进行返回
     */
    public List<List<Integer>> pairSums1(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length <= 1) {
            return list;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int other = 0;
        List temp = null;
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            other = target - nums[i];//得到另一个值
            if (map.isEmpty()) {//如果map为空，就直接录入
                map.put(nums[i], 1);
            } else if (map.get(other) != null) {//如果集合中存在另一个值，就取出数据other
                temp = new ArrayList();
                temp.add(other);
                temp.add(nums[i]);
                list.add(temp);
                int j = 0;
                if ((j = map.get(other)) > 1) {//说明有多个other
                    map.put(other, --j);
                } else {//只有一个other
                    map.remove(other);
                }
            } else {//不存在另一个值，就将nums[i]加入到集合中
                if (map.get(nums[i]) != null) {//集合中已经存在
                    int j = map.get(nums[i]);
                    map.put(nums[i], ++j);
                } else {
                    map.put(nums[i], 1);
                }
            }
        }
        return list;
    }

    /**
     * 方式1：哈希（2）
     * 思路：和哈希（1）一样
     * 时间复杂度：On
     * 空间复杂度：On
     *
     * @param nums   传入的数组
     * @param target 目标值
     * @return 每两个数为一组进行返回
     */
    public List<List<Integer>> pairSums2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        for (Integer ele : nums) {
            Integer temp = (Integer) map.get(target - ele);//值
            if (temp != null) {
                list.add(Arrays.asList(ele, target - ele));
                if (temp == 1) {
                    map.remove(target - ele);
                } else {
                    map.put(target - ele, temp - 1);
                }
            } else {
                map.put(ele, map.getOrDefault(ele, 0) + 1);
            }
        }
        return list;
    }
}
