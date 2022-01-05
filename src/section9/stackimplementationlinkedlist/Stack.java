package section9.stackimplementationlinkedlist;

public class Stack<T> {
    //the LIFO last item we inserted is the first one we take out.
    //when the pop() method is called
    private Node<T> head;
    private int count;


    public void push(T data) {
        count++;
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(head);
        head = newNode;
    }

    //removes the last item we have inserted O(1)
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = head.getData();
        head = head.getNextNode();
        count--;
        return item;
    }

    //O(1)
    public int size() {
        return count;
    }

    //O(1)
    public boolean isEmpty() {
        return count == 0;
    }
}
