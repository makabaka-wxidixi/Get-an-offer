package offer1;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-19 23:28
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class buildTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private Map<Integer, Integer> indexMap;


    /**
     * 方式一：递归
     * 时间复杂度：On——每次递归都加入一个结点，总共n个结点，因此为On。又用到了HashMap中的get方法。get方法如果哈希值不
     * 发生碰撞，数组中的每个链表都只有一个结点，那么时间复杂度就是O1，如果发生碰撞，链表为On，红黑树为Ologn。
     * 所以时间复杂度为On
     * 空间复杂度：On——使用map来存储键值对On，此外，递归使用栈帧复杂度为Oh，h为树的高度，h<n，所以是On
     * 思路：
     * 前序遍历：根左右
     * 中序遍历：左根右
     * 我们可以从前序中得到根节点（最左侧的值），我们得到根节点之后，可以通过中序遍历结果来判断左子树和右子树
     * 的区域。
     *
     * @param preorder       前序遍历数组
     * @param inorder        中序遍历数组
     * @param preorder_left  前序数组左边界下标
     * @param preorder_right 前序数组右边界下标
     * @param inorder_left   中序数组左边界下标
     * @param inorder_right  中序数组右边界下标
     * @return
     */
    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


    /*
    方式二：迭代
    思路：
        前序遍历特点：第一个结点是根节点
                    后一个节点是前一个结点的左孩子，或者是前一个结点（或其某个祖先节点）的右孩子
        中序遍历特点：前面的结点比后面的结点更左，第一个结点就是最左边，最后一个结点就是最右边
     */

    /**
     * @param preorder 传入的前序遍历结果
     * @param inorder  传入的中序遍历结果
     * @return 返回构建之后的树
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null | preorder.length == 0) {
            return null;
        }
        //栈中放的节点都是没有判断过是否有右孩子的
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        TreeNode node;
        int in_index = 0;
        for (int i = 1; i < preorder.length; i++) {
            node = stack.peek();//当前栈顶结点
            //如果栈顶结点和in_index所指向的结点不等，就说明栈顶结点有左孩子，那么从前序遍历结果就可以得到其左孩子
            if (node.val != inorder[in_index]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            } else {//栈顶结点和in_index相等，根据前序遍历结果，下一个节点一定是当前节点（或其祖先节点的右结点）
                while (!stack.isEmpty() && stack.peek().val == inorder[in_index]) {
                    //栈顶元素如果等于in_index，那么说明栈顶元素没有右孩子，因为中序遍历结点顺序是自左向右，自下向上
                    //栈中下一个元素一定是当前栈顶元素的祖先节点，向上不等，那一定是向右
                    node = stack.pop();
                    in_index++;
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }
        return root;
    }
}

