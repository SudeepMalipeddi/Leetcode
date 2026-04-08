public class MergeNodesBetweenZero2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        int sum = 0;
        while (fast.next != null) {
            sum += fast.data;
            if (fast.next.data == 0) {
                slow.data = sum;
                if (fast.next.next != null)
                    slow = slow.next;
                sum = 0;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
