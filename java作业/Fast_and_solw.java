public class Fast_and_slow {
    public boolean hasCycle(ListNode head) {
              ListNode slow = head;
        ListNode fast = head;
         while(fast!=null){
             fast = fast.next;
            if(fast != null) {
                fast = fast.next;
            }
            if(fast == slow) {
                return true;
            }
            slow = slow.next;
         }
         return false;
    }
};
