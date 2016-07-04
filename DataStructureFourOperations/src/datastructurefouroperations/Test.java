package datastructurefouroperations;

public class Test {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> bol = new DoublyLinkedList<>();
        list1.addFirst(5);
        list1.addFirst(5);
        list1.addFirst(5);
        list1.addFirst(5);
        list1.printList();
        list2.addFirst(5);
        list2.addFirst(5);
        list2.addFirst(5);
        list2.addFirst(5);
        list2.printList();
        bol = DoublyLinkedList.multipleTwoList2(list1, list2);
        bol.printList();
    }

}
