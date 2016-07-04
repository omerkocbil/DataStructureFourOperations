/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurefouroperations;

/**
 *
 * @author rivendell
 */
public class DoublyLinkedList<T> {

    Node<T> head;
    private int listSize = 0;

    void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            newNode.prev = null;
            head.prev = newNode;
            head = newNode;
        }

    }

    void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            newNode.next = null;
        }
    }

    void printList() {
        Node<T> current = head;

        if (head == null) {
            System.out.println("The List is empty....");
            return;
        } else {
            while (current != null) {
                System.out.print(current.data + " =>");
                current = current.next;
            }
            System.out.print("null\n");
        }
    }

    void removeExactPosition(int position) {
        Node temp = head;
        if (temp == null || position < 0) {
            throw new DoublyLinkedException("LinkedList is empty or index cannot be negative");
        }
        int pos = 0;
        if (temp.prev == null && position == pos) {
            head = head.next;
            head.prev = null;
        } else {
            while (temp != null) {

                if (position == pos) {
                    if (temp.next == null) {  // last node
                        temp.prev.next = null;
                        temp.prev = null;
                    } else { // middle node
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev.next;
                    }
                }
                pos++;
                temp = temp.next;
            }
        }
    }

    int getListSize() {
        Node<T> current = head;
        listSize = 0;
        if (head == null) {
            throw new DoublyLinkedException("The list is empty..");
        } else {
            while (current != null) {
                listSize++;
                current = current.next;
            }
        }
        return listSize;
    }

    void insertAfter(T data, int position) {

        if (position <= 0 || position > getListSize()) {
            throw new DoublyLinkedException("Position cannot be negative or zero..");
        }
        if (head == null) {
            throw new DoublyLinkedException("The list is empty...");
        }
        int pos = 0;
        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        while (current != null) {
            pos++;
            if (pos == position) {
                if (current.next != null) {// eğer son node değil ise
                    newNode.next = current.next;
                    current.next.prev = newNode;
                    current.next = newNode;
                    newNode.prev = current;
                    break;
                } else {
                    current.next = newNode;
                    newNode.prev = current;
                    break;
                }

            }
            current = current.next;

        }

    }

    void removeLast() {

        Node<T> current = head;

        if (head == null) {
            throw new DoublyLinkedException("The list is empty..");
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.prev.next = null;
        }

    }

    void removeFirst() {
        if (head == null) {
            throw new DoublyLinkedException("The list is empty..");
        } else {

            head = head.next;
            head.prev = null;
        }

    }

    public static DoublyLinkedList<Integer> addTwoList(DoublyLinkedList<Integer> list1, DoublyLinkedList<Integer> list2) {
        DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> list4 = list1;
        DoublyLinkedList<Integer> list5 = list2;
        Node<Integer> current1 = list4.head;
        Node<Integer> current2 = list5.head;

        int size1 = list4.getListSize();
        int size2 = list5.getListSize();
        boolean sonMu = true;

        if (size1 != size2) {
            if (size1 > size2) {
                for (int i = 0; i < size1 - size2; i++) {
                    list5.addFirst(0);
                }
                current2 = list5.head;
            } else {
                for (int i = 0; i < size2 - size1; i++) {
                    list4.addFirst(0);
                }
                current1 = list4.head;
            }
        }
        while (current1.next != null) {
            current1 = current1.next;
            current2 = current2.next;
        }
        while (current1 != null) {
            int iSonuc = current1.data + current2.data;
            String sSonuc = String.valueOf(iSonuc).substring(0, 1);
            String sonuc = String.valueOf(iSonuc).substring(1);
            int kesinSonuc = Integer.parseInt(sSonuc);

            if (iSonuc >= 10) {
                if (current1.prev != null) {
                    current1.prev.data = current1.prev.data + 1;
                    sonMu = false;
                }
                iSonuc = iSonuc - 10;
            } else {
                sonMu = false;
            }

            list3.addFirst(iSonuc);

            if (sonMu) {
                list3.addFirst(1);
            }

            current1 = current1.prev;
            current2 = current2.prev;
            sonMu = true;
        }

        return list3;
    }

    public static DoublyLinkedList<Integer> subtractTwoList(DoublyLinkedList<Integer> list1, DoublyLinkedList<Integer> list2) {
        DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
        Node<Integer> current1 = list1.head;
        Node<Integer> current2 = list2.head;
        int size1 = list1.getListSize();
        int size2 = list2.getListSize();
        String sSayi1 = "", sSayi2 = "";

        while (current1 != null) {
            sSayi1 += String.valueOf(current1.data);
            
            current1 = current1.next;
            
        }
        
        while (current2 != null) {
            
            sSayi2 += String.valueOf(current2.data);
            
            current2 = current2.next;
        }

        int sayi1 = Integer.parseInt(sSayi1);
        int sayi2 = Integer.parseInt(sSayi2);

        current1 = list1.head;
        current2 = list2.head;

        if (sayi1 < sayi2) {
            throw new DoublyLinkedException("Bu iki sayı çıkarılamaz");
        }
        if (size1 != size2) {
            if(size1 > size2){
                for (int i = 0; i < size1 - size2; i++) {
                    list2.addFirst(0);
                }
                current2 = list2.head;
            }
            else{
                throw new DoublyLinkedException("Bu iki sayı çıkarılamaz");
            }
        } 
            while (current1.next != null) {
                current1 = current1.next;
            }
            while (current2.next != null) {
                current2 = current2.next;
            }
            while (current1 != null) {
                int iSonuc = current1.data - current2.data;
                boolean kesinSonuc = current1.data < current2.data;

                if (kesinSonuc) {
                    if (current1.prev != null) {
                        current1.prev.data = current1.prev.data - 1;
                        iSonuc = iSonuc + 10;
                    }
                }

                list3.addFirst(iSonuc);

                current1 = current1.prev;
                current2 = current2.prev;
            }
        

        return list3;
    }

    public static DoublyLinkedList<Integer> divideTwoList(DoublyLinkedList<Integer> list1, DoublyLinkedList<Integer> list2) {
        DoublyLinkedList<Integer> list3 = list1;
        DoublyLinkedList<Integer> list4 = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> list5 = new DoublyLinkedList<>();
        list5.addFirst(1);
        Node<Integer> current1 = list1.head;
        Node<Integer> current2 = list2.head;
        int size1 = list1.getListSize();
        int size2 = list2.getListSize();
        String sSayi1 = "", sSayi2 = "", sSayi3 = "";

        while (current1 != null) {
            sSayi1 += String.valueOf(current1.data);
            current1 = current1.next;
        }
        
        while (current2 != null) {
            sSayi2 += String.valueOf(current2.data);
            current2 = current2.next;
        }

        int sayi1 = Integer.parseInt(sSayi1);
        int sayi2 = Integer.parseInt(sSayi2);
        int sayi3 = sayi2;

        current1 = list1.head;
        current2 = list2.head;

        if (sayi1 < sayi2) {
            throw new DoublyLinkedException("Bu iki sayı bölünemez");
        }

        while (sayi3 >= sayi2) {
            list3 = DoublyLinkedList.subtractTwoList(list3, list2);
            Node<Integer> current3 = list3.head;
            while (current3 != null) {
                sSayi3 += String.valueOf(current3.data);
                current3 = current3.next;
            }
            sayi3 = Integer.parseInt(sSayi3);
            if (list4.head != null) {
                list4 = DoublyLinkedList.addTwoList(list4, list5);
            } else {
                list4.addFirst(1);
            }
            sSayi3 = "";
        }

        return list4;
    }

    public static DoublyLinkedList<Integer> multipleTwoList2(DoublyLinkedList<Integer> list1, DoublyLinkedList<Integer> list2) {
        DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
        Node<Integer> current1 = list1.head;
        Node<Integer> current2 = list2.head;
        int size1 = list1.getListSize();
        int size2 = list2.getListSize();
        String sSayi1 = "", sSayi2 = "", sSayi3 = "";

        while (current1 != null) {
            sSayi1 += String.valueOf(current1.data);
            list3.addFirst(current1.data);
            current1 = current1.next;
            
        }
        
        while (current2 != null) {
            sSayi2 += String.valueOf(current2.data);
            current2 = current2.next;
        }

        int sayi1 = Integer.parseInt(sSayi1);
        int sayi2 = Integer.parseInt(sSayi2);
        int sayi3 = sayi2;

        current1 = list1.head;
        current2 = list2.head;

        for (int i = 0; i < sayi2 - 1; i++) {
            list3 = DoublyLinkedList.addTwoList(list3, list1);
        }

        return list3;
    }
}

class DoublyLinkedException extends RuntimeException {

    public DoublyLinkedException(String message) {
        super(message);
    }
}
