package offer1;


/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-21 10:18
 * <p>
 * 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 假设青蛙跳n阶，不管他们之前怎么跳，最后一跳要么是跳两个台阶，要么就是跳一个台阶。跳到n-1个台阶有f(n-1)种跳法
 * ，跳到n-2个台阶有f(n-2)种跳法。那么是不是就有f(n)=f(n-1)+f(n-2)，这不就是斐波那契数列嘛。那么我们这道题就
 * 尝试用递归来处理
 */
public class TheFog {

    /**
     * 方式一：传统递归方法
     * 时间复杂度：O(2^n)
     * 空间复杂度：On
     * <p>
     * 思想：采用分治方法，将一个问题分解成多个子问题，然后求解子问题
     * 分析：这种方法是个树状的，例如n=5。那么f(3)就需要计算2次。如果n更大那么就会出现更多的重复计算，我们能
     * 不能用一个容器来记录这些重复出现的数据，这样我们就可以直接使用不用再次计算
     * 参考下面方法二
     *
     * @param n 台阶数
     * @return 返回方式数
     */
    public int numWays1(int n) {
        if (n == 1 || n == 0)
            return 1;
        if (n == 2)
            return 2;
        return (numWays1(n - 1) + numWays1(n - 2)) % (1000000007);
    }

    /*
    方法二：递归优化——记忆化递归
    时间复杂度：On：在计算一个分支的时候，另一个分支的值已经出来了，根据递归特性，那么整体就是线性的
    空间复杂度：On：On的数组，树高度为h，h<n。总体还是On
    算法分析：
        用一个数组来记录到达每个层所需要的步数，递归在这个“树”中会一直向左子树计算，然后回来的过程中每个子树的
        孩子的计算都可以用到通过左子树填到数组中的值，那么就避免了很多重复的计算。其实到这里就有点动态规划的雏形了
        那么我们就尝试用动态规划的方式来解决，下面方法（三）介绍
     */
    public int numWays2(int n) {
        if (n == 1 || n == 0)
            return 1;
        if (n == 2)
            return 2;
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        return myNumWays(n, arr);
    }

    private int myNumWays(int n, int[] arr) {
        if (arr[n] != 0) {//如果数组中已经记录的有值了，那么就直接返回
            return arr[n];
        }
        int ret = (myNumWays(n - 1, arr) + myNumWays(n - 2, arr)) % 1000000007;
        arr[n] = ret;//给该位置赋值
        return ret;
    }

    /**
     * 方法三：动态规划
     * 动态规划思想：将待求问题分解成多个子问题，但是每个子问题之间又不是完全独立的，是有一定的联系的。和分治
     * 最大的区别是，分治思想在求解子问题时，可能会导致大量的重复求解。如果我们可以保存以解决的子问题的结果。
     * 再在需要时直接使用以求得的答案。这样我们就可以节省很多资源
     * 时间复杂度：On
     * 空间复杂度：O1 用了三个变量a,b,num
     * 此题：我们用num来存储子问题的结果
     * @param n 台阶数
     * @return 方式
     */
    public int numWays3(int n) {
        if (n == 0 || n == 1)
            return 1;
        if (n == 2)
            return 2;
        int a = 1, b = 2, num = 3;
        for (int i = 3; i <= n; i++) {
            /*
            算法核心
                将得到的num=a+b，用于记录上一次的结果
                上一轮的b赋值给本轮的a，上一轮的结果赋值给本轮的b。然后再进行计算
             */
            num = (a + b) % (1000000007);
            a = b;
            b = num;
        }
        return num;
    }
}