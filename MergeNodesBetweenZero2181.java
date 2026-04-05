public class mergeNodesbetweenzero2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        int sum = 0;
        while(fast.next!=null){
            sum+=fast.val;
            if(fast.next.val==0){
                slow.val=sum;
                if(fast.next.next!=null)
                    slow=slow.next;
                sum=0;
            }
            fast=fast.next;
        }
        slow.next=null;
        return head;
    }
}
