package offer1;

import java.sql.Array;
import java.util.*;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-28 14:00
 * <p>
 * 从上到下打印二叉树
 * 该题有很多变种
 * 下面给出不同问题的解决方法
 * 要求一：从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 要求二：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 要求三：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的
 * 顺序打印，其他行以此类推。
 */
public class TreeLevelOrder {

    //内部节点类
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    要求一：从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
    */

    /**
     * 方式一：队列
     * 时间复杂度：On——遍历所有结点
     * 空间复杂度：使用了一个队列（On）和集合（On）。总体是On
     */
    public int[] levelOrder1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /*
    要求二：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    */

    /**
     * 方式一：队列
     * 时间复杂度：遍历每个节点（On）
     * 空间复杂度：队列（On）
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size != 0) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            res.add(temp);
        }
        return res;
    }

    /*
    要求三：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的
    顺序打印，其他行以此类推。
    */

    /**
     * 方式一：队列+逆序
     * 时间复杂度：遍历每个结点On
     * 空间复杂度：使用到队列，当树为满二叉树时，最多有n/2个结点在队列中。On
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3_1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size != 0) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            if (res.size() % 2 == 1) {
                Collections.reverse(temp);
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 方式二：双端队列
     * 时间复杂度：遍历每个结点On
     * 空间复杂度：满二叉树时，此时队列中最多存在n/2个结点。On
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3_2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            LinkedList<Integer> deque = new LinkedList<>();
            int size = queue.size();
            while (size != 0) {
                TreeNode node = queue.poll();
                //如果是偶数层，那么就正常添加
                if (res.size() % 2 == 0) {
                    deque.addLast(node.val);
                } else {//奇数层，就逆序添加
                    deque.addFirst(node.val);
                }
                //保证结点顺序不变。只是改变结点值得输出顺序
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            res.add(deque);
        }
        return res;
    }


    /**
     * 方式三：双端队列+奇偶层分离
     * 时间复杂度：遍历每个结点On
     * 空间复杂度：满二叉树时，此时队列中最多存在n/2个结点。On
     * 考虑到方法二中每次都需要判断奇偶层，我们可以将奇偶层放在一个循环中
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3_3(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            deque.add(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> temp = new ArrayList<>();
            while (size != 0) {
                TreeNode node = deque.pollLast();
                temp.add(node.val);
                if (node.left != null) {
                    deque.addFirst(node.left);
                }
                if (node.right != null) {
                    deque.addFirst(node.right);
                }
                size--;
            }
            res.add(temp);
            //如果此时队列已经为null了，就直接跳出循环
            if (deque.isEmpty()) {
                break;
            }
            size = deque.size();
            temp = new ArrayList<>();
            while (size != 0) {
                TreeNode node = deque.pollFirst();
                temp.add(node.val);
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                size--;
            }
            res.add(temp);
        }
        return res;
    }
}