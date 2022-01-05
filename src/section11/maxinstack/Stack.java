package section11.maxinstack;


import java.util.ArrayList;

public class Stack<T extends Comparable<T>> {
    //the LIFO last item we inserted is the first one we take out.
    //when the pop() method is called
    private Node<T> head;
    private int count;
    private ArrayList<T> trackMax;

    public Stack(){
        trackMax = new ArrayList<>();
    }

    //return without remove
    public T peek(){
        return head.getData();
    }
    public T getMax(){
        return trackMax.get(trackMax.size()-1);
    }


    public void push(T data) {
        count++;
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(head);
        head = newNode;
        if(trackMax.isEmpty()){
            trackMax.add(data);
        } else {
            if (data.compareTo(trackMax.get(trackMax.size()-1))>0) {
                trackMax.add(data);
            }
        }

    }

    //removes the last item we have inserted O(1)
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        if(head.getData().compareTo(trackMax.get(trackMax.size()-1))==0){
            trackMax.remove(trackMax.size()-1);
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
