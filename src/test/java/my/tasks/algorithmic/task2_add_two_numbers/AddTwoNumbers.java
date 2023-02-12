package my.tasks.algorithmic.task2_add_two_numbers;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AddTwoNumbers {

    @Test
    public void test() {
//        ListNode l13 = new ListNode(9);
        ListNode l12 = new ListNode(9);
        ListNode l11 = new ListNode(9, l12);
        ListNode l1 = new ListNode(9, l11); // 10574 // 99464

//        ListNode l23 = new ListNode(9);
//        ListNode l22 = new ListNode(3);
        ListNode l21 = new ListNode(9);
        ListNode l2 = new ListNode(9, l21);

        ListNode result = addTwoNumbers(l1, l2);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pointer = new ListNode(0);
        ListNode first = pointer;
        ListNode last = pointer;
        while ((l1 != null || l2 != null)) {
            pointer.next = new ListNode();

            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2;

            if (sum >= 10) {
                pointer.val = pointer.val + sum % 10;
                if (pointer.val == 10) {
                    pointer.val = 0;
                }
                pointer.next.val = 1;
            } else {
                pointer.val = pointer.val + sum;
                if (pointer.val == 10) {
                    pointer.val = 0;
                    pointer.next.val = 1;
                }
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            last = pointer;
            pointer = pointer.next;
        }

        if (last.next.val != 1) last.next = null;

        return first;
    }
}
