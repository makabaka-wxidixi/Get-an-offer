package offer1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-26 18:24
 * <p>
 * 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 */
public class ReversePrint {

    //内部节点类
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方式一：迭代
     * 时间复杂度：两次遍历链表On+On，总体还是On
     * 空间复杂度：O1，没有用额外的数据结构
     * <p>
     * 两次遍历链表，第一次用于确定int数组大小。第二次填充数据
     *
     * @param head 链表头结点
     * @return 返回的数组
     */
    public int[] reversePrint1(ListNode head) {
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            curr = curr.next;
            size++;
        }
        int[] arr = new int[size];
        curr = head;
        while (curr != null) {
            arr[--size] = curr.val;
            curr = curr.next;
        }
        return arr;
    }

    /**
     * 方式二：哈希+迭代
     * 时间复杂度：一次遍历链表，然后循环将集合中的数据放到数组中。总共是On
     * 空间复杂度：使用了额外的ArrayList集合，所以是On
     *
     * @param head 链表头结点
     * @return 返回的数组
     */
    public int[] reversePrint2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        int len = list.size();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = list.get(len - i - 1);
        }
        return arr;
    }

    /**
     * 方式三：栈+迭代
     * 时间复杂度：一次遍历链表，然后循环将栈中的数据放到数组中。总共是On
     * 空间复杂度：使用了额外的栈，所以是On
     *
     * @param head 链表头结点
     * @return 返回的数组
     */
    public int[] reversePrint3(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }
        int[] arr = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            arr[index++] = stack.pop();
        }
        return arr;
    }


    /**
     * 方式四：递归
     * 时间复杂度：递归确定数组大小，然后回溯填装数据。共On
     * 空间复杂度：递归使用栈帧，所以是On
     *
     * @param head 链表头结点
     * @return 返回的数组
     */
    public int[] reversePrint(ListNode head) {
        return recursion(head, 0);
    }

    private int[] recursion(ListNode head, int i) {
        if (head == null) {
            return new int[i];
        }
        int[] arr = recursion(head.next, i + 1);
        int len = arr.length;
        arr[len - i - 1] = head.val;
        return arr;
    }
}
