package offer1;

import java.util.List;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-11-27 18:56
 * <p>
 * 翻转链表
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 */
public class reverseList {

    //内部类
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 方式一：指针
     * 时间复杂度；On。从尾到头遍历链表
     * 空间复杂度：O1。使用三个指针
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null, curr = head, rear = head.next;
        while (rear != null) {
            curr.next = prev;
            prev = curr;
            curr = rear;
            rear = rear.next;
        }
        curr.next = prev;
        return curr;
    }

    /**
     * 方式二：递归
     * 时间复杂度：On的链表长度
     * 空间复杂度：On的栈帧
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList3(ListNode head) {

    }
}
