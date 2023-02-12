package my.tasks.algorithmic.task2_add_two_numbers;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AddTwoNumbers {

    @Test
    public void test() {
        ListNode l13 = new ListNode(9);
        ListNode l12 = new ListNode(9, l13);
        ListNode l11 = new ListNode(9, l12);
        ListNode l1 = new ListNode(9, l11); // 10574 // 99464

//        ListNode l23 = new ListNode(9);
//        ListNode l22 = new ListNode(9);
        ListNode l21 = new ListNode(9);
        ListNode l2 = new ListNode(9, l21);

        ListNode result = addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder numberOne = new StringBuilder();
        reverseNumber(l1, numberOne);

        StringBuilder numberTwo = new StringBuilder();
        reverseNumber(l2, numberTwo);

        String sumAsString = getSum(numberOne, numberTwo);

        ListNode sumNew = getSumNew(l1, l2);


        List<ListNode> listNodes = sumAsString.chars()
                .mapToObj(symbol -> new ListNode(Integer.parseInt(String.valueOf((char) symbol))))
                .collect(Collectors.toList());

        for (int j = listNodes.size() - 1; j >= 0; j--) {
            if (j != 0) {
                listNodes.get(j).next = listNodes.get(j - 1);
            }
        }

        return listNodes.get(listNodes.size() - 1);
    }

    private ListNode getSumNew(ListNode l1, ListNode l2) {
        ListNode pointer = new ListNode(0);
        ListNode first = pointer;
        while ((l1 != null || l2 != null) && pointer != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2;
            boolean hasOneNext = l1 != null && l1.next != null;
            boolean hasTwoNext = l2 != null && l2.next != null;
            boolean willNextIteration = hasOneNext || hasTwoNext;

            if (sum >= 10) {
                pointer.val = pointer.val + sum % 10;
                if (pointer.val == 10) {
                    pointer.val = 0;
                }
                pointer.next = new ListNode(1);
            } else {
                pointer.val = pointer.val + sum;
                if (pointer.val == 10) {
                    pointer.val = 0;
                    pointer.next = new ListNode(1);
                } else if (willNextIteration) {
                    pointer.next = new ListNode();
                }
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            pointer = pointer.next;
        }
        return first;
    }

    private String getSum(StringBuilder stringBuilder, StringBuilder stringBuilder2) {
        BigInteger numberOne = new BigInteger(stringBuilder.reverse().toString());
        BigInteger numberTwo = new BigInteger(stringBuilder2.reverse().toString());
        return String.valueOf(numberOne.add(numberTwo));
    }

    private void reverseNumber(ListNode node, StringBuilder stringBuilder) {
        if (node != null) {
            stringBuilder.append(node.val);
            reverseNumber(node.next, stringBuilder);
        }
    }
}
