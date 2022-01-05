package section7;

public class DoublyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            //this is the first node
            tail = newNode;
            head = newNode;
        } else {
            //insert to the end of the list
            //we just have to manipulate the tail node and its references in 0(1)
            tail.setNextNode(newNode);
            newNode.setPreviousNode(tail);
            tail = newNode;
        }
    }

    //traverse the list forward
    public void traverseForward() {
        Node<T> actualNode = head;
        while (actualNode != null) {
            System.out.println(actualNode);
//            System.out.println("this is head's point" + head);
            actualNode = actualNode.getNextNode();
        }
    }

    //traverse the list backward
    public void traverseBackward() {
        Node<T> actualNode = tail;
        while (actualNode != null) {
            System.out.println(actualNode);
            actualNode = actualNode.getPreviousNode();
        }
    }

}


