package offer1;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-29 10:48
 * <p>
 * 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class IsSubStructure {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方式一：递归
     * 时间复杂度：O(nm)——M和N分别是A树和B树的结点个数。每次遍历A结点时，都会调用isInclude方法进行判断。那么就是Omn
     * 空间复杂度：O(m)——最坏是当M和N都退化为链表时，如果M>N，最坏情况下是遍历到A树最后一个结点OM，如果N>M，那么最多也就
     * 遍历完A树就会发现判断失败，那么最多也是OM
     *
     * @param A 主树
     * @param B 子树
     * @return
     */
    public boolean isSubStruture(TreeNode A, TreeNode B) {
        /*
        由于空树不是任何树的子树，所以需要对根节点是否为空进行判断
        然后以当前节点开始，判断是否满足
        如果不满足那么就递归遍历两个子节点
         */
        return (A != null && B != null) && (isInclude(A, B) || isSubStruture(A.left, B) || isSubStruture(A.right, B));
    }

    /**
     * 用于判断B是不是A的子树
     */
    private boolean isInclude(TreeNode A, TreeNode B) {
        //如果后序B为NULL那么满足条件
        if (B == null) {
            return true;
        }
        //当前节点为空或者不等于b那么就不满足条件
        if (A == null || A.val != B.val) {
            return false;
        }
        //然后递归遍历两棵树的左右结点
        return isInclude(A.left, B.left) && isInclude(A.right, B.right);
    }
}
