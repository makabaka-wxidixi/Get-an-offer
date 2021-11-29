package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-29 11:55
 * 对称的二叉树
 * <p>
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class IsSymmetric {
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
     * 时间复杂度：On，最坏到最后才发现不是镜像的
     * 空间复杂度：On，h为树的深度，最大不会超过树的深度。树的最大深度为n/2
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode L, TreeNode R) {
        if (L == null && R == null) {
            return true;
        }
        if (L == null || R == null || L.val != R.val) {
            return false;
        }
        return isMirror(L.left,R.right)&&isMirror(L.right, R.left);
    }
}
