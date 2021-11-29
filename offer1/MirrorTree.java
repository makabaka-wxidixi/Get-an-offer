package offer1;

import com.sun.source.tree.Tree;

import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-29 11:34
 * <p>
 * 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class MirrorTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一：递归
     * 时间复杂度：O(n)，递归遍历每个节点
     * 空间复杂度：O(n)，当树退化为链表时，用O(n)个栈帧
     *
     * @return
     */
    public TreeNode MirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = MirrorTree1(root.right);
        root.right = MirrorTree1(temp);
        return root;
    }

    /**
     * 方法二：栈
     * 时间复杂度：O(n)，遍历每个节点
     * 空间复杂度：O(n)，最差当树子树中，有一个子树只有一层时，栈中最多存储n/2个结点
     *
     * @param root
     * @return
     */
    public TreeNode MirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp =node.left;
            node.left=node.right;
            node.right=temp;
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        return root;
    }
}
